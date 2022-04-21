package com.manolitsas.david.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.manolitsas.david.entity.Order;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class OrderResponse {

  @JsonProperty private final Order order;
}
