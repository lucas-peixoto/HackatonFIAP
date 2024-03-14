package br.com.fiap.room.roomSchedule;

import java.time.LocalDate;

public record RoomScheduleRequest(Long roomId, Long clientId, LocalDate checkIn, LocalDate checkOut) {
}
