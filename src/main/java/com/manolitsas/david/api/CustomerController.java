package com.manolitsas.david.api;

import com.manolitsas.david.model.Customer;
import com.manolitsas.david.model.request.CreateCustomerRequest;
import com.manolitsas.david.module.CustomerModule;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Slf4j
@Controller
@RequiredArgsConstructor
@RequestMapping("/api")
public class CustomerController {

  private final CustomerModule customerModule;

  @GetMapping("/customer/{id}")
  public ResponseEntity<Customer> getCustomerById(@PathVariable Long id) {
    log.info("Getting customer by id {}", id);
    return ResponseEntity.ok(customerModule.getCustomer(id));
  }

  @PostMapping("/customer/create")
  public ResponseEntity<Customer> createCustomer(@RequestBody CreateCustomerRequest request) {
    log.info("Creating a new customer");
    return ResponseEntity.ok(customerModule.createCustomer(request));
  }

  @DeleteMapping("/customer/{id}")
  public ResponseEntity.HeadersBuilder<?> deleteCustomer(@PathVariable Long id) {
    log.info("Deleting customer by id {}", id);
    customerModule.deleteCustomer(id);
    return ResponseEntity.noContent();
  }
}
