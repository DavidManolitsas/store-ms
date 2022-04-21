package com.manolitsas.david.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.oauth2.core.OAuth2TokenValidator;
import org.springframework.security.oauth2.jwt.*;

@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

  //  @Value("${spring.security.oauth2.resourceserver.jwt.issuer-uri}")
  private final String issuer = "https://dev-zhd4iadn.us.auth0.com/";

  @Override
  public void configure(HttpSecurity http) throws Exception {
    http.cors()
        .and()
        .authorizeRequests()
        .mvcMatchers("/api/**")
        .authenticated()
        .and()
        .oauth2ResourceServer()
        .jwt();
  }

  @Bean
  public JwtDecoder jwtDecoder() {
    NimbusJwtDecoder jwtDecoder = JwtDecoders.fromOidcIssuerLocation(issuer);
    OAuth2TokenValidator<Jwt> withIssuer = JwtValidators.createDefaultWithIssuer(issuer);
    jwtDecoder.setJwtValidator(withIssuer);

    return jwtDecoder;
  }
}
