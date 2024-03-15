package br.com.fiap.booking.booking;

import jakarta.validation.constraints.*;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

public record BookingCreateRequest(@NotNull Long clientId, @Min(1) Integer peopleAmount, @NotNull List<Long> roomsIds,
                                   @NotNull List<Long> servicesIds, @NotNull @Future LocalDate checkIn,
                                   @NotNull @Future LocalDate checkOut) {
}