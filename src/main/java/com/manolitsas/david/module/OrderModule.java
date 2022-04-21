package com.manolitsas.david.module;

import com.manolitsas.david.entity.Order;
import com.manolitsas.david.model.OrderResponse;
import com.manolitsas.david.model.OrdersResponse;
import com.manolitsas.david.repository.OrderRepository;
import java.time.LocalDate;
import java.util.Comparator;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class OrderModule {

  private final OrderRepository orderRepository;

  public OrderResponse getOrderById(Long id) {
    Order order = orderRepository.findById(id).orElse(null);

    if (order == null || order.getOrderLines() == null) {
      // TODO: 12/4/2022 : Error Handling
      return null;
    }

    if (order.getTotal() == null) {
      Double total =
          order.getOrderLines().stream()
              .map(line -> line.getProduct().getPrice() * line.getQuantity())
              .reduce(Double::sum)
              .orElse(null);

      order.setTotal(total);
    }

    return OrderResponse.builder().order(order).build();
  }

  public OrdersResponse getAllControllerOrders() {
    return OrdersResponse.builder()
        .orders(
            orderRepository.findAll().stream()
                .filter(
                    o ->
                        o.getOrderLines().stream()
                            .anyMatch(
                                l -> l.getProduct().getCategory().equalsIgnoreCase("Controller")))
                .collect(Collectors.toList()))
        .build();
  }

  public OrdersResponse getRecentOrders(int limit) {
    return OrdersResponse.builder()
        .orders(
            orderRepository.findAll().stream()
                .sorted(Comparator.comparing(Order::getOrderDate).reversed())
                .limit(limit)
                .collect(Collectors.toList()))
        .build();
  }

  public OrdersResponse getOrdersByDate(String date) {
    LocalDate orderDate = LocalDate.parse(date);
    return OrdersResponse.builder()
        .orders(
            orderRepository.findAll().stream()
                .filter(o -> o.getOrderDate().equals(orderDate))
                .peek(
                    o ->
                        log.info(
                            "Order {} [Customer: {} {}, Order Date: {}, {}]",
                            o.getId(),
                            o.getCustomer().getId(),
                            o.getCustomer().getName(),
                            o.getOrderDate(),
                            o.getStatus()))
                .collect(Collectors.toList()))
        .build();
  }
}
