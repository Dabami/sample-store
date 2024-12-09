package com.immfly.java_backend_test.api.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OrderInputDto {

    @Schema(example = "A")
    private String seatLetter;
    @Schema(example = "10")
    private Integer seatNumber;

}
