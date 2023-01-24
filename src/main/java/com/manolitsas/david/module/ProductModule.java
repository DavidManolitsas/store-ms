package com.manolitsas.david.module;

import com.manolitsas.david.model.OrderLine;
import com.manolitsas.david.model.Product;
import com.manolitsas.david.model.request.ProductsOrderedRequest;
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

  public List<Product> getAllProducts() {
    return productRepository.findAll();
  }

  public List<Product> getProductWithPriceLessThan(String category, double price) {
    return productRepository.findAll().stream()
        .filter(p -> p.getCategory().equalsIgnoreCase(category))
        .filter(p -> p.getPrice() < price)
        .collect(Collectors.toList());
  }

  public List<Product> getAllProductsByCategory(String category) {
    return productRepository.findAll().stream()
        .filter(p -> p.getCategory().equalsIgnoreCase(category))
        .collect(Collectors.toList());
  }

  public List<Product> getCategoryProductSale(String category, double discount) {
    return productRepository.findAll().stream()
        .filter(p -> p.getCategory().equalsIgnoreCase(category))
        .map(
            p -> {
              double discountPrice = p.getPrice() * (1.0 - discount);
              p.setPrice(discountPrice);
              return p;
            })
        .collect(Collectors.toList());
  }

  public List<Product> getProductsOrdered(ProductsOrderedRequest request) {
    return orderRepository.findAll().stream()
        .filter(o -> o.getCustomer().getTier().equalsIgnoreCase(request.getCustomerTier()))
        .filter(o -> o.getOrderDate().compareTo(request.getStartDate()) >= 0)
        .filter(o -> o.getOrderDate().compareTo(request.getEndDate()) <= 0)
        .flatMap(o -> o.getOrderLines().stream().map(OrderLine::getProduct))
        .distinct()
        .collect(Collectors.toList());
  }

  public List<Product> getCheapestProductInCategory(String category) {
    return List.of(
        Objects.requireNonNull(
            productRepository.findAll().stream()
                .filter(p -> p.getCategory().equalsIgnoreCase(category))
                .min(Comparator.comparing(Product::getPrice))
                .orElse(null)));
  }
}
