package br.com.fiap.services.product;

import java.math.BigDecimal;

public record ProductUpdateRequest(String name, BigDecimal price, ProductType type) {
}
