package br.com.fiap.booking.room;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.data.domain.Page;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class RoomServiceTest {

    private RoomClient roomClient;
    private RoomService roomService;

    @BeforeEach
    void setUp() {
        roomClient = mock(RoomClient.class);
        roomService = new RoomService(roomClient);
    }

    @Test
    void shouldSearchRooms() {
        Page<RoomClientResponse> empty = Page.empty();
        when(roomClient.search(any(RoomSearchRequest.class))).thenReturn(empty);

        Page<RoomClientResponse> rooms = roomService.search(mock(RoomSearchRequest.class));

        assertEquals(rooms, empty);
    }
}