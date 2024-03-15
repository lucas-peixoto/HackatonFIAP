package br.com.fiap.booking.booking;

import java.time.LocalDate;
import java.util.List;

public record BookingResponse(Long id, Long clientId, Integer peopleAmount, List<Long> roomsIds, List<Long> servicesIds,
                              LocalDate checkIn, LocalDate checkOut, boolean active) {
    public BookingResponse(Booking booking) {
        this(booking.getId(), booking.getClientId(), booking.getPeopleAmount(), booking.getRoomsIds(),
                booking.getServicesIds(), booking.getCheckIn(), booking.getCheckOut(), booking.isActive());
    }
}