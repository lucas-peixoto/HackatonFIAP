package br.com.fiap.services.product;

import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/products")
public class ProductController {

    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @PostMapping
    ResponseEntity<ProductResponse> create(@Valid @RequestBody ProductCreateRequest productCreateRequest) {
        Product product = productService.create(productCreateRequest);

        return ResponseEntity.ok(new ProductResponse(product));
    }

    @GetMapping("/{id}")
    ResponseEntity<ProductResponse> findById(@PathVariable Long id) {
        Product product = productService.findById(id);

        return ResponseEntity.ok(new ProductResponse(product));
    }

    @GetMapping
    ResponseEntity<Page<ProductResponse>> findAll(Pageable pageable) {
        Page<Product> products = productService.findAll(pageable);

        return ResponseEntity.ok(products.map(ProductResponse::new));
    }

    @PutMapping("/{id}")
    ResponseEntity<ProductResponse> update(@PathVariable Long id, @Valid @RequestBody ProductUpdateRequest productUpdateRequest) {
        Product product = productService.update(id, productUpdateRequest);

        return ResponseEntity.ok(new ProductResponse(product));
    }

    @DeleteMapping("/{id}")
    ResponseEntity<Void> delete(@PathVariable Long id) {
        productService.delete(id);

        return ResponseEntity.noContent().build();
    }
}
