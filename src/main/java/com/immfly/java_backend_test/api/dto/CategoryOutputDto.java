package com.immfly.java_backend_test.api.dto;

import java.util.UUID;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class CategoryOutputDto {

    private UUID id;
    private String name;
    private UUID parentCategoryId;

}
