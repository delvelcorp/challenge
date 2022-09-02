package org.develcorp.services.customer.services.impl;

import io.netty.channel.ChannelOption;
import io.netty.handler.timeout.ReadTimeoutHandler;
import io.netty.handler.timeout.WriteTimeoutHandler;
import org.develcorp.services.customer.mapper.CustomerMapper;
import org.develcorp.services.customer.model.dto.CustomerDto;
import org.develcorp.services.customer.model.entity.Customer;
import org.develcorp.services.customer.model.error.CustomerError;
import org.develcorp.services.customer.repository.CustomerRepository;
import org.develcorp.services.customer.services.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.client.reactive.ReactorClientHttpConnector;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.netty.http.client.HttpClient;
import reactor.netty.tcp.TcpClient;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.TimeUnit;

@Service
public class CustomerServiceImpl implements CustomerService {

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private CustomerMapper customerMapper;

    private final WebClient.Builder webClientBuilder;

    public CustomerServiceImpl(WebClient.Builder webClientBuilder) {
        this.webClientBuilder = webClientBuilder;
    }

    TcpClient tcpClient = TcpClient
            .create()
            .option(ChannelOption.CONNECT_TIMEOUT_MILLIS, 5000)
            .doOnConnected(connection -> {
                connection.addHandlerLast(new ReadTimeoutHandler(5000, TimeUnit.MILLISECONDS));
                connection.addHandlerLast(new WriteTimeoutHandler(5000, TimeUnit.MILLISECONDS));
            });

    private <T> List<T> getAccounts(long customerId) {
        List<T> accounts = new ArrayList<>();

        try {
            WebClient client = webClientBuilder.clientConnector(new ReactorClientHttpConnector(HttpClient.from(tcpClient)))
                    .baseUrl("http://endpoint-account")
                    .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                    .defaultUriVariables(Collections.singletonMap("url", "http://endpoint-account"))
                    .build();
            List<Object> block = client.method(HttpMethod.GET).uri(uriBuilder -> uriBuilder
                            .path("/cuentas")
                            .queryParam("customerId", customerId)
                            .build())
                    .retrieve().bodyToFlux(Object.class).collectList().block();
            accounts = (List<T>) block;
        } catch (Exception e) {
            return accounts;
        }
        return accounts;
    }

    @Override
    @Transactional(readOnly = true)
    public List<CustomerDto> listAllCustomers() {
        List<Customer> customers = customerRepository.findAll();
        List<CustomerDto> customerDtos = new ArrayList<>();

        for (Customer customer : customers) {
            CustomerDto customerDto = customerMapper.CustomerToCustomerDto(customer);
            customerDtos.add(customerDto);
        }

        return customerDtos;
    }

    @Override
    @Transactional(readOnly = true)
    public CustomerDto byCustomerId(Long id) {
        CustomerDto customerDto = customerMapper.CustomerToCustomerDto(customerRepository.findById(id).orElse(null));
        if(customerDto == null){
            return null;
        }

        customerDto.setAccounts(getAccounts(customerDto.getId()));
        return customerDto;
    }

    @Override
    @Transactional
    public CustomerDto saveCustomer(CustomerDto customerDto) {
        Customer customer = customerMapper.CustomerDtoToCustomer(customerDto);

        if (customerRepository.findByIdentification(customer.getIdentification()) != null) {
            throw CustomerError.E002;
        } else {
            customer.setStatus(true);
            return customerMapper.CustomerToCustomerDto(customerRepository.save(customer));
        }
    }

    @Override
    @Transactional
    public CustomerDto updateCustomer(CustomerDto customerDto) {
        Customer customer = customerMapper.CustomerDtoToCustomer(customerDto);
        customer = customerRepository.findByIdentification(customer.getIdentification());

        if (customer != null) {
            customer.setName(customerDto.getName());
            customer.setGender(customerDto.getGender());
            customer.setAge(customerDto.getAge());
            customer.setIdentification(customerDto.getIdentification());
            customer.setAddress(customerDto.getAddress());
            customer.setPhone(customerDto.getPhone());
            customer.setPassword(customerDto.getPassword());
            customer.setStatus(customerDto.isStatus());
            customer.setAccounts(customerDto.getAccounts());

            return customerMapper.CustomerToCustomerDto(customerRepository.save(customer));
        } else {
            throw CustomerError.E001;
        }
    }

    @Override
    @Transactional
    public CustomerDto deleteCustomer(Long id) {
        Customer customer = customerRepository.findById(id).orElse(null);

        if (customer != null) {
            try{
                customer.setStatus(false);
                return customerMapper.CustomerToCustomerDto(customerRepository.save(customer));
            }catch (Exception e){
                throw CustomerError.E004;
            }
        }else {
            throw CustomerError.E001;
        }
    }

    @Override
    @Transactional(readOnly = true)
    public List<CustomerDto> findByName(String name) {
        List<Customer> customers = customerRepository.findByName(name);
        List<CustomerDto> customerDtoList = new ArrayList<>();

        for (Customer customer : customers) {
            CustomerDto customerDto = customerMapper.CustomerToCustomerDto(customer);
            customerDtoList.add(customerDto);
        }

        return customerDtoList;
    }
}