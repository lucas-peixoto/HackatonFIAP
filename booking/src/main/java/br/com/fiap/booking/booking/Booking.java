package br.com.fiap.booking.booking;

import br.com.fiap.booking.user.User;
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
    private String clientName;
    private String clientEmail;
    private Integer peopleAmount;

    @ElementCollection
    private List<Long> roomsIds;

    @ElementCollection
    private List<Long> servicesIds;

    private LocalDate checkIn;
    private LocalDate checkOut;

    public Booking() {
    }

    public static Booking start(User client, Integer peopleAmount, List<Long> roomsIds, List<Long> servicesIds, LocalDate checkIn, LocalDate checkOut) {
        Booking booking = new Booking();
        booking.active = false;
        booking.clientId = client.getId();
        booking.clientName = client.getName();
        booking.clientEmail = client.getEmail();
        booking.peopleAmount = peopleAmount;
        booking.roomsIds = roomsIds;
        booking.servicesIds = servicesIds;
        booking.checkIn = checkIn;
        booking.checkOut = checkOut;

        return booking;
    }

    public Long getId() {
        return id;
    }

    public boolean isActive() {
        return active;
    }

    public Long getClientId() {
        return clientId;
    }

    public String getClientName() {
        return clientName;
    }

    public String getClientEmail() {
        return clientEmail;
    }

    public Integer getPeopleAmount() {
        return peopleAmount;
    }

    public List<Long> getRoomsIds() {
        return roomsIds;
    }

    public List<Long> getServicesIds() {
        return servicesIds;
    }

    public LocalDate getCheckIn() {
        return checkIn;
    }

    public LocalDate getCheckOut() {
        return checkOut;
    }

    public void confirm() {
        this.active = true;
    }
}
