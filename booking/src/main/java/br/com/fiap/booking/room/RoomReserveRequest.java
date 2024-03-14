package br.com.fiap.booking.room;

import java.time.LocalDate;

public record RoomReserveRequest(Long roomId, Long clientId, LocalDate checkIn, LocalDate checkOut) {
}
