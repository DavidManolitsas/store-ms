package com.manolitsas.david.api;

import com.manolitsas.david.model.request.CreateCustomerRequest;
import com.manolitsas.david.model.response.CustomerResponse;
import com.manolitsas.david.module.CustomerModule;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequiredArgsConstructor
@RequestMapping("/api")
public class CustomerController {

  private final CustomerModule customerModule;

  @GetMapping("/customer/{id}")
  public ResponseEntity<CustomerResponse> getCustomerById(@PathVariable Long id) {
    return ResponseEntity.ok(customerModule.getCustomer(id));
  }

  @PostMapping("/customer/create")
  public ResponseEntity<CustomerResponse> createCustomer(
      @RequestBody CreateCustomerRequest request) {
    return ResponseEntity.ok(customerModule.createCustomer(request));
  }
}
