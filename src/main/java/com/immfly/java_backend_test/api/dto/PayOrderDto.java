package com.immfly.java_backend_test.api.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PayOrderDto {

    @Schema(example = "5555555555555555")
    private String cardToken;
    @Schema(example = "ING")
    private String paymentGateway;
    @Schema(example = "buyer@email.com")
    private String buyerEmail;

}
