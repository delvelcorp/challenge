package org.develcorp.services.account.config;

import org.develcorp.services.account.error.RestTemplateExceptionHandler;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@lombok.experimental.FieldDefaults(level = lombok.AccessLevel.PRIVATE)
@Configuration
public class ApplicationConfiguration {

  @Bean
  public RestTemplate restTemplate(RestTemplateBuilder restTemplateBuilder,
                                   RestTemplateExceptionHandler restTemplateExceptionHandler) {
    return restTemplateBuilder.errorHandler(restTemplateExceptionHandler).build();
  }

}