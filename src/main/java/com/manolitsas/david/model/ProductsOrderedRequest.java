package com.manolitsas.david.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.time.LocalDate;
import lombok.Getter;
import org.springframework.validation.annotation.Validated;

@Getter
@Validated
public class ProductsOrderedRequest {

  @JsonProperty private LocalDate startDate;

  @JsonProperty private LocalDate endDate;

  @JsonProperty private String customerTier;
}
