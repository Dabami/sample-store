package com.immfly.java_backend_test.business.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Optional;
import java.util.UUID;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.immfly.java_backend_test.domain.entity.Category;
import com.immfly.java_backend_test.domain.repository.CategoryRepository;

class CategoryServiceTest {

    @InjectMocks
    private CategoryService categoryService;

    @Mock
    private CategoryRepository categoryRepository;

    public CategoryServiceTest() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void getCategoryById_ShouldReturnCategory_WhenExists() {
        UUID categoryId = UUID.randomUUID();
        Category mockCategory = Category.builder().id(categoryId).build();

        when(categoryRepository.findById(categoryId)).thenReturn(Optional.of(mockCategory));

        Category result = categoryService.getCategoryById(categoryId);

        assertNotNull(result);
        assertEquals(mockCategory.getId(), result.getId());
        verify(categoryRepository, times(1)).findById(categoryId);
    }

    @Test
    void getCategoryById_ShouldThrowException_WhenNotExists() {
        UUID categoryId = UUID.randomUUID();

        when(categoryRepository.findById(categoryId)).thenReturn(Optional.empty());

        assertThrows(RuntimeException.class, () -> categoryService.getCategoryById(categoryId));
        verify(categoryRepository, times(1)).findById(categoryId);
    }
}
