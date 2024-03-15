package br.com.fiap.room.roomSchedule;

import br.com.fiap.room.exception.NotFoundException;
import br.com.fiap.room.room.Room;
import br.com.fiap.room.room.RoomRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class RoomScheduleServiceTest {

    private RoomRepository roomRepository;
    private RoomScheduleRepository roomScheduleRepository;

    private RoomScheduleService roomScheduleService;

    @BeforeEach
    void setUp() {
        roomRepository = mock(RoomRepository.class);
        roomScheduleRepository = mock(RoomScheduleRepository.class);

        roomScheduleService = new RoomScheduleService(roomRepository, roomScheduleRepository);
    }

    @Test
    public void roomScheduleIsCreatedWhenValidRequestIsGiven() {
        RoomScheduleRequest roomScheduleRequest = new RoomScheduleRequest(1L, 1L, LocalDate.now(), LocalDate.now().plusDays(2));
        when(roomRepository.findById(roomScheduleRequest.roomId())).thenReturn(Optional.of(mock(Room.class)));
        when(roomScheduleRepository.save(any())).thenAnswer(invocation -> invocation.getArgument(0));

        RoomSchedule roomSchedule = roomScheduleService.schedule(roomScheduleRequest);

        assertNotNull(roomSchedule);
        verify(roomScheduleRepository).save(any());
    }

    @Test
    public void exceptionIsThrownWhenInvalidRoomIdIsGiven() {
        RoomScheduleRequest roomScheduleRequest = new RoomScheduleRequest(999L, 1L, LocalDate.now(), LocalDate.now().plusDays(2));

        assertThrows(NotFoundException.class, () -> roomScheduleService.schedule(roomScheduleRequest));
    }
}