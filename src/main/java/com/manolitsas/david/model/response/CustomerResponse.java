package com.manolitsas.david.model.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.manolitsas.david.entity.Customer;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class CustomerResponse {

  @JsonProperty private final Customer customer;
}
