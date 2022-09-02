package org.develcorp.services.account.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

@lombok.Getter
@lombok.Setter
@lombok.experimental.FieldDefaults(level = lombok.AccessLevel.PRIVATE)
@ConfigurationProperties(prefix = "customer", ignoreUnknownFields = false)
public class TransactionProperties {


  TransactionError customerError;
  String url;
  MethodGet get;

  @lombok.Getter
  @lombok.Setter
  @lombok.experimental.FieldDefaults(level = lombok.AccessLevel.PRIVATE)
  public static class MethodGet {
    String byTransactionId;
  }

  @lombok.Getter
  @lombok.Setter
  @lombok.experimental.FieldDefaults(level = lombok.AccessLevel.PRIVATE)
  public static class TransactionError {
    String title;
    String component;
    String detail;
  }
}
