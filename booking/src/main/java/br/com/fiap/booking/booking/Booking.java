package br.com.fiap.booking.booking;

import br.com.fiap.booking.product.ProductItem;
import br.com.fiap.booking.room.Room;
import br.com.fiap.booking.user.User;
import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.ArrayList;
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

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Room> rooms = new ArrayList<>();

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<ProductItem> productItems = new ArrayList<>();

    private LocalDate checkIn;
    private LocalDate checkOut;

    public Booking() {
    }

    public static Booking start(User client, Integer peopleAmount, List<Room> rooms, List<ProductItem> productItems, LocalDate checkIn, LocalDate checkOut) {
        Booking booking = new Booking();
        booking.active = false;
        booking.clientId = client.getId();
        booking.clientName = client.getName();
        booking.clientEmail = client.getEmail();
        booking.peopleAmount = peopleAmount;
        booking.rooms = rooms;
        booking.productItems = productItems;
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

    public List<Room> getRooms() {
        return rooms;
    }

    public List<ProductItem> getProductItems() {
        return productItems;
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
