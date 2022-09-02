package org.develcorp.services.transaction.external.service;

import org.develcorp.services.transaction.external.dto.customer.CustomerDto;

public interface CustomerService {

    CustomerDto getCustomerId(Long id);
}
