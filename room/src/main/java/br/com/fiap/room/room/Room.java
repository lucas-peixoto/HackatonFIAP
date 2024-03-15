package br.com.fiap.room.room;

import jakarta.persistence.*;

import java.math.BigDecimal;
import java.util.Map;

@Entity
public class Room {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Integer capacity;
    private BigDecimal dailyRate;

    @ElementCollection
    private Map<Integer, String> beds;

    @ElementCollection
    private Map<Integer, String> furniture;

    public Room() {
    }

    public Room(Integer capacity, BigDecimal dailyRate, Map<Integer, String> beds, Map<Integer, String> furniture) {
        this.capacity = capacity;
        this.dailyRate = dailyRate;
        this.beds = beds;
        this.furniture = furniture;
    }

    public Long getId() {
        return id;
    }

    public Integer getCapacity() {
        return capacity;
    }

    public BigDecimal getDailyRate() {
        return dailyRate;
    }

    public Map<Integer, String> getBeds() {
        return beds;
    }

    public Map<Integer, String> getFurniture() {
        return furniture;
    }

    public void update(RoomUpdateRequest roomUpdateRequest) {
        this.capacity = roomUpdateRequest.capacity();
        this.dailyRate = roomUpdateRequest.dailyRate();
        this.beds = roomUpdateRequest.beds();
        this.furniture = roomUpdateRequest.furniture();
    }
}
