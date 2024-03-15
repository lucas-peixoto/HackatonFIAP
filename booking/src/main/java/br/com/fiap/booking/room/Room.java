package br.com.fiap.booking.room;

import jakarta.persistence.*;

import java.math.BigDecimal;
import java.util.Map;

@Entity
public class Room {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long roomId;
    private Integer capacity;
    private BigDecimal dailyRate;

    @ElementCollection
    private Map<Integer, String> beds;

    @ElementCollection
    private Map<Integer, String> furniture;

    public Room() {
    }

    public Room(Long roomId, Integer capacity, BigDecimal dailyRate, Map<Integer, String> beds, Map<Integer, String> furniture) {
        this.roomId = roomId;
        this.capacity = capacity;
        this.dailyRate = dailyRate;
        this.beds = beds;
        this.furniture = furniture;
    }

    public Long getId() {
        return id;
    }

    public Long getRoomId() {
        return roomId;
    }

    public Integer getCapacity() {
        return capacity;
    }

    public BigDecimal getDailyRate() {
        return dailyRate;
    }

    public void setDailyRate(BigDecimal dailyRate) {
        this.dailyRate = dailyRate;
    }

    public Map<Integer, String> getBeds() {
        return beds;
    }

    public Map<Integer, String> getFurniture() {
        return furniture;
    }
}
