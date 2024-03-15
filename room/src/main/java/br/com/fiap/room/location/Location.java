package br.com.fiap.room.location;

import br.com.fiap.room.address.Address;
import br.com.fiap.room.building.Building;
import jakarta.persistence.*;

import java.util.*;

@Entity
public class Location {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private Address address;

    @ElementCollection
    private Map<Integer, String> amenities;

    @OneToMany
    private List<Building> buildings = new ArrayList<>();

    public Location() {
    }

    public Location(String name, Address address, Map<Integer, String> amenities) {
        this.name = name;
        this.address = address;
        this.amenities = amenities;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Address getAddress() {
        return address;
    }

    public Map<Integer, String> getAmenities() {
        return amenities;
    }

    public void addBuilding(Building building) {
        this.buildings.add(building);
    }

    public List<Building> getBuildings() {
        return buildings;
    }

    public void update(LocationUpdateRequest locationRequest) {
        this.name = locationRequest.name();
        this.address = new Address(locationRequest.address().street(), locationRequest.address().number(),
                locationRequest.address().city(), locationRequest.address().state(), locationRequest.address().cep());
        this.amenities = locationRequest.amenities();
    }
}
