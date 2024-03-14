package br.com.fiap.room.roomSchedule;

import br.com.fiap.room.room.Room;
import jakarta.persistence.*;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Entity
public class RoomSchedule {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Room room;

    private Long clientId;
    private LocalDate checkIn;
    private LocalDate checkOut;

    public RoomSchedule() {
    }

    public RoomSchedule(Room room, Long clientId, LocalDate checkIn, LocalDate checkOut) {
        this.room = room;
        this.clientId = clientId;
        this.checkIn = checkIn;
        this.checkOut = checkOut;
    }
}
