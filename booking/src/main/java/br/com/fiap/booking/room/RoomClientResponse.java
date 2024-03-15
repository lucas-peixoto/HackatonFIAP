package br.com.fiap.booking.room;

import java.math.BigDecimal;
import java.util.Map;

public class RoomClientResponse {

    private Long id;
    private Integer capacity;
    private BigDecimal dailyRate;
    private Map<Integer, String> beds;
    private Map<Integer, String> furniture;

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
}
