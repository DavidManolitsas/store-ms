package com.manolitsas.david.api;

import com.manolitsas.david.model.ProductsOrderedRequest;
import com.manolitsas.david.model.ProductsResponse;
import com.manolitsas.david.module.ProductModule;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequiredArgsConstructor
@RequestMapping("/api")
public class ProductController {

  private final ProductModule productModule;

  @GetMapping("/product")
  public ResponseEntity<ProductsResponse> getAllProducts() {
    return ResponseEntity.ok(productModule.getAllProducts());
  }

  @GetMapping("/product/{category}")
  public ResponseEntity<ProductsResponse> getAllProductsByCategory(@PathVariable String category) {
    return ResponseEntity.ok(productModule.getAllProductsByCategory(category));
  }

  @GetMapping("/product/{category}/price/less/{price}")
  public ResponseEntity<ProductsResponse> getGamesWithCostLessThan(
      @PathVariable String category, @PathVariable double price) {
    return ResponseEntity.ok(productModule.getProductWithPriceLessThan(category, price));
  }

  @GetMapping("/product/{category}/sale/{discount}")
  public ResponseEntity<ProductsResponse> getCategoryProductSale(
      @PathVariable String category, @PathVariable double discount) {
    return ResponseEntity.ok(productModule.getCategoryProductSale(category, discount));
  }

  @GetMapping("/product/ordered")
  public ResponseEntity<ProductsResponse> getProductsOrdered(
      @RequestBody ProductsOrderedRequest request) {
    return ResponseEntity.ok(productModule.getProductsOrdered(request));
  }

  @GetMapping("/product/{category}/cheapest")
  public ResponseEntity<ProductsResponse> getCheapestProductInCategory(
      @PathVariable String category) {
    return ResponseEntity.ok(productModule.getCheapestProductInCategory(category));
  }
}
