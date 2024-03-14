package br.com.fiap.booking.booking;

import br.com.fiap.booking.mailer.MailService;
import br.com.fiap.booking.room.RoomClient;
import br.com.fiap.booking.room.RoomReserveRequest;
import br.com.fiap.booking.user.User;
import br.com.fiap.booking.user.UserClient;
import jakarta.ws.rs.NotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookingService {

    private final BookingRepository bookingRepository;
    private final MailService mailer;
    private final RoomClient roomClient;
    private final UserClient userClient;

    public BookingService(BookingRepository bookingRepository, MailService mailer, RoomClient roomClient, UserClient userClient) {
        this.bookingRepository = bookingRepository;
        this.mailer = mailer;
        this.roomClient = roomClient;
        this.userClient = userClient;
    }

    public Booking start(BookingStartRequest bookingStartRequest) {
        User client = userClient.getUserById(bookingStartRequest.clientId());
        Booking booking = Booking.start(client, bookingStartRequest.peopleAmount(), bookingStartRequest.roomsIds(), bookingStartRequest.servicesIds().values().stream().toList(),
                bookingStartRequest.checkIn(), bookingStartRequest.checkOut());
        return bookingRepository.save(booking);
    }

    public Booking confirm(Long id) {
        Booking booking = bookingRepository.findById(id).orElseThrow(NotFoundException::new);

        booking.getRoomsIds().forEach(roomId -> {
            RoomReserveRequest roomReserveRequest = new RoomReserveRequest(roomId, booking.getClientId(), booking.getCheckIn(), booking.getCheckOut());
            roomClient.schedule(roomReserveRequest);
        });

        booking.confirm();
        mailer.sendReservationConfirmation(booking.getClientName(), booking.getClientEmail(), booking.getCheckIn(), booking.getCheckOut());

        return bookingRepository.save(booking);
    }
}
