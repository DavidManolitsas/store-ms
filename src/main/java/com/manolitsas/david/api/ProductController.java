package com.manolitsas.david.api;

import com.manolitsas.david.model.Product;
import com.manolitsas.david.model.request.ProductsOrderedRequest;
import com.manolitsas.david.module.ProductModule;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Slf4j
@Controller
@RequiredArgsConstructor
@RequestMapping("/api")
public class ProductController {

  private final ProductModule productModule;

  @GetMapping("/product")
  public ResponseEntity<List<Product>> getAllProducts() {
    log.info("Getting all products");
    return ResponseEntity.ok(productModule.getAllProducts());
  }

  @GetMapping("/product/{category}")
  public ResponseEntity<List<Product>> getAllProductsByCategory(@PathVariable String category) {
    log.info("Getting all {} products", category);
    return ResponseEntity.ok(productModule.getAllProductsByCategory(category));
  }

  @GetMapping("/product/{category}/price/less/{price}")
  public ResponseEntity<List<Product>> getGamesWithCostLessThan(
      @PathVariable String category, @PathVariable double price) {
    log.info("Getting games with price less than ${}", price);
    return ResponseEntity.ok(productModule.getProductWithPriceLessThan(category, price));
  }

  @GetMapping("/product/{category}/sale/{discount}")
  public ResponseEntity<List<Product>> getCategoryProductSale(
      @PathVariable String category, @PathVariable double discount) {
    log.info("Getting {} products with a discount of {}%", category, discount);
    return ResponseEntity.ok(productModule.getCategoryProductSale(category, discount));
  }

  @GetMapping("/product/ordered")
  public ResponseEntity<List<Product>> getProductsOrdered(
      @RequestBody ProductsOrderedRequest request) {
    log.info(
        "Getting all ordered products between the dates of {} to {}",
        request.getStartDate(),
        request.getEndDate());
    return ResponseEntity.ok(productModule.getProductsOrdered(request));
  }

  @GetMapping("/product/{category}/cheapest")
  public ResponseEntity<List<Product>> getCheapestProductInCategory(
      @PathVariable String category) {
    log.info("Getting the cheapest {} product", category);
    return ResponseEntity.ok(productModule.getCheapestProductInCategory(category));
  }
}
