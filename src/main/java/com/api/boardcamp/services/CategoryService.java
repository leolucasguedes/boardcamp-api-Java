package com.api.boardcamp.services;

import org.springframework.stereotype.Service;
import com.api.boardcamp.models.entities.Category;
import com.api.boardcamp.models.dto.CategoryDTO;
import com.api.boardcamp.repositories.CategoryRepository;
import com.api.boardcamp.exceptions.CategoryAlreadyExistsException;
import java.util.List;

@Service
public class CategoryService {

    final CategoryRepository categoryRepository;

    public CategoryService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    public List<Category> getAllCategories() {
        return categoryRepository.findAll();
    }

    public void addCategory(CategoryDTO categoryDTO) {
        Category category = new Category();
        category.setName(categoryDTO.getName());
        Category existingCategory = categoryRepository.findByName(categoryDTO.getName());
        if (existingCategory != null) {
            throw new CategoryAlreadyExistsException("Category already exists");
        }

        categoryRepository.save(category);
    }
}