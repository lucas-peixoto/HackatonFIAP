package br.com.fiap.services.product;

import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.assertj.core.api.Assertions.assertThat;

class ProductCreateRequestTest {

    @Test
    void should_create_product_with_given_values() {
        ProductCreateRequest request = new ProductCreateRequest("Product one", new BigDecimal("100.00"), ProductType.SERVICE);
        Product product = request.toModel();

        assertThat(request.name()).isEqualTo(product.getName());
        assertThat(request.price()).isEqualTo(product.getPrice());
        assertThat(request.type()).isEqualTo(product.getType());
    }

    @Test
    void should_create_product_with_different_values() {
        ProductCreateRequest request = new ProductCreateRequest("Product Two", new BigDecimal("200.00"), ProductType.ITEM);
        Product product = request.toModel();

        assertThat(request.name()).isEqualTo(product.getName());
        assertThat(request.price()).isEqualTo(product.getPrice());
        assertThat(request.type()).isEqualTo(product.getType());
    }
}