package com.turkcellcamp.ecommerce.business.concretes;

import com.turkcellcamp.ecommerce.business.abstracts.ProductService;
import com.turkcellcamp.ecommerce.entities.Product;
import com.turkcellcamp.ecommerce.repository.ProductRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;

    @Override
    public List<Product> getAll() {
        return productRepository.findAll();
    }

    @Override
    public Product getById(int id) {
        checkIfProductExists(id);
        return productRepository.findById(id).get();
    }

    @Override
    public Product add(Product product) {
        validateProduct(product);
        return productRepository.save(product);
    }

    @Override
    public Product update(int id, Product product) {
        checkIfProductExists(id);
        validateProduct(product);
        product.setId(id);
        return productRepository.save(product);
    }

    @Override
    public void deleteById(int id) {
        checkIfProductExists(id);
        productRepository.deleteById(id);
    }

    private void checkIfProductExists(int id) {
        if (!productRepository.existsById(id)) {
            throw new IllegalArgumentException("Product does not exists with id: " + id);
        }
    }

    private void validateProduct(Product product) {
        checkIfUnitPriceValid(product);
        checkIfQuantityValid(product);
        checkIfDescriptionValid(product);
    }

    private void checkIfUnitPriceValid(Product product) {
        if (product.getUnitPrice() <= 0) {
            throw new IllegalArgumentException("Price cannot be less than or equal to 0");
        }
    }

    private void checkIfQuantityValid(Product product) {
        if (product.getQuantity() < 0) {
            throw new IllegalArgumentException("Quantity cannot be less than 0");
        }
    }

    private void checkIfDescriptionValid(Product product) {
        if (product.getDescription().length() < 10 || product.getDescription().length() > 50 ) {
            throw new IllegalArgumentException("Description length must be between 10 and 50 characters");
        }
    }
}
