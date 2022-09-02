package org.develcorp.services.account.external.service;


import org.develcorp.services.account.external.dto.customer.CustomerDto;

public interface CustomerService {

    CustomerDto getCustomerId(Long id);
}
