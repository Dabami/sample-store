package com.immfly.java_backend_test.api.dto;

import java.sql.Date;
import java.util.List;
import java.util.UUID;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class OrderOutputDto {

    private UUID id;
    private String status;
    private List<ProductOutputDto> products;
    private Float totalPrice;
    private String cardToken;
    private String paymentStatus;
    private Date paymentDate;
    private String paymentGateway;

}
