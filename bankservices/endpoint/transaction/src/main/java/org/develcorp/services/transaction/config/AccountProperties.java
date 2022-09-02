package org.develcorp.services.transaction.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

@lombok.Getter
@lombok.Setter
@lombok.experimental.FieldDefaults(level = lombok.AccessLevel.PRIVATE)
@ConfigurationProperties(prefix = "account", ignoreUnknownFields = false)
public class AccountProperties {


  AccountError accountError;
  String url;
  MethodGet get;

  @lombok.Getter
  @lombok.Setter
  @lombok.experimental.FieldDefaults(level = lombok.AccessLevel.PRIVATE)
  public static class MethodGet {
    String byAccountNumber;
  }

  @lombok.Getter
  @lombok.Setter
  @lombok.experimental.FieldDefaults(level = lombok.AccessLevel.PRIVATE)
  public static class AccountError {
    String title;
    String component;
    String detail;
  }
}
