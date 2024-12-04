package com.immfly.java_backend_test.api.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CategoryInputDto {

    @Schema(example = "Juices")
    private String name;
    @Schema(example = "3fa85f64-5717-4562-b3fc-2c963f66afa6")
    private String parentCategoryId;
    
}
