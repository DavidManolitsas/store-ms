package com.manolitsas.david.api;

import com.manolitsas.david.model.request.CreateOrderRequest;
import com.manolitsas.david.model.response.DailySummaryResponse;
import com.manolitsas.david.model.response.OrderResponse;
import com.manolitsas.david.model.response.OrdersResponse;
import com.manolitsas.david.model.response.RevenueResponse;
import com.manolitsas.david.module.OrderModule;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequiredArgsConstructor
@RequestMapping("/api")
public class OrderController {

  private final OrderModule orderModule;

  @PostMapping("/order")
  public ResponseEntity<OrderResponse> createOrder(@RequestBody CreateOrderRequest request) {
    return ResponseEntity.ok(orderModule.createOrder(request));
  }

  @GetMapping("/order/{id}")
  public ResponseEntity<OrderResponse> getOrderById(@PathVariable Long id) {
    return ResponseEntity.ok(orderModule.getOrderById(id));
  }

  @GetMapping("/order/controller")
  public ResponseEntity<OrdersResponse> getAllControllerOrders() {
    return ResponseEntity.ok(orderModule.getAllControllerOrders());
  }

  @GetMapping("/order/recent/{limit}")
  public ResponseEntity<OrdersResponse> getRecentOrders(@PathVariable int limit) {
    return ResponseEntity.ok(orderModule.getRecentOrders(limit));
  }

  @GetMapping("/order")
  public ResponseEntity<OrdersResponse> getOrdersOnDate(@RequestParam String date) {
    return ResponseEntity.ok(orderModule.getOrdersByDate(date));
  }

  @GetMapping("/order/revenue/{year}/{month}")
  public ResponseEntity<RevenueResponse> getMonthRevenue(
      @PathVariable String month, @PathVariable int year) {
    return ResponseEntity.ok(orderModule.getMonthRevenue(month, year));
  }

  @GetMapping("/order/revenue/{day}/average")
  public ResponseEntity<DailySummaryResponse> getDailyAverageTransactionValue(
      @PathVariable String day) {
    return ResponseEntity.ok(orderModule.getDailyAverageTransactionValue(day));
  }
}
