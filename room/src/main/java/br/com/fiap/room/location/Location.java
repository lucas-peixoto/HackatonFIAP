package br.com.fiap.room.location;

import br.com.fiap.room.building.Building;
import jakarta.persistence.*;

import java.util.List;

@Entity
public class Location {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToMany
    private List<Building> buildings;
}
