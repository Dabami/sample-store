package com.immfly.java_backend_test.api.mapper;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import com.immfly.java_backend_test.api.dto.ProductInputDto;
import com.immfly.java_backend_test.api.dto.ProductOutputDto;
import com.immfly.java_backend_test.domain.entity.Product;

public class ProductMapper {

    public static ProductOutputDto toOutputDto(Product product) {
        return ProductOutputDto.builder()
                .id(product.getId())
                .name(product.getName())
                .price(product.getPrice())
                .categoryId(product.getCategory().getId())
                .build();
    }

    public static List<ProductOutputDto> toOutputDtoList(Iterable<Product> products) {
        if (products == null) {
            return List.of();
        }
        return StreamSupport.stream(products.spliterator(), false)
                .map(ProductMapper::toOutputDto)
                .collect(Collectors.toList());
    }

    public static Product fromInputDto(ProductInputDto productInputDto) {
        return Product.builder()
                .name(productInputDto.getName())
                .price(productInputDto.getPrice())
                .build();
    }

}
