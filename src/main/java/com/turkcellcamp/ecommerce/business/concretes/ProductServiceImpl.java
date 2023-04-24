package com.turkcellcamp.ecommerce.business.concretes;

import com.turkcellcamp.ecommerce.business.abstracts.CategoryService;
import com.turkcellcamp.ecommerce.business.abstracts.ProductService;
import com.turkcellcamp.ecommerce.business.dto.requests.create.CreateProductRequest;
import com.turkcellcamp.ecommerce.business.dto.requests.update.UpdateProductRequest;
import com.turkcellcamp.ecommerce.business.dto.responses.create.CreateProductResponse;
import com.turkcellcamp.ecommerce.business.dto.responses.get.GetAllProductsResponse;
import com.turkcellcamp.ecommerce.business.dto.responses.get.GetProductResponse;
import com.turkcellcamp.ecommerce.business.dto.responses.update.UpdateProductResponse;
import com.turkcellcamp.ecommerce.entities.Category;
import com.turkcellcamp.ecommerce.entities.Product;
import com.turkcellcamp.ecommerce.repository.ProductRepository;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final CategoryService categoryService;
    private final ProductRepository productRepository;
    private final ModelMapper mapper;

    @Override
    public List<GetAllProductsResponse> getAll() {
        List<Product> productList = productRepository.findAll();
        List<GetAllProductsResponse> response = productList
                .stream()
                .map(product -> mapper.map(product, GetAllProductsResponse.class))
                .toList();

        return response;
    }

    @Override
    public GetProductResponse getById(int id) {
        checkIfProductExists(id);
        Optional<Product> product = productRepository.findById(id);
        GetProductResponse response = mapper.map(product, GetProductResponse.class);
        return response;
    }

    @Override
    public CreateProductResponse add(CreateProductRequest request) {
        Product product = mapper.map(request, Product.class);
        validateProduct(product);
        categoryService.getAll()
                .stream()
                .map(getAllCategoriesResponse -> mapper.map(getAllCategoriesResponse, Category.class))
                .filter(category -> request.getCategories().contains(category.getId()))
                .forEach(category -> product.getCategories().add(category));

        product.setId(0);
        Product createdProduct = productRepository.save(product);
        CreateProductResponse response = mapper.map(createdProduct, CreateProductResponse.class);
        return response;
    }

    @Override
    public UpdateProductResponse update(int id, UpdateProductRequest request) {
        checkIfProductExists(id);
        Product product = mapper.map(request, Product.class);
        validateProduct(product);
        product.setId(id);
        Product updatedProduct = productRepository.save(product);
        UpdateProductResponse response = mapper.map(updatedProduct, UpdateProductResponse.class);
        return response;
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
