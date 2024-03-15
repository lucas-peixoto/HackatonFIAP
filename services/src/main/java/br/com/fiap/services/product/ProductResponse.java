package br.com.fiap.services.product;

import java.math.BigDecimal;

public record ProductResponse(Long id, String name, BigDecimal price, ProductType type) {

    public ProductResponse(Product product) {
        this(product.getId(), product.getName(), product.getPrice(), product.getType());
    }
}
