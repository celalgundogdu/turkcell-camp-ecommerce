package com.turkcellcamp.ecommerce.business.abstracts;

import com.turkcellcamp.ecommerce.business.dto.requests.create.CreateProductRequest;
import com.turkcellcamp.ecommerce.business.dto.requests.update.UpdateProductRequest;
import com.turkcellcamp.ecommerce.business.dto.responses.create.CreateProductResponse;
import com.turkcellcamp.ecommerce.business.dto.responses.get.GetAllProductsResponse;
import com.turkcellcamp.ecommerce.business.dto.responses.get.GetProductResponse;
import com.turkcellcamp.ecommerce.business.dto.responses.update.UpdateProductResponse;
import com.turkcellcamp.ecommerce.entities.Product;

import java.util.List;

public interface ProductService {

    List<GetAllProductsResponse> getAll();

    GetProductResponse getById(int id);

    CreateProductResponse add(CreateProductRequest request);

    UpdateProductResponse update(int id, UpdateProductRequest request);

    void deleteById(int id);
}
