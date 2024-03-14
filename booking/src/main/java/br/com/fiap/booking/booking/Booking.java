package br.com.fiap.booking.booking;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.List;

@Entity
public class Booking {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private boolean active;

    private Long clientId;
    private Integer peopleAmount;

    @ElementCollection
    private List<Long> roomsIds;

    @ElementCollection
    private List<Long> servicesIds;

    private LocalDate checkIn;
    private LocalDate checkOut;

    public Booking() {
    }

    public static Booking start(Long clientId, Integer peopleAmount, LocalDate checkIn, LocalDate checkOut) {
        Booking booking = new Booking();
        booking.active = false;
        booking.clientId = clientId;
        booking.peopleAmount = peopleAmount;
        booking.checkIn = checkIn;
        booking.checkOut = checkOut;

        return booking;
    }
}
