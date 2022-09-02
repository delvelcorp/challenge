package org.develcorp.services.customer.error;

import org.springframework.http.client.ClientHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.util.StreamUtils;
import org.springframework.web.client.DefaultResponseErrorHandler;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

@Component
public class RestTemplateExceptionHandler extends DefaultResponseErrorHandler {

  @Override
  public void handleError(@lombok.NonNull ClientHttpResponse response) throws IOException {
    throw new RestTemplateException(response.getStatusCode(),
        StreamUtils.copyToString(response.getBody(),
            StandardCharsets.UTF_8));
  }

}
