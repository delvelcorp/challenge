package org.develcorp.services.transaction.external.service.impl;

import lombok.extern.log4j.Log4j2;
import org.develcorp.services.transaction.config.AccountProperties;
import org.develcorp.services.transaction.config.CustomerProperties;
import org.develcorp.services.transaction.external.dto.customer.CustomerDto;
import org.develcorp.services.transaction.external.service.AccountService;
import org.develcorp.services.transaction.external.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.HashMap;
import java.util.Map;

@Service
@Log4j2
public class CustomerServiceImpl implements CustomerService {

    @Autowired
    RestTemplate restTemplate;

    @Autowired
    private CustomerProperties customerProperties;

    public CustomerDto getCustomerId(Long id) {
        log.info("Start method getCustomerId :: Parameters [{}]", () -> id);

        String urlService = this.customerProperties.getUrl();
        String methodService = this.customerProperties.getGet().getByCustomerId();

        String url = UriComponentsBuilder.fromHttpUrl(urlService.concat(methodService))
                .build().toUriString();

        Map<String, Long> pathParams = new HashMap<>();
        pathParams.put("id", id);

        CustomerDto response =
                restTemplate.getForEntity(url, CustomerDto.class, pathParams).getBody();

        log.info("End method getCustomerId :: Result [{}]", () -> response);

        return response;
    }


}