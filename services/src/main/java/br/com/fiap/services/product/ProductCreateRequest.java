package br.com.fiap.services.product;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;

public record ProductCreateRequest(@NotBlank
                                   String name,
                                   @DecimalMin(value = "0.0", inclusive = false)
                                   @Digits(integer=3, fraction=2)
                                   BigDecimal price,
                                   @NotNull
                                   ProductType type) {

    public Product toModel() {
        return new Product(name, price, type);
    }
}
