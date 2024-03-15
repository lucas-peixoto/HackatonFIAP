package br.com.fiap.booking.product;

import java.math.BigDecimal;

public record ProductItemResponse(Long id, Long productId, String name, BigDecimal price, Integer quantity) {
    public ProductItemResponse(ProductItem productItem) {
        this(productItem.getId(), productItem.getProductId(), productItem.getName(), productItem.getPrice(), productItem.getQuantity());
    }
}