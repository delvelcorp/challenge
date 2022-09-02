package org.develcorp.services.customer.repository;

import org.assertj.core.api.Assertions;
import org.develcorp.services.customer.model.entity.Customer;
import org.develcorp.services.customer.model.entity.Person;
import org.develcorp.services.customer.model.enums.Gender;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;

@DataJpaTest
@AutoConfigureTestDatabase(replace=AutoConfigureTestDatabase.Replace.NONE)
public class CustomerRepositoryMockTest {

    @Autowired
    private CustomerRepository customerRepository;


}
