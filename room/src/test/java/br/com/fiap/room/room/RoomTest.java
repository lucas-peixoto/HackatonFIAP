package br.com.fiap.room.room;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class RoomTest {

    private Room room;
    private RoomUpdateRequest roomUpdateRequest;

    @BeforeEach
    void setUp() {
        room = new Room();
        roomUpdateRequest = mock(RoomUpdateRequest.class);
    }

    @Test
    void shouldUpdateRoomDetails() {
        Integer capacity = 2;
        BigDecimal dailyRate = new BigDecimal("100.00");
        Map<Integer, String> beds = new HashMap<>() {{
            put(1, "Single");
        }};
        Map<Integer, String> furniture = new HashMap<>() {{
            put(1, "Table");
        }};

        when(roomUpdateRequest.capacity()).thenReturn(capacity);
        when(roomUpdateRequest.dailyRate()).thenReturn(dailyRate);
        when(roomUpdateRequest.beds()).thenReturn(beds);
        when(roomUpdateRequest.furniture()).thenReturn(furniture);

        room.update(roomUpdateRequest);

        assertEquals(capacity, room.getCapacity());
        assertEquals(dailyRate, room.getDailyRate());
        assertEquals(beds, room.getBeds());
        assertEquals(furniture, room.getFurniture());
    }
}