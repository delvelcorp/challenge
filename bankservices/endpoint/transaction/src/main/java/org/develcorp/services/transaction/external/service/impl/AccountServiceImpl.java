package org.develcorp.services.transaction.external.service.impl;

import lombok.extern.log4j.Log4j2;
import org.develcorp.services.transaction.config.AccountProperties;
import org.develcorp.services.transaction.external.dto.account.AccountDto;
import org.develcorp.services.transaction.external.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import java.util.*;

@Service
@Log4j2
public class AccountServiceImpl implements AccountService {

    @Autowired
    RestTemplate restTemplate;

    @Autowired
    private AccountProperties accountProperties;

    public AccountDto getAccountNumber(Long id) {
        log.info("Start method getAccountNumber :: Parameters [{}]", () -> id);

        String urlService = this.accountProperties.getUrl();
        String methodService = this.accountProperties.getGet().getByAccountNumber();

        String url = UriComponentsBuilder.fromHttpUrl(urlService.concat(methodService))
                .build().toUriString();

        Map<String, Long> pathParams = new HashMap<>();
        pathParams.put("id", id);

        AccountDto response =
                restTemplate.getForEntity(url, AccountDto.class, pathParams).getBody();

        log.info("End method getAccountNumber :: Result [{}]", () -> response);

        return response;
    }


}