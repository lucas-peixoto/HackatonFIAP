package br.com.fiap.room.room;

import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class RoomController {

    private final RoomService roomService;

    public RoomController(RoomService roomService) {
        this.roomService = roomService;
    }

    @PostMapping("/building/{id}/rooms")
    public ResponseEntity<RoomResponse> create(@PathVariable Long id, @Valid RoomCreateRequest roomCreateRequest) {
        Room room = roomService.create(id, roomCreateRequest);
        return ResponseEntity.ok(new RoomResponse(room));
    }

    @PutMapping("/rooms/{id}")
    public ResponseEntity<RoomResponse> update(@PathVariable Long id, @Valid RoomUpdateRequest roomUpdateRequest) {
        Room room = roomService.update(id, roomUpdateRequest);
        return ResponseEntity.ok(new RoomResponse(room));
    }

    @GetMapping("/rooms/{id}")
    public ResponseEntity<RoomResponse> findById(@PathVariable Long id) {
        Room room = roomService.findById(id);
        return ResponseEntity.ok(new RoomResponse(room));
    }

    @GetMapping("/rooms")
    public ResponseEntity<Page<RoomResponse>> findAll(Pageable pageable) {
        Page<Room> rooms = roomService.findAll(pageable);
        return ResponseEntity.ok(rooms.map(RoomResponse::new));
    }

    @DeleteMapping("/rooms/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        roomService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
