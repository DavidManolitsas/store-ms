package com.manolitsas.david.api;

import com.manolitsas.david.model.OrderResponse;
import com.manolitsas.david.model.OrdersResponse;
import com.manolitsas.david.module.OrderModule;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequiredArgsConstructor
@RequestMapping("/api")
public class OrderController {

  private final OrderModule orderModule;

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
}
