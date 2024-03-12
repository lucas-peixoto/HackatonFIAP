package br.com.fiap.room.building;

import br.com.fiap.room.room.Room;
import jakarta.persistence.*;

import java.util.List;

@Entity
public class Building {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToMany
    private List<Room> rooms;
}
