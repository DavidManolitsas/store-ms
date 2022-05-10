package com.manolitsas.david.model.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.manolitsas.david.entity.Order;
import java.util.List;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class OrdersResponse {

  @JsonProperty private final List<Order> orders;
}
