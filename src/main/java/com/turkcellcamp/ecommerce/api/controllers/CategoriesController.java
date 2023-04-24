package com.turkcellcamp.ecommerce.api.controllers;

import com.turkcellcamp.ecommerce.business.abstracts.CategoryService;
import com.turkcellcamp.ecommerce.business.dto.requests.create.CreateCategoryRequest;
import com.turkcellcamp.ecommerce.business.dto.requests.update.UpdateCategoryRequest;
import com.turkcellcamp.ecommerce.business.dto.responses.create.CreateCategoryResponse;
import com.turkcellcamp.ecommerce.business.dto.responses.get.GetAllCategoriesResponse;
import com.turkcellcamp.ecommerce.business.dto.responses.get.GetCategoryResponse;
import com.turkcellcamp.ecommerce.business.dto.responses.update.UpdateCategoryResponse;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/categories")
@AllArgsConstructor
public class CategoriesController {

    private final CategoryService categoryService;

    @GetMapping
    public List<GetAllCategoriesResponse> getAll() {
        return categoryService.getAll();
    }

    @GetMapping("/{id}")
    public GetCategoryResponse getById(@PathVariable int id) {
        return categoryService.getById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CreateCategoryResponse add(@RequestBody CreateCategoryRequest request) {
        return categoryService.add(request);
    }

    @PutMapping("/{id}")
    public UpdateCategoryResponse update(@PathVariable int id, @RequestBody UpdateCategoryRequest request) {
        return categoryService.update(id, request);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteById(@PathVariable int id) {
        categoryService.deleteById(id);
    }

}
