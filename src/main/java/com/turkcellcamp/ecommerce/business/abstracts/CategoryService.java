package com.turkcellcamp.ecommerce.business.abstracts;

import com.turkcellcamp.ecommerce.business.dto.requests.create.CreateCategoryRequest;
import com.turkcellcamp.ecommerce.business.dto.requests.update.UpdateCategoryRequest;
import com.turkcellcamp.ecommerce.business.dto.responses.create.CreateCategoryResponse;
import com.turkcellcamp.ecommerce.business.dto.responses.get.GetAllCategoriesResponse;
import com.turkcellcamp.ecommerce.business.dto.responses.get.GetCategoryResponse;
import com.turkcellcamp.ecommerce.business.dto.responses.update.UpdateCategoryResponse;

import java.util.List;

public interface CategoryService {

    List<GetAllCategoriesResponse> getAll();

    GetCategoryResponse getById(int id);

    CreateCategoryResponse add(CreateCategoryRequest request);

    UpdateCategoryResponse update(int id, UpdateCategoryRequest request);

    void deleteById(int id);
}
