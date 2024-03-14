package br.com.fiap.room.roomSchedule;

import br.com.fiap.room.exception.NotFoundException;
import br.com.fiap.room.room.Room;
import br.com.fiap.room.room.RoomRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class RoomScheduleService {

    private final RoomRepository roomRepository;
    private final RoomScheduleRepository roomScheduleRepository;

    public RoomScheduleService(RoomRepository roomRepository, RoomScheduleRepository roomScheduleRepository) {
        this.roomRepository = roomRepository;
        this.roomScheduleRepository = roomScheduleRepository;
    }

    public RoomSchedule schedule(RoomScheduleRequest roomScheduleRequest) {
        Room room = roomRepository.findById(roomScheduleRequest.roomId()).orElseThrow(NotFoundException::new);
        RoomSchedule roomSchedule = new RoomSchedule(room, roomScheduleRequest.clientId(), roomScheduleRequest.checkIn(), roomScheduleRequest.checkOut());
        return roomScheduleRepository.save(roomSchedule);
    }

    public boolean isAvailable(Long roomId, LocalDate checkIn, LocalDate checkOut) {
        return roomScheduleRepository.isAvailable(roomId, checkIn, checkOut);
    }
}
