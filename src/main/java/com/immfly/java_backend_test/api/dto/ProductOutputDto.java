package com.immfly.java_backend_test.api.dto;

import java.util.UUID;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class ProductOutputDto {

    private UUID id;
    private String name;
    private float price;
    private UUID categoryId;
    private Integer stock;

}
