package br.com.fiap.room.roomSchedule;

import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class RoomScheduleController {

    private final RoomScheduleService roomScheduleService;

    public RoomScheduleController(RoomScheduleService roomScheduleService) {
        this.roomScheduleService = roomScheduleService;
    }

    @PostMapping("/roomSchedule/reserve")
    public ResponseEntity<RoomScheduleResponse> schedule(@Valid @RequestBody RoomScheduleRequest roomScheduleRequest) {
        RoomSchedule roomSchedule = roomScheduleService.schedule(roomScheduleRequest);
        return ResponseEntity.ok(new RoomScheduleResponse(roomSchedule));
    }
}
