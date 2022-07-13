package com.manolitsas.david.model.request;


import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import org.springframework.validation.annotation.Validated;

@Getter
@Validated
public class OrderStatusRequest {

    @JsonProperty private Long orderId;
    @JsonProperty private String status;
}
