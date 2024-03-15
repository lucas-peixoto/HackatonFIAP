package br.com.fiap.booking.booking;

import br.com.fiap.booking.product.ProductItemResponse;
import br.com.fiap.booking.room.RoomResponse;

import java.time.LocalDate;
import java.util.List;

public record BookingResponse(Long id, Long clientId, Integer peopleAmount, List<RoomResponse> rooms,
                              List<ProductItemResponse> productItems, LocalDate checkIn, LocalDate checkOut,
                              boolean active) {
    public BookingResponse(Booking booking) {
        this(booking.getId(), booking.getClientId(), booking.getPeopleAmount(), booking.getRooms().stream().map(RoomResponse::new).toList(), booking.getProductItems().stream().map(ProductItemResponse::new).toList(), booking.getCheckIn(), booking.getCheckOut(), booking.isActive());
    }
}