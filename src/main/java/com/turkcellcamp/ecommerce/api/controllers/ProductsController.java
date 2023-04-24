package com.turkcellcamp.ecommerce.api.controllers;

import com.turkcellcamp.ecommerce.business.abstracts.ProductService;
import com.turkcellcamp.ecommerce.business.dto.requests.create.CreateProductRequest;
import com.turkcellcamp.ecommerce.business.dto.requests.update.UpdateProductRequest;
import com.turkcellcamp.ecommerce.business.dto.responses.create.CreateProductResponse;
import com.turkcellcamp.ecommerce.business.dto.responses.get.GetAllProductsResponse;
import com.turkcellcamp.ecommerce.business.dto.responses.get.GetProductResponse;
import com.turkcellcamp.ecommerce.business.dto.responses.update.UpdateProductResponse;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/api/products")
public class ProductsController {

    private final ProductService productService;

    @GetMapping
    public List<GetAllProductsResponse> getAll() {
        return productService.getAll();
    }

    @GetMapping("/{id}")
    public GetProductResponse getById(@PathVariable int id) {
        return productService.getById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CreateProductResponse add(@RequestBody CreateProductRequest request) {
        return productService.add(request);
    }

    @PutMapping("/{id}")
    public UpdateProductResponse update(@PathVariable int id, @RequestBody UpdateProductRequest request) {
        return productService.update(id, request);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteById(@PathVariable int id) {
        productService.deleteById(id);
    }
}
