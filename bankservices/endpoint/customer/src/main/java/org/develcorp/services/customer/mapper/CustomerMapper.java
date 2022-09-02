package org.develcorp.services.customer.mapper;

import org.develcorp.services.customer.model.dto.CustomerDto;
import org.develcorp.services.customer.model.entity.Customer;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CustomerMapper {

    Customer CustomerDtoToCustomer(CustomerDto customerDto);

    CustomerDto CustomerToCustomerDto(Customer customer);
}
