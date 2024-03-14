package br.com.fiap.booking.room;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

@FeignClient("room")
public interface RoomClient {

    @PostMapping("/rooms/search")
//    @RequestMapping(method = RequestMethod.POST, path = "/rooms/search", consumes = "application/json")
    Page<Room> search(RoomSearchRequest roomSearchRequest);

    @PostMapping("/roomSchedule/reserve")
//    @RequestMapping(method = RequestMethod.POST, path = "/rooms/search", consumes = "application/json")
    RoomSchedule schedule(RoomReserveRequest roomReserveRequest);
}
