package org.develcorp.services.customer.mapper;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.Generated;
import org.develcorp.services.customer.model.dto.CustomerDto;
import org.develcorp.services.customer.model.entity.Customer;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2022-09-02T10:56:39-0500",
    comments = "version: 1.5.2.Final, compiler: javac, environment: Java 11.0.15 (Oracle Corporation)"
)
@Component
public class CustomerMapperImpl implements CustomerMapper {

    @Override
    public Customer CustomerDtoToCustomer(CustomerDto customerDto) {
        if ( customerDto == null ) {
            return null;
        }

        Customer customer = new Customer();

        customer.setId( customerDto.getId() );
        customer.setName( customerDto.getName() );
        customer.setGender( customerDto.getGender() );
        customer.setAge( customerDto.getAge() );
        customer.setIdentification( customerDto.getIdentification() );
        customer.setAddress( customerDto.getAddress() );
        customer.setPhone( customerDto.getPhone() );
        customer.setPassword( customerDto.getPassword() );
        customer.setStatus( customerDto.isStatus() );
        List<?> list = customerDto.getAccounts();
        if ( list != null ) {
            customer.setAccounts( new ArrayList<Object>( list ) );
        }

        return customer;
    }

    @Override
    public CustomerDto CustomerToCustomerDto(Customer customer) {
        if ( customer == null ) {
            return null;
        }

        CustomerDto customerDto = new CustomerDto();

        customerDto.setId( customer.getId() );
        customerDto.setName( customer.getName() );
        customerDto.setGender( customer.getGender() );
        customerDto.setAge( customer.getAge() );
        customerDto.setIdentification( customer.getIdentification() );
        customerDto.setAddress( customer.getAddress() );
        customerDto.setPhone( customer.getPhone() );
        customerDto.setPassword( customer.getPassword() );
        customerDto.setStatus( customer.isStatus() );
        List<?> list = customer.getAccounts();
        if ( list != null ) {
            customerDto.setAccounts( new ArrayList<Object>( list ) );
        }

        return customerDto;
    }
}
