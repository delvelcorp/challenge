package org.develcorp.services.customer.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

@lombok.Getter
@lombok.Setter
@lombok.experimental.FieldDefaults(level = lombok.AccessLevel.PRIVATE)
@ConfigurationProperties(prefix = "customer", ignoreUnknownFields = false)
public class CustomerProperties {


  CustomerError customerError;
  String url;
  MethodGet get;

  @lombok.Getter
  @lombok.Setter
  @lombok.experimental.FieldDefaults(level = lombok.AccessLevel.PRIVATE)
  public static class MethodGet {
    String byCustomerId;
  }

  @lombok.Getter
  @lombok.Setter
  @lombok.experimental.FieldDefaults(level = lombok.AccessLevel.PRIVATE)
  public static class CustomerError {
    String title;
    String component;
    String detail;
  }
}
