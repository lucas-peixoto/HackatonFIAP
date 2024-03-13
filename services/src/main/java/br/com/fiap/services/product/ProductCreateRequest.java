package br.com.fiap.services.product;

import jakarta.persistence.*;

import java.math.BigDecimal;

public record ProductCreateRequest(String name, BigDecimal price, ProductType type) {

    public Product toModel() {
        return new Product(name, price, type);
    }
}
