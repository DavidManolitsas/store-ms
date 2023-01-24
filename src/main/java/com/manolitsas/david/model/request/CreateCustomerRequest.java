package com.manolitsas.david.model.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import org.springframework.validation.annotation.Validated;

@Getter
@Validated
@Builder
@AllArgsConstructor
public class CreateCustomerRequest {

  @JsonProperty private String name;

  @JsonProperty private String tier;
}
