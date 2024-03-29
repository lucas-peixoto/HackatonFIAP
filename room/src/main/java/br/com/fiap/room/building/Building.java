package br.com.fiap.room.building;

import br.com.fiap.room.room.Room;
import jakarta.persistence.*;

import java.util.*;

@Entity
public class Building {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @OneToMany(cascade = CascadeType.ALL)
    private List<Room> rooms = new ArrayList<>();

    public Building() {
    }

    public Building(String name) {
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void addRoom(Room room) {
        this.rooms.add(room);
    }

    public List<Room> getRooms() {
        return rooms;
    }

    public void update(BuildingUpdateRequest buildingRequest) {
        this.name = buildingRequest.name();
    }
}
