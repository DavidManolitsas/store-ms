package com.manolitsas.david.model.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class RevenueResponse {

  @JsonProperty private String month;

  @JsonProperty private int year;

  @JsonProperty private double revenue;
}
