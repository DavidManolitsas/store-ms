package com.manolitsas.david.module;

import com.manolitsas.david.model.Customer;
import com.manolitsas.david.model.Order;
import com.manolitsas.david.model.OrderLine;
import com.manolitsas.david.model.Product;
import com.manolitsas.david.model.request.CreateOrderRequest;
import com.manolitsas.david.model.request.OrderStatusRequest;
import com.manolitsas.david.model.response.DailySummaryResponse;
import com.manolitsas.david.model.response.RevenueResponse;
import com.manolitsas.david.repository.CustomerRepository;
import com.manolitsas.david.repository.OrderRepository;
import com.manolitsas.david.repository.ProductRepository;
import java.text.DecimalFormat;
import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class OrderModule {

  private final OrderRepository orderRepository;
  private final CustomerRepository customerRepository;
  private final ProductRepository productRepository;
  private static final DecimalFormat df = new DecimalFormat("0.00");

  public Order createOrder(CreateOrderRequest request) {
    LocalDate today = LocalDate.now();
    List<OrderLine> orderLines = new ArrayList<>();

    Customer customer = customerRepository.findById(request.getCustomerId()).orElse(null);

    if (customer == null) {
      // TODO: 10/5/2022 error handling not found 404
      log.info("Customer {} not found", request.getCustomerId());
      return null;
    }

    Order order =
        Order.builder()
            .customer(customer)
            .status("Pending")
            .orderDate(today)
            .deliveryDate(today.plusDays(14))
            .build();

    for (OrderLine orderLine : request.getOrderLines()) {
      Product product = productRepository.findById(orderLine.getProduct().getId()).orElse(null);

      if (product == null) {
        // TODO: 10/5/2022 error handling not found 404
        log.info("Product {} not found", orderLine.getProduct().getId());
        return null;
      }

      orderLines.add(
          OrderLine.builder()
              .order(order)
              .product(product)
              .quantity(orderLine.getQuantity())
              .build());
    }

    Double orderTotal =
        orderLines.stream().map(OrderLine::getLineTotal).reduce(Double::sum).orElse(null);

    order.setOrderLines(orderLines);
    order.setTotal(orderTotal);
    orderRepository.save(order);

    return order;
  }

  public Order getOrderById(Long id) {
    Order order = orderRepository.findById(id).orElse(null);

    if (order == null || order.getOrderLines() == null) {
      // TODO: 12/4/2022 : Error Handling
      return null;
    }

    if (order.getTotal() == null) {
      Double total =
          order.getOrderLines().stream()
              .map(OrderLine::getLineTotal)
              .reduce(Double::sum)
              .orElse(null);

      order.setTotal(total);
    }

    return order;
  }

  public Order updateOrderStatus(OrderStatusRequest request) {
    var order = orderRepository.findById(request.getOrderId()).orElse(null);

    if (order == null) {
      log.info("Order {} not found", request.getOrderId());
      return null;
    }

    order.setStatus(request.getStatus());
    orderRepository.save(order);
    return order;
  }

  public List<Order> getAllControllerOrders() {
    return orderRepository.findAll().stream()
        .filter(
            o ->
                o.getOrderLines().stream()
                    .anyMatch(
                        l -> l.getProduct().getCategory().equalsIgnoreCase("Controller")))
        .collect(Collectors.toList());
  }

  public List<Order> getRecentOrders(int limit) {
    return orderRepository.findAll().stream()
        .sorted(Comparator.comparing(Order::getOrderDate).reversed())
        .limit(limit)
        .collect(Collectors.toList());
  }

  public List<Order> getOrdersByDate(String date) {
    LocalDate orderDate = LocalDate.parse(date);
    return orderRepository.findAll().stream()
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
        .collect(Collectors.toList());
  }

  public RevenueResponse getMonthRevenue(String month, int year) {
    LocalDate start =
        LocalDate.of(year, Month.valueOf(month.toUpperCase(Locale.ROOT)).getValue(), 1);
    LocalDate end = start.plusMonths(1);

    return RevenueResponse.builder()
        .month(month)
        .year(year)
        .revenue(
            Double.parseDouble(
                df.format(
                    orderRepository.findAll().stream()
                        .filter(o -> o.getOrderDate().compareTo(start) >= 0)
                        .filter(o -> o.getOrderDate().compareTo(end) < 0)
                        .flatMap(o -> o.getOrderLines().stream())
                        .mapToDouble(OrderLine::getLineTotal)
                        .sum())))
        .build();
  }

  public DailySummaryResponse getDailyAverageTransactionValue(String date) {
    LocalDate localDate = LocalDate.parse(date);

    // calculate average daily transaction value
    var revenue =
        orderRepository.findAll().stream()
            .filter(o -> o.getOrderDate().isEqual(localDate))
            .peek(o -> log.info("Order {}: {}", o.getId(), o.getTotal()))
            .flatMap(o -> o.getOrderLines().stream())
            .mapToDouble(OrderLine::getLineTotal)
            .sum();

    // calculate number of transactions
    var transactionCount =
        orderRepository.findAll().stream().filter(o -> o.getOrderDate().isEqual(localDate)).count();

    return DailySummaryResponse.builder()
        .date(localDate)
        .averageTransactionValue(revenue / transactionCount)
        .numOfTransactions(transactionCount)
        .build();
  }
}
