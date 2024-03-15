package br.com.fiap.booking.room;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

@FeignClient("room")
public interface RoomClient {

    @PostMapping("/rooms/search")
    Page<RoomClientResponse> search(RoomSearchRequest roomSearchRequest);

    @PostMapping("/roomSchedule/reserve")
    RoomSchedule schedule(RoomReserveRequest roomReserveRequest);

    @GetMapping("/rooms/{roomId}")
    RoomClientResponse getRoomById(@PathVariable Long roomId);
}
