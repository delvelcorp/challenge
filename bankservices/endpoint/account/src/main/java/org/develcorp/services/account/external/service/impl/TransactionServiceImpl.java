package org.develcorp.services.account.external.service.impl;

import lombok.extern.log4j.Log4j2;
import org.develcorp.services.account.config.TransactionProperties;
import org.develcorp.services.account.external.dto.transaction.TransactionDto;
import org.develcorp.services.account.external.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.HashMap;
import java.util.Map;

@Service
@Log4j2
public class TransactionServiceImpl implements TransactionService {

    @Autowired
    RestTemplate restTemplate;

    @Autowired
    private TransactionProperties transactionProperties;

    public TransactionDto getTransactionId(Long id) {
        log.info("Start method getTransactionId :: Parameters [{}]", () -> id);

        String urlService = this.transactionProperties.getUrl();
        String methodService = this.transactionProperties.getGet().getByTransactionId();

        String url = UriComponentsBuilder.fromHttpUrl(urlService.concat(methodService))
                .build().toUriString();

        Map<String, Long> pathParams = new HashMap<>();
        pathParams.put("id", id);

        TransactionDto response =
                restTemplate.getForEntity(url, TransactionDto.class, pathParams).getBody();

        log.info("End method getTransactionId :: Result [{}]", () -> response);

        return response;
    }


}