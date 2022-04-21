package com.manolitsas.david.module;

import com.manolitsas.david.entity.Customer;
import com.manolitsas.david.model.CreateCustomerRequest;
import com.manolitsas.david.model.CustomerResponse;
import com.manolitsas.david.repository.CustomerRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class CustomerModule {

  private final CustomerRepository customerRepository;

  public CustomerResponse getCustomer(Long id) {
    Customer customer = customerRepository.findById(id).orElse(null);
    return CustomerResponse.builder().customer(customer).build();
  }

  public CustomerResponse createCustomer(CreateCustomerRequest request) {
    Customer customer =
        customerRepository.save(
            Customer.builder().name(request.getName()).tier(request.getTier()).build());
    log.info(
        "Customer created: {}, {}, {}", customer.getId(), customer.getName(), customer.getTier());
    return CustomerResponse.builder().customer(customer).build();
  }
}
