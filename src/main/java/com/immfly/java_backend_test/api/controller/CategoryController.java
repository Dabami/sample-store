package com.immfly.java_backend_test.api.controller;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.immfly.java_backend_test.api.dto.CategoryInputDto;
import com.immfly.java_backend_test.api.dto.CategoryOutputDto;
import com.immfly.java_backend_test.api.mapper.CategoryMapper;
import com.immfly.java_backend_test.domain.entity.Category;
import com.immfly.java_backend_test.service.CategoryService;

import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("api/categories")
@Tag(name = "Categories")
public class CategoryController {

    @Autowired
    CategoryService categoryService;

    @GetMapping
    public ResponseEntity<Iterable<CategoryOutputDto>> getCategories() {
        return ResponseEntity.ok(CategoryMapper.toDtoList(categoryService.getAllCategories()));
    }

    @PostMapping
    public ResponseEntity<CategoryOutputDto> saveCategory(@RequestBody CategoryInputDto categoryInputDto) {
        Category parentCategory = null;
        if (categoryInputDto.getParentCategoryId() != null) {
            parentCategory = categoryService.getById(UUID.fromString(categoryInputDto.getParentCategoryId()));
        }
        Category newCategory = Category.builder()
                .name(categoryInputDto.getName())
                .parentCategory(parentCategory)
                .build();
        return ResponseEntity.ok(CategoryMapper.toDto(categoryService.saveCategory(newCategory)));
    }
}
