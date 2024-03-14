package br.com.fiap.booking.room;

import java.math.BigDecimal;
import java.util.Map;

public record RoomResponse(Long id, Integer capacity, BigDecimal dailyRate, Map<Integer, String> beds,
                           Map<Integer, String> furniture) {
    public RoomResponse(Room room) {
        this(room.getId(), room.getCapacity(), room.getDailyRate(), room.getBeds(), room.getFurniture());
    }
}
