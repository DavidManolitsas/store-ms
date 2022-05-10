package com.manolitsas.david.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import org.springframework.validation.annotation.Validated;

@Validated
@Getter
public class OrderLineModel {

  @JsonProperty private Long productId;

  @JsonProperty private Integer quantity;
}
