package com.immfly.java_backend_test.service;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.immfly.java_backend_test.domain.entity.Category;
import com.immfly.java_backend_test.domain.repository.CategoryRepository;

@Service
public class CategoryService {

    @Autowired
    CategoryRepository categoryRepository;

    public Iterable<Category> getAllCategories() {
        return categoryRepository.findAll();
    }

    public Category getById(UUID id) {
        return categoryRepository.findById(id).get();
    }

    public Category saveCategory(Category category) {
        return categoryRepository.save(category);
    }

}
