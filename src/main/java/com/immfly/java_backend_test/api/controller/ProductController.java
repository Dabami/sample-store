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

import com.immfly.java_backend_test.api.dto.ProductInputDto;
import com.immfly.java_backend_test.api.dto.ProductOutputDto;
import com.immfly.java_backend_test.api.mapper.ProductMapper;
import com.immfly.java_backend_test.business.service.CategoryService;
import com.immfly.java_backend_test.business.service.ProductService;
import com.immfly.java_backend_test.domain.entity.Product;

import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("api/products")
@Tag(name = "Products")
public class ProductController {

    @Autowired
    ProductService productService;
    @Autowired
    CategoryService categoryService;

    @GetMapping
    public ResponseEntity<List<ProductOutputDto>> getProducts() {
        return ResponseEntity.ok(ProductMapper.toOutputDtoList(productService.getAllProducts()));
    }

    @PostMapping
    public ResponseEntity<ProductOutputDto> saveProduct(@RequestBody ProductInputDto productInputDto) {
        Product newProduct = ProductMapper.fromInputDto(productInputDto);
        return ResponseEntity.ok(ProductMapper.toOutputDto(productService.saveProduct(newProduct, UUID.fromString(productInputDto.getCategoryId()))));
    }

}
