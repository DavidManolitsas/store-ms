package com.manolitsas.david.util;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TokenResponse {

  @JsonProperty("access_token")
  private String accessToken;

  @JsonProperty("expires_in")
  private String expiresIn;

  @JsonProperty("token_type")
  private String tokenType;
}
