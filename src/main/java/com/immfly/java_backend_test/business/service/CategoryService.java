package com.immfly.java_backend_test.business.service;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.immfly.java_backend_test.business.exception.CategoryNotFoundException;
import com.immfly.java_backend_test.domain.entity.Category;
import com.immfly.java_backend_test.domain.repository.CategoryRepository;

@Service
public class CategoryService {

    @Autowired
    CategoryRepository categoryRepository;

    public Iterable<Category> getAllCategories() {
        return categoryRepository.findAll();
    }

    public Category getCategoryById(UUID id) {
        return categoryRepository.findById(id).orElseThrow(() -> new CategoryNotFoundException("Category with id " + id + " not found."));
    }

    public Category saveCategory(Category category, UUID parentCategoryId) {
        if (parentCategoryId != null) {
            category.setParentCategory(getCategoryById(parentCategoryId));
        }
        return categoryRepository.save(category);
    }

}
