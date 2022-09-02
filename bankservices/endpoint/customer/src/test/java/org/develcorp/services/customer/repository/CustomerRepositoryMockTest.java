package org.develcorp.services.customer.repository;

import org.assertj.core.api.Assertions;
import org.develcorp.services.customer.model.entity.Customer;
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

    @Test
    public void whenFindAll_thenReturnListCustomers(){
        Customer customer = new Customer();
                customer.setAge(23);
                customer.setAddress("Amazonas");
                customer.setGender(Gender.MAN);
                customer.setIdentification("0103948374");
                customer.setName("Jhon Lazo");
                customer.setPhone("0948734748");
                customer.setPassword("39432328");
                customer.setStatus(true);

        customerRepository.save(customer);

        List<Customer> founds = customerRepository.findAll();

        Assertions.assertThat(founds.size()).isEqualTo(1);
    }
}
