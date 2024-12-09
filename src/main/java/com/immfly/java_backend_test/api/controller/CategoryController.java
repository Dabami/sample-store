package com.immfly.java_backend_test.api.controller;

import java.util.List;
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
import com.immfly.java_backend_test.business.service.CategoryService;
import com.immfly.java_backend_test.domain.entity.Category;

import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("api/categories")
@Tag(name = "Categories")
public class CategoryController {

    @Autowired
    CategoryService categoryService;

    @GetMapping
    public ResponseEntity<List<CategoryOutputDto>> getCategories() {
        return ResponseEntity.ok(CategoryMapper.toOutputDtoList(categoryService.getAllCategories()));
    }

    @PostMapping
    public ResponseEntity<CategoryOutputDto> saveCategory(@RequestBody CategoryInputDto categoryInputDto) {
        Category newCategory = CategoryMapper.fromInputDto(categoryInputDto);
        return ResponseEntity.ok(CategoryMapper.toOutputDto(categoryService.saveCategory(newCategory, UUID.fromString(categoryInputDto.getParentCategoryId()))));
    }
}
