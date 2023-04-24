package com.turkcellcamp.ecommerce.business.concretes;

import com.turkcellcamp.ecommerce.business.abstracts.CategoryService;
import com.turkcellcamp.ecommerce.business.dto.requests.create.CreateCategoryRequest;
import com.turkcellcamp.ecommerce.business.dto.requests.update.UpdateCategoryRequest;
import com.turkcellcamp.ecommerce.business.dto.responses.create.CreateCategoryResponse;
import com.turkcellcamp.ecommerce.business.dto.responses.get.GetAllCategoriesResponse;
import com.turkcellcamp.ecommerce.business.dto.responses.get.GetCategoryResponse;
import com.turkcellcamp.ecommerce.business.dto.responses.update.UpdateCategoryResponse;
import com.turkcellcamp.ecommerce.entities.Category;
import com.turkcellcamp.ecommerce.repository.CategoryRepository;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;
    private final ModelMapper mapper;

    @Override
    public List<GetAllCategoriesResponse> getAll() {
        List<Category> categoryList = categoryRepository.findAll();
        List<GetAllCategoriesResponse> response = categoryList
                .stream()
                .map(category -> mapper.map(category, GetAllCategoriesResponse.class))
                .toList();

        return response;
    }

    @Override
    public GetCategoryResponse getById(int id) {
        checkIfCategoryExistsById(id);
        Category category = categoryRepository.findById(id).orElseThrow();
        GetCategoryResponse response = mapper.map(category, GetCategoryResponse.class);
        return response;
    }

    @Override
    public CreateCategoryResponse add(CreateCategoryRequest request) {
        checkIfCategoryExistsByName(request.getName());
        Category category = mapper.map(request, Category.class);
        category.setId(0);
        Category createdCategory = categoryRepository.save(category);
        CreateCategoryResponse response = mapper.map(createdCategory, CreateCategoryResponse.class);
        return response;
    }

    @Override
    public UpdateCategoryResponse update(int id, UpdateCategoryRequest request) {
        checkIfCategoryExistsById(id);
        Category category = mapper.map(request, Category.class);
        category.setId(0);
        Category updatedCategory = categoryRepository.save(category);
        UpdateCategoryResponse response = mapper.map(updatedCategory, UpdateCategoryResponse.class);
        return response;
    }

    @Override
    public void deleteById(int id) {
        checkIfCategoryExistsById(id);
        categoryRepository.deleteById(id);
    }

    private void checkIfCategoryExistsById(int id) {
        if (!categoryRepository.existsById(id)) {
            throw new RuntimeException("Category does not exist");
        }
    }

    private void checkIfCategoryExistsByName(String name) {
        if (categoryRepository.existsByNameIgnoreCase(name)) {
            throw new RuntimeException("Category does not exist");
        }
    }
}
