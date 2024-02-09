package com.api.boardcamp.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.api.boardcamp.models.entities.Category;
import com.api.boardcamp.models.dto.CategoryDTO;
import com.api.boardcamp.services.CategoryService;
import com.api.boardcamp.exceptions.CategoryAlreadyExistsException;
import jakarta.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/categories")
public class CategoryController {

    final CategoryService categoryService;

    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping
    public ResponseEntity<List<CategoryDTO>> getCategory() {
        List<Category> categories = categoryService.getAllCategories();
        List<CategoryDTO> categoryDTOs = categories.stream()
                .map(category -> {
                    CategoryDTO dto = new CategoryDTO();
                    dto.setName(category.getName());
                    return dto;
                })
                .collect(Collectors.toList());
        return ResponseEntity.ok(categoryDTOs);
    }

    @PostMapping
    public ResponseEntity<Void> postCategory(@Valid @RequestBody CategoryDTO categoryDTO) {
        try {
            categoryService.addCategory(categoryDTO);
            return ResponseEntity.status(HttpStatus.CREATED).build();
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().build();
        } catch (CategoryAlreadyExistsException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        }
    }
}