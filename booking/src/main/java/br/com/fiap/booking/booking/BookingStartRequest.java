package br.com.fiap.booking.booking;

import jakarta.validation.constraints.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

public record BookingStartRequest(@NotNull Long clientId, @Min(1) Integer peopleAmount,
                                  @NotNull List<Long> roomsIds, @NotNull Map<Integer, Long> servicesIds,
                                  @NotNull @Future LocalDate checkIn,
                                  @NotNull @Future LocalDate checkOut) {
}