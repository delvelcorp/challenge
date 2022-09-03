package org.develcorp.services.customer.services;

import org.develcorp.services.customer.model.dto.CustomerDto;

import java.util.List;

public interface CustomerService {

    List<CustomerDto> getAllCustomers();

    CustomerDto findByCustomer(Long id);

    CustomerDto saveCustomer(CustomerDto customerDto);

    CustomerDto updateCustomer(CustomerDto customerDto);

    CustomerDto deleteCustomer(Long id);

    List<CustomerDto> findByName(String name);

    CustomerDto findByIdentification(String identification);
}