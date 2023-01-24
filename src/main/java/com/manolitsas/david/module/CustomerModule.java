package com.manolitsas.david.module;

import com.manolitsas.david.model.Customer;
import com.manolitsas.david.model.request.CreateCustomerRequest;
import com.manolitsas.david.repository.CustomerRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class CustomerModule {

  private final CustomerRepository customerRepository;

  public Customer getCustomer(Long id) {
    return customerRepository.findById(id).orElse(null);
  }

  public Customer createCustomer(CreateCustomerRequest request) {
    Customer customer =
        customerRepository.save(
            Customer.builder().name(request.getName()).tier(request.getTier()).build());
    log.info(
        "Customer created: {}, {}, {}", customer.getId(), customer.getName(), customer.getTier());
    return customer;
  }

  public void deleteCustomer(Long id) {
    log.info("Deleting customer {}", id);
  }
}
