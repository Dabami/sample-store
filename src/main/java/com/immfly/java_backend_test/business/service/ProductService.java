package com.immfly.java_backend_test.business.service;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.immfly.java_backend_test.business.exception.ProductNotFoundException;
import com.immfly.java_backend_test.domain.entity.Product;
import com.immfly.java_backend_test.domain.repository.ProductRepository;

@Service
public class ProductService {

    @Autowired
    ProductRepository productRepository;
    @Autowired
    CategoryService categoryService;

    public Iterable<Product> getAllProducts() {
        return productRepository.findAll();
    }

    public Product getProductById(UUID id) {
        return productRepository.findById(id).orElseThrow(() -> new ProductNotFoundException("Product with id " + id + " not found."));
    }

    public Product saveProduct(Product product) {
        return productRepository.save(product);
    }

    public Product saveProduct(Product product, UUID categoryId) {
        product.setCategory(categoryService.getCategoryById(categoryId));
        return productRepository.save(product);
    }

}
