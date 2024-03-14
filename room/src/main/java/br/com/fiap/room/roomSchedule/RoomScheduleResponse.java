package br.com.fiap.room.roomSchedule;

import java.time.LocalDate;

public record RoomScheduleResponse(Long roomId, Long clientId, LocalDate checkIn, LocalDate checkOut) {
    public RoomScheduleResponse(RoomSchedule roomSchedule) {
        this(roomSchedule.getRoomId(), roomSchedule.getClientId(), roomSchedule.getCheckIn(), roomSchedule.getCheckOut());
    }
}
