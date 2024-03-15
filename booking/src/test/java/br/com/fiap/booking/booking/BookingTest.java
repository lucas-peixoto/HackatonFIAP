package br.com.fiap.booking.booking;

import br.com.fiap.booking.product.ProductItem;
import br.com.fiap.booking.room.Room;
import br.com.fiap.booking.user.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class BookingTest {

    private Booking booking;
    private User client;
    private Room room;
    private ProductItem productItem;

    @BeforeEach
    void setUp() {
        client = new User();
        client.setId(1L);
        client.setName("Test User");
        client.setEmail("testuser@example.com");

        room = new Room();
        room.setDailyRate(BigDecimal.valueOf(100));

        productItem = new ProductItem(1L, "Test Product", BigDecimal.valueOf(50), 2);

        booking = Booking.start(client, 2, List.of(room), List.of(productItem), LocalDate.now(), LocalDate.now().plusDays(2));
    }

    @Test
    void bookingStartShouldInitializeFieldsCorrectly() {
        assertFalse(booking.isActive());
        assertEquals(client.getId(), booking.getClientId());
        assertEquals(client.getName(), booking.getClientName());
        assertEquals(client.getEmail(), booking.getClientEmail());
        assertEquals(2, booking.getPeopleAmount());
        assertEquals(List.of(room), booking.getRooms());
        assertEquals(List.of(productItem), booking.getProductItems());
        assertEquals(LocalDate.now(), booking.getCheckIn());
        assertEquals(LocalDate.now().plusDays(2), booking.getCheckOut());
    }

    @Test
    void getTotalShouldCalculateCorrectTotal() {
        BigDecimal expectedTotal = BigDecimal.valueOf(400);
        assertEquals(expectedTotal, booking.getTotal());
    }

    @Test
    void confirmShouldSetActiveToTrue() {
        booking.confirm();
        assertTrue(booking.isActive());
    }
}