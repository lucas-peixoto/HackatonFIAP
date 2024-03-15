package br.com.fiap.room.roomSchedule;

import br.com.fiap.room.room.Room;
import br.com.fiap.room.room.RoomRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.testcontainers.service.connection.ServiceConnection;
import org.testcontainers.containers.MySQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import java.math.BigDecimal;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertTrue;

@Testcontainers
@SpringBootTest
class RoomScheduleRepositoryTest {

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
    public void roomIsAvailablewhenBookingExistsButNotOverlapping() {
        LocalDate checkIn = LocalDate.now();
        LocalDate checkOut = checkIn.plusDays(2);

        Room room = roomRepository.save(new Room(2, BigDecimal.valueOf(50L), null, null));
        roomRepository.save(room);

        RoomSchedule roomSchedule = new RoomSchedule(room, 1L, checkIn, checkOut);
        roomScheduleRepository.save(roomSchedule);

        boolean isAvailable = roomScheduleRepository.existsByRoom_IdAndCheckInBetweenAndCheckOutBetween(room.getId(), checkIn.plusDays(1), checkOut, checkIn.plusDays(1), checkOut);

        assertTrue(isAvailable);
    }
}