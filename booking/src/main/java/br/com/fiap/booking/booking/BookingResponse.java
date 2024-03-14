package br.com.fiap.booking.booking;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

public record BookingResponse(Long id, Long clientId, Integer peopleAmount, List<Long> roomsIds, List<Long> servicesIds,
                              LocalDate checkIn, LocalDate checkOut) {
}