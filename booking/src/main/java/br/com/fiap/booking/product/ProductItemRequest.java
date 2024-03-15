package br.com.fiap.booking.product;

import jakarta.validation.constraints.NotNull;

public record ProductItemRequest(@NotNull Long productId, @NotNull Integer quantity) {
}
