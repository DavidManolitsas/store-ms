package com.manolitsas.david.model.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.manolitsas.david.entity.Product;
import java.util.List;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class ProductsResponse {

  @JsonProperty private final List<Product> products;
}
