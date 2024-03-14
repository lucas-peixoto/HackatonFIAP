package br.com.fiap.services.product;

import br.com.fiap.services.exception.NotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.math.BigDecimal;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;
import static org.mockito.Mockito.*;

class ProductServiceTest {

    private ProductRepository productRepository;
    private ProductService productService;

    @BeforeEach
    void setUp() {
        productRepository = mock(ProductRepository.class);
        productService = new ProductService(productRepository);
    }

    @Test
    void should_create_a_product() {
        ProductCreateRequest request = new ProductCreateRequest("Café da manhã", new BigDecimal("100.00"), ProductType.SERVICE);
        Product product = new Product(request.name(), request.price(), request.type());

        when(productRepository.save(any(Product.class))).thenReturn(product);
        Product createdProduct = productService.create(request);

        assertThat(request.name()).isEqualTo(createdProduct.getName());
        assertThat(request.price()).isEqualTo(createdProduct.getPrice());
        assertThat(request.type()).isEqualTo(createdProduct.getType());
    }

    @Test
    void should_find_productById() {
        Product productOne = new Product("Product number one", new BigDecimal("100.0"), ProductType.SERVICE);
        productOne.setId(1L);
        Product productTwo = new Product("Product number two", new BigDecimal("200.0"), ProductType.ITEM);
        productTwo.setId(2L);
        Product productThree = new Product("Product number trhee", new BigDecimal("300.0"), ProductType.SERVICE);
        productTwo.setId(3L);

        when(productRepository.findById(2L)).thenReturn(Optional.of(productTwo));
        Product foundProduct = productService.findById(2L);

        assertThat(foundProduct).isEqualTo(productTwo);
        assertThat(foundProduct).isNotEqualTo(productOne);
        assertThat(foundProduct).isNotEqualTo(productThree);
    }

    @Test
    void should_throw_not_found_exception_when_product_not_found() {
        when(productRepository.findById(1L)).thenReturn(Optional.empty());

        assertThatExceptionOfType(NotFoundException.class).isThrownBy(() -> {
            productService.findById(1L);
        }).withMessage("Product not found");
    }

    @Test
    void shouldFindAllProducts() {
        Page<Product> page = mock(Page.class);
        Pageable pageable = mock(Pageable.class);
        when(productRepository.findAll(pageable)).thenReturn(page);

        Page<Product> foundPage = productService.findAll(pageable);

        assertThat(foundPage).isNotNull();
        assertThat(foundPage).isEqualTo(page);
    }

    @Test
    void should_update_product() {
        Product product = new Product("Product one", new BigDecimal("100.00"), ProductType.SERVICE);
        ProductUpdateRequest request = new ProductUpdateRequest("Product two", new BigDecimal("200.00"), ProductType.ITEM);
        when(productRepository.findById(1L)).thenReturn(Optional.of(product));

        Product updatedProduct = productService.update(1L, request);

        assertThat(updatedProduct.getName()).isEqualTo(product.getName());
    }

    @Test
    void should_delete_product() {
        Product product = new Product("Product One", new BigDecimal("100.00"), ProductType.SERVICE);
        when(productRepository.getReferenceById(1L)).thenReturn(product);
        productService.delete(1L);

        verify(productRepository, times(1)).delete(product);
    }

    @Test
    void should_throw_not_found_exception_when_deleting_non_existing_product() {
        when(productRepository.getReferenceById(1L)).thenThrow(new NotFoundException("Product not found"));

        assertThatExceptionOfType(NotFoundException.class).isThrownBy(() -> {
            productService.findById(1L);
        }).withMessage("Product not found");
    }
}