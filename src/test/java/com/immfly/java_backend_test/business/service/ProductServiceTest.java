
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

import com.immfly.java_backend_test.domain.entity.Product;
import com.immfly.java_backend_test.domain.repository.ProductRepository;

class ProductServiceTest {

    @InjectMocks
    private ProductService productService;

    @Mock
    private ProductRepository productRepository;

    public ProductServiceTest() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void getProductById_ShouldReturnProduct_WhenExists() {
        UUID productId = UUID.randomUUID();
        Product mockProduct = Product.builder().id(productId).build();

        when(productRepository.findById(productId)).thenReturn(Optional.of(mockProduct));

        Product result = productService.getProductById(productId);

        assertNotNull(result);
        assertEquals(mockProduct.getId(), result.getId());
        verify(productRepository, times(1)).findById(productId);
    }

    @Test
    void getProductById_ShouldThrowException_WhenNotExists() {
        UUID productId = UUID.randomUUID();

        when(productRepository.findById(productId)).thenReturn(Optional.empty());

        assertThrows(RuntimeException.class, () -> productService.getProductById(productId));
        verify(productRepository, times(1)).findById(productId);
    }
}
