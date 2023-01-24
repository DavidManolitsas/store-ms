package com.manolitsas.david.api;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.manolitsas.david.model.Customer;
import com.manolitsas.david.module.CustomerModule;
import com.manolitsas.david.util.JwtUtil;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

@Slf4j
@WebMvcTest(CustomerController.class)
public class CustomerControllerTest {

  @Autowired private MockMvc mvc;

  @MockBean private CustomerModule customerModule;

  @Value("${auth0.audience}")
  private String audience;

  @Value("${auth0.client-id}")
  private String clientId;

  @Value("${auth0.client-secret}")
  private String clientSecret;

  private final ObjectMapper mapper = new ObjectMapper();
  private final JwtUtil jwtUtil = new JwtUtil();

  @Test
  void givenCreateCustomer_whenValidRequest_thenCreateCustomer() throws Exception {
    when(customerModule.getCustomer(any(Long.class)))
        .thenReturn(Customer.builder().id(11L).name("Jane Doe").tier("Gold").build());

    String token =
        jwtUtil.getValidToken(
            "https://dev-zhd4iadn.us.auth0.com/oauth/token", clientId, clientSecret, audience);

    log.info("Bearer: {}", token);

    mvc.perform(
            post("/api/customer/create")
                .header(HttpHeaders.AUTHORIZATION, String.format("Bearer %s", token))
                .contentType(MediaType.APPLICATION_JSON)
                .content(
                    mapper.writeValueAsString(
                        Customer.builder().name("Jane Doe").tier("Gold").build())))
        .andExpect(status().isOk())
        .andReturn();
  }
}
