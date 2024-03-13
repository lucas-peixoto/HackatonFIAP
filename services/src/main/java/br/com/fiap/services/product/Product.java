package br.com.fiap.services.product;

import jakarta.persistence.*;

import java.math.BigDecimal;

@Entity
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private BigDecimal price;

    @Enumerated(EnumType.STRING)
    private ProductType type;

    public Product() {
    }

    public Product(String name, BigDecimal price, ProductType type) {
        this.name = name;
        this.price = price;
        this.type = type;
    }

    public void merge(ProductUpdateRequest productUpdateRequest) {
        this.name = productUpdateRequest.name();
        this.price = productUpdateRequest.price();
        this.type = productUpdateRequest.type();
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public ProductType getType() {
        return type;
    }
}
