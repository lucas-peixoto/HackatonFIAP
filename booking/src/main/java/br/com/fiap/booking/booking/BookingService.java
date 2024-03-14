package br.com.fiap.booking.booking;

import jakarta.validation.Valid;
import org.springframework.stereotype.Service;

@Service
public class BookingService {

    private final BookingRepository bookingRepository;

    public BookingService(BookingRepository bookingRepository) {
        this.bookingRepository = bookingRepository;
    }

    public Booking start(BookingStartRequest bookingStartRequest) {
        Booking booking = Booking.start(bookingStartRequest.clientId(), bookingStartRequest.peopleAmount(),
                bookingStartRequest.checkIn(), bookingStartRequest.checkOut());
        return bookingRepository.save(booking);
    }
}
