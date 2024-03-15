package br.com.fiap.booking.product;

import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class ProductItemTest {

    @Test
    void shouldCalculateSubtotalCorrectly() {
        ProductItem productItem = new ProductItem(1L, "Test Product", new BigDecimal("10.00"), 2);
        BigDecimal expectedSubtotal = new BigDecimal("20.00");
        assertEquals(expectedSubtotal, productItem.getSubtotal());
    }

    @Test
    void shouldHandleZeroQuantity() {
        ProductItem productItem = new ProductItem(1L, "Test Product", new BigDecimal("10.00"), 0);
        BigDecimal expectedSubtotal = BigDecimal.ZERO;
        assertEquals(expectedSubtotal.compareTo(productItem.getSubtotal()), 0);
    }

    @Test
    void shouldHandleNullQuantity() {
        ProductItem productItem = new ProductItem(1L, "Test Product", new BigDecimal("10.00"), null);
        assertThrows(NullPointerException.class, productItem::getSubtotal);
    }

    @Test
    void shouldHandleNullPrice() {
        ProductItem productItem = new ProductItem(1L, "Test Product", null, 2);
        assertThrows(NullPointerException.class, productItem::getSubtotal);
    }
}