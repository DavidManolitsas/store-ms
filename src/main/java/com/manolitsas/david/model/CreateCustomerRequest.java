package com.manolitsas.david.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import org.springframework.validation.annotation.Validated;

@Getter
@Validated
public class CreateCustomerRequest {

  @JsonProperty private String name;

  @JsonProperty private String tier;
}
