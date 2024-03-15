package br.com.fiap.booking.room;

import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

public record RoomSearchRequest(@NotNull @Future LocalDate checkIn, @NotNull @Future LocalDate checkOut) {
}
