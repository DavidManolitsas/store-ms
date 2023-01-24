package com.manolitsas.david.model.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.manolitsas.david.model.OrderLine;
import java.util.List;
import lombok.Getter;
import org.springframework.validation.annotation.Validated;

@Getter
@Validated
public class CreateOrderRequest {

  @JsonProperty private Long customerId;

  @JsonProperty private List<OrderLine> orderLines;
}
