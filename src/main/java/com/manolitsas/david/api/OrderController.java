package com.manolitsas.david.api;

import com.manolitsas.david.model.Order;
import com.manolitsas.david.model.request.CreateOrderRequest;
import com.manolitsas.david.model.request.OrderStatusRequest;
import com.manolitsas.david.model.response.DailySummaryResponse;
import com.manolitsas.david.model.response.RevenueResponse;
import com.manolitsas.david.module.OrderModule;
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
public class OrderController {

  private final OrderModule orderModule;

  @PostMapping("/order")
  public ResponseEntity<Order> createOrder(@RequestBody CreateOrderRequest request) {
    log.info("Creating a new order");
    return ResponseEntity.ok(orderModule.createOrder(request));
  }

  @PostMapping("/order/update/status")
  public ResponseEntity<Order> updateOrderStatus(@RequestBody OrderStatusRequest request) {
    log.info("Updating order {} status to {}", request.getOrderId(), request.getStatus());
    return ResponseEntity.ok(orderModule.updateOrderStatus(request));
  }

  @GetMapping("/order/{id}")
  public ResponseEntity<Order> getOrderById(@PathVariable Long id) {
    log.info("Getting order by id {}", id);
    return ResponseEntity.ok(orderModule.getOrderById(id));
  }

  @GetMapping("/order/controller")
  public ResponseEntity<List<Order>> getAllControllerOrders() {
    log.info("Getting all controller orders");
    return ResponseEntity.ok(orderModule.getAllControllerOrders());
  }

  @GetMapping("/order/recent/{limit}")
  public ResponseEntity<List<Order>> getRecentOrders(@PathVariable int limit) {
    log.info("Getting recent orders");
    return ResponseEntity.ok(orderModule.getRecentOrders(limit));
  }

  @GetMapping("/order")
  public ResponseEntity<List<Order>> getOrdersOnDate(@RequestParam String date) {
    log.info("Getting orders made on {}", date);
    return ResponseEntity.ok(orderModule.getOrdersByDate(date));
  }

  @GetMapping("/order/revenue/{year}/{month}")
  public ResponseEntity<RevenueResponse> getMonthRevenue(
      @PathVariable String month, @PathVariable int year) {
    log.info("Getting {} {} total revenue", month, year);
    return ResponseEntity.ok(orderModule.getMonthRevenue(month, year));
  }

  @GetMapping("/order/revenue/{day}/average")
  public ResponseEntity<DailySummaryResponse> getDailyAverageTransactionValue(
      @PathVariable String day) {
    log.info("Getting daily sales summary for {}", day);
    return ResponseEntity.ok(orderModule.getDailyAverageTransactionValue(day));
  }
}
