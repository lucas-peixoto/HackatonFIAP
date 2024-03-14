package br.com.fiap.room.room;

import br.com.fiap.room.building.Building;
import br.com.fiap.room.building.BuildingRepository;
import br.com.fiap.room.exception.NotFoundException;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class RoomService {

    private final BuildingRepository buildingRepository;
    private final RoomRepository roomRepository;

    public RoomService(BuildingRepository buildingRepository, RoomRepository roomRepository) {
        this.buildingRepository = buildingRepository;
        this.roomRepository = roomRepository;
    }

    public Room create(Long id, RoomCreateRequest roomCreateRequest) {
        Building building = buildingRepository.findById(id).orElseThrow(NotFoundException::new);
        Room room = new Room(roomCreateRequest.capacity(), roomCreateRequest.dailyRate(), roomCreateRequest.beds(), roomCreateRequest.furniture());
        building.addRoom(room);

        return roomRepository.save(room);
    }

    public Room update(Long id, RoomUpdateRequest roomUpdateRequest) {
        Room room = roomRepository.findById(id).orElseThrow(NotFoundException::new);
        room.update(roomUpdateRequest);
        return roomRepository.save(room);
    }

    public Room findById(Long id) {
        return roomRepository.findById(id).orElseThrow(NotFoundException::new);
    }

    public Page<Room> findAll(Pageable pageable) {
        return roomRepository.findAll(pageable);
    }

    public Page<Room> search(RoomSearchRequest roomSearchRequest, Pageable pageable) {
        return roomRepository.findAllAvailable(roomSearchRequest.checkIn(), roomSearchRequest.checkOut(), pageable);
    }

    public void delete(Long id) {
        roomRepository.deleteById(id);
    }
}
