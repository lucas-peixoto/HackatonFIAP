package br.com.fiap.booking.booking;

import br.com.fiap.booking.exception.NotFoundException;
import br.com.fiap.booking.mailer.MailService;
import br.com.fiap.booking.product.*;
import br.com.fiap.booking.room.*;
import br.com.fiap.booking.user.User;
import br.com.fiap.booking.user.UserClient;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class BookingService {

    private final BookingRepository bookingRepository;
    private final MailService mailer;
    private final RoomClient roomClient;
    private final ProductClient serviceClient;
    private final UserClient userClient;

    public BookingService(BookingRepository bookingRepository, MailService mailer, RoomClient roomClient, ProductClient serviceClient, UserClient userClient) {
        this.bookingRepository = bookingRepository;
        this.mailer = mailer;
        this.roomClient = roomClient;
        this.serviceClient = serviceClient;
        this.userClient = userClient;
    }

    public Booking start(BookingStartRequest bookingStartRequest) {
        User client = userClient.getUserById(bookingStartRequest.clientId());

        List<ProductItem> productItems = new ArrayList<>();
        bookingStartRequest.products().forEach(productItemRequest -> {
            Product service = serviceClient.getProductById(productItemRequest.productId());
            ProductItem productItem = new ProductItem(service.getId(), service.getName(), service.getPrice(), productItemRequest.quantity());
            productItems.add(productItem);
        });

        List<Room> rooms = new ArrayList<>();
        bookingStartRequest.roomsIds().forEach(roomId -> {
            RoomClientResponse roomClientResponse = roomClient.getRoomById(roomId);
            rooms.add(new Room(roomClientResponse.getId(), roomClientResponse.getCapacity(), roomClientResponse.getDailyRate(), roomClientResponse.getBeds(), roomClientResponse.getFurniture()));
        });

        Booking booking = Booking.start(client, bookingStartRequest.peopleAmount(), rooms,
                productItems, bookingStartRequest.checkIn(), bookingStartRequest.checkOut());
        return bookingRepository.save(booking);
    }

    public Booking confirm(Long id) {
        Booking booking = bookingRepository.findById(id).orElseThrow(NotFoundException::new);

        booking.getRooms().forEach(room -> {
            RoomReserveRequest roomReserveRequest = new RoomReserveRequest(room.getRoomId(), booking.getClientId(), booking.getCheckIn(), booking.getCheckOut());
            roomClient.schedule(roomReserveRequest);
        });

        booking.confirm();
        mailer.sendReservationConfirmation(booking.getClientName(), booking.getClientEmail(), booking.getCheckIn(), booking.getCheckOut());

        return bookingRepository.save(booking);
    }

    public Booking findById(Long id) {
        return bookingRepository.findById(id).orElseThrow(br.com.fiap.booking.exception.NotFoundException::new);
    }
}
