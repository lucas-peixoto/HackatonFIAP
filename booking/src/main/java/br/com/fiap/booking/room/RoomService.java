package br.com.fiap.booking.room;

import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

@Service
public class RoomService {

    private final RoomClient roomClient;

    public RoomService(RoomClient roomClient) {
        this.roomClient = roomClient;
    }

    public Page<Room> search(RoomSearchRequest roomSearchRequest) {
        return roomClient.search(roomSearchRequest);
    }
}
