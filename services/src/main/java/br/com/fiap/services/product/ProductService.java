package br.com.fiap.services.product;

import br.com.fiap.services.exception.NotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class ProductService {

    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public Product create(ProductResponse productResponse) {
        Product product = new Product(productResponse.name(), productResponse.price(), productResponse.type());

        return productRepository.save(product);
    }

    public Product findById(Long id) {
        return productRepository.findById(id).orElseThrow(() -> new NotFoundException("Product not found"));
    }

    public Page<Product> findAll(Pageable pageable) {
        return productRepository.findAll(pageable);
    }

    @Transactional
    public Product update(Long id, ProductUpdateRequest productUpdateRequest) {
        Product product = productRepository.findById(id).orElseThrow(() -> new NotFoundException("Product not found"));
        product.merge(productUpdateRequest);

        return product;
    }

    public void delete(Long id) {
        productRepository.deleteById(id);
    }
}
