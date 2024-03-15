package br.com.fiap.booking.booking;

import br.com.fiap.booking.exception.NotFoundException;
import br.com.fiap.booking.mailer.MailService;
import br.com.fiap.booking.product.*;
import br.com.fiap.booking.room.RoomClient;
import br.com.fiap.booking.room.RoomClientResponse;
import br.com.fiap.booking.user.User;
import br.com.fiap.booking.user.UserClient;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class BookingServiceTest {

    private BookingRepository bookingRepository;
    private MailService mailer;
    private RoomClient roomClient;
    private ProductClient serviceClient;
    private UserClient userClient;

    private BookingService bookingService;

    @BeforeEach
    void setUp() {
        bookingRepository = mock(BookingRepository.class);
        mailer = mock(MailService.class);
        roomClient = mock(RoomClient.class);
        serviceClient = mock(ProductClient.class);
        userClient = mock(UserClient.class);

        bookingService = new BookingService(bookingRepository, mailer, roomClient, serviceClient, userClient);
    }

    @Test
    void startShouldCreateAndSaveBooking() {
        when(userClient.getUserById(anyLong())).thenReturn(new User());
        when(serviceClient.getProductById(anyLong())).thenReturn(new Product());
        when(roomClient.getRoomById(anyLong())).thenReturn(new RoomClientResponse());
        when(bookingRepository.save(any(Booking.class))).thenReturn(new Booking());

        BookingStartRequest request = new BookingStartRequest(1L, 1, List.of(1L, 2L), List.of(new ProductItemRequest(1L, 2)), LocalDate.now(), LocalDate.now().plusDays(1));
        Booking booking = bookingService.start(request);

        assertNotNull(booking);
        verify(bookingRepository).save(any(Booking.class));
    }

    @Test
    void confirmShouldUpdateAndSaveBooking() {
        User user = new User(1L, "John Doe", "jd@mail.com");
        Booking booking = Booking.start(user, 1, List.of(), List.of(), LocalDate.now(), LocalDate.now().plusDays(1));
        when(bookingRepository.findById(anyLong())).thenReturn(Optional.of(booking));
        when(bookingRepository.save(any(Booking.class))).thenReturn(booking);

        Booking confirmedBooking = bookingService.confirm(1L);

        assertTrue(confirmedBooking.isActive());
        verify(bookingRepository).save(any(Booking.class));
        verify(mailer).sendReservationConfirmation(anyString(), anyString(), any(LocalDate.class), any(LocalDate.class));
    }

    @Test
    void confirmShouldThrowNotFoundExceptionWhenBookingNotFound() {
        when(bookingRepository.findById(anyLong())).thenReturn(Optional.empty());

        assertThrows(NotFoundException.class, () -> bookingService.confirm(1L));
    }

    @Test
    void findByIdShouldReturnBooking() {
        Booking booking = new Booking();
        when(bookingRepository.findById(anyLong())).thenReturn(Optional.of(booking));

        Booking foundBooking = bookingService.findById(1L);

        assertEquals(booking, foundBooking);
    }

    @Test
    void findByIdShouldThrowNotFoundExceptionWhenBookingNotFound() {
        when(bookingRepository.findById(anyLong())).thenReturn(Optional.empty());

        assertThrows(NotFoundException.class, () -> bookingService.findById(0L));
    }
}