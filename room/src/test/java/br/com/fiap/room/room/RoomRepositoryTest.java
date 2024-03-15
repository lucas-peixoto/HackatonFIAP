package br.com.fiap.room.room;

import br.com.fiap.room.roomSchedule.RoomSchedule;
import br.com.fiap.room.roomSchedule.RoomScheduleRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.testcontainers.service.connection.ServiceConnection;
import org.springframework.data.domain.*;
import org.testcontainers.containers.MySQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@Testcontainers
@SpringBootTest
class RoomRepositoryTest {

    @Container
    @ServiceConnection
    static MySQLContainer container = new MySQLContainer("mysql:8.3.0")
            .withDatabaseName("room")
            .withUsername("root")
            .withPassword("root");

    @Autowired
    private RoomRepository roomRepository;

    @Autowired
    private RoomScheduleRepository roomScheduleRepository;

    @BeforeEach
    void setUp() {
        roomScheduleRepository.deleteAll();
        roomRepository.deleteAll();
    }

    @Test
    void shouldFindAllAvailableRooms() {
        LocalDate checkIn = LocalDate.now();
        LocalDate checkOut = checkIn.plusDays(2);
        Pageable pageable = PageRequest.of(0, 10);

        Room room = roomRepository.save(new Room(2, BigDecimal.valueOf(50L), null, null));

        Page<Room> rooms = roomRepository.findAllAvailable(checkIn, checkOut, pageable);

        assertEquals(1, rooms.getTotalElements());
        assertEquals(room.getId(), rooms.getContent().getFirst().getId());
    }

    @Test
    void shouldNotFindRoomsWhenDatesOverlap() throws JsonProcessingException {
        LocalDate checkIn = LocalDate.now();
        LocalDate checkOut = checkIn.plusDays(2);
        Pageable pageable = PageRequest.of(0, 10);

        Room room = roomRepository.save(new Room(2, BigDecimal.valueOf(50L), null, null));
        Room room2 = roomRepository.save(new Room(1, BigDecimal.valueOf(75L), null, null));
        roomRepository.saveAll(List.of(room, room2));

        RoomSchedule roomSchedule = new RoomSchedule(room, 1L, checkIn, checkOut);
        roomScheduleRepository.save(roomSchedule);

        Page<Room> rooms = roomRepository.findAllAvailable(checkIn.plusDays(1), checkOut.plusDays(1), pageable);

        assertEquals(1, rooms.getTotalElements());
        assertEquals(room2.getId(), rooms.getContent().getFirst().getId());
    }
}