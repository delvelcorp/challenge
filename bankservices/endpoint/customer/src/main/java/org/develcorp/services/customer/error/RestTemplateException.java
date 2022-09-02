package org.develcorp.services.customer.error;

import org.springframework.http.HttpStatus;

@lombok.Getter
public class RestTemplateException extends RuntimeException {

  private final HttpStatus statusCode;

  public RestTemplateException(HttpStatus statusCode, String message) {
    super(message);
    this.statusCode = statusCode;
  }

}
