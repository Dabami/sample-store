package com.immfly.java_backend_test.api.mapper;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import com.immfly.java_backend_test.api.dto.CategoryOutputDto;
import com.immfly.java_backend_test.domain.entity.Category;

public class CategoryMapper {

    public static CategoryOutputDto toDto(Category category) {
        return CategoryOutputDto.builder()
                .id(category.getId())
                .name(category.getName())
                .parentCategoryId(category.getParentCategory() != null ? category.getParentCategory().getId() : null)
                .build();
    }

    public static List<CategoryOutputDto> toDtoList(Iterable<Category> categories) {
        return StreamSupport.stream(categories.spliterator(), false)
                .map(CategoryMapper::toDto)
                .collect(Collectors.toList());
    }

}
