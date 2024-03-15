package br.com.fiap.services.product;

import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.assertj.core.api.Assertions.assertThat;

class ProductResponseTest {

    @Test
    void should_create_product_response_with_given_values() {
        Product product = new Product("Product One", new BigDecimal("100.00"), ProductType.SERVICE);
        product.setId(1L);
        ProductResponse response = new ProductResponse(product);

        assertThat(response.id()).isEqualTo(product.getId());
        assertThat(response.name()).isEqualTo(product.getName());
        assertThat(response.price()).isEqualTo(product.getPrice());
        assertThat(response.type()).isEqualTo(product.getType());
    }

    @Test
    void should_create_product_response_with_different_values() {
        Product product = new Product("Product Two", new BigDecimal("200.00"), ProductType.ITEM);
        product.setId(2L);
        ProductResponse response = new ProductResponse(product);

        assertThat(response.id()).isEqualTo(product.getId());
        assertThat(response.name()).isEqualTo(product.getName());
        assertThat(response.price()).isEqualTo(product.getPrice());
        assertThat(response.type()).isEqualTo(product.getType());
    }
}