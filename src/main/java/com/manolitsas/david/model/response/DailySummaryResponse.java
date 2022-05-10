package com.manolitsas.david.model.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.time.LocalDate;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class DailySummaryResponse {

  @JsonProperty private LocalDate date;
  @JsonProperty private Double averageTransactionValue;
  @JsonProperty private Long numOfTransactions;
}
