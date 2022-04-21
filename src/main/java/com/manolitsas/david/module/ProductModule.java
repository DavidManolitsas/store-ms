package com.manolitsas.david.module;

import com.manolitsas.david.entity.OrderLine;
import com.manolitsas.david.entity.Product;
import com.manolitsas.david.model.ProductsOrderedRequest;
import com.manolitsas.david.model.ProductsResponse;
import com.manolitsas.david.repository.OrderRepository;
import com.manolitsas.david.repository.ProductRepository;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class ProductModule {

  private final ProductRepository productRepository;
  private final OrderRepository orderRepository;

  public ProductsResponse getAllProducts() {
    return ProductsResponse.builder().products(productRepository.findAll()).build();
  }

  public ProductsResponse getProductWithPriceLessThan(String category, double price) {
    log.info("Getting products from {} with price less than ${}", category, price);
    return ProductsResponse.builder()
        .products(
            productRepository.findAll().stream()
                .filter(p -> p.getCategory().equalsIgnoreCase(category))
                .filter(p -> p.getPrice() < price)
                .collect(Collectors.toList()))
        .build();
  }

  public ProductsResponse getAllProductsByCategory(String category) {
    return ProductsResponse.builder()
        .products(
            productRepository.findAll().stream()
                .filter(p -> p.getCategory().equalsIgnoreCase(category))
                .collect(Collectors.toList()))
        .build();
  }

  public ProductsResponse getCategoryProductSale(String category, double discount) {
    return ProductsResponse.builder()
        .products(
            productRepository.findAll().stream()
                .filter(p -> p.getCategory().equalsIgnoreCase(category))
                .map(
                    p -> {
                      double discountPrice = p.getPrice() * (1.0 - discount);
                      p.setPrice(discountPrice);
                      return p;
                    })
                .collect(Collectors.toList()))
        .build();
  }

  public ProductsResponse getProductsOrdered(ProductsOrderedRequest request) {
    return ProductsResponse.builder()
        .products(
            orderRepository.findAll().stream()
                .filter(o -> o.getCustomer().getTier().equalsIgnoreCase(request.getCustomerTier()))
                .filter(o -> o.getOrderDate().compareTo(request.getStartDate()) >= 0)
                .filter(o -> o.getOrderDate().compareTo(request.getEndDate()) <= 0)
                .flatMap(o -> o.getOrderLines().stream().map(OrderLine::getProduct))
                .distinct()
                .collect(Collectors.toList()))
        .build();
  }

  public ProductsResponse getCheapestProductInCategory(String category) {
    return ProductsResponse.builder()
        .products(
            List.of(
                Objects.requireNonNull(
                    productRepository.findAll().stream()
                        .filter(p -> p.getCategory().equalsIgnoreCase(category))
                        .min(Comparator.comparing(Product::getPrice))
                        .orElse(null))))
        .build();
  }
}
