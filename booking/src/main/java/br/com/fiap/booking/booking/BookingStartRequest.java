package br.com.fiap.booking.booking;

import br.com.fiap.booking.product.ProductItemRequest;
import jakarta.validation.constraints.*;

import java.time.LocalDate;
import java.util.List;

public record BookingStartRequest(@NotNull Long clientId, @Min(1) Integer peopleAmount,
                                  @NotNull List<Long> roomsIds, @NotNull List<ProductItemRequest> products,
                                  @NotNull @Future LocalDate checkIn,
                                  @NotNull @Future LocalDate checkOut) {
}