package br.com.fiap.room.roomSchedule;

import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RoomScheduleController {

    private final RoomScheduleService roomScheduleService;

    public RoomScheduleController(RoomScheduleService roomScheduleService) {
        this.roomScheduleService = roomScheduleService;
    }

//    @PostMapping("/rooms/schedule")
//    public ResponseEntity<RoomScheduleResponse> schedule(@Valid RoomScheduleRequest roomScheduleRequest) {
//        RoomSchedule roomSchedule = roomScheduleService.schedule(roomScheduleRequest);
//        return ResponseEntity.ok(new RoomScheduleResponse(roomSchedule));
//    }
}
