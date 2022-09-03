package org.develcorp.services.customer.controller;

import lombok.extern.slf4j.Slf4j;
import org.develcorp.services.customer.error.BindingError;
import org.develcorp.services.customer.error.Error;
import org.develcorp.services.customer.error.ExceptionError;
import org.develcorp.services.customer.error.Message;
import org.develcorp.services.customer.model.dto.CustomerDto;
import org.develcorp.services.customer.services.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(value = "/customers")
@Slf4j
public class CustomerController {

    @Autowired
    private CustomerService customerService;

    @GetMapping (value = "/{id}")
    public ResponseEntity<?> findById(@PathVariable("id") Long id){
        log.info("findById --> GET: /api/customers/?id");

        CustomerDto customerDto = customerService.findByCustomer(id);
        if (customerDto == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new Message("C001", "Customer with identification " + id + " not found").toString());
        } else {
            return new ResponseEntity<>(customerDto, HttpStatus.OK);
        }
    }

    @GetMapping
    public ResponseEntity<?> getAllCustomer(@RequestParam(name = "name", required = false) String name){
        log.info("getAllCustomer --> GET: /api/customers");

        List<CustomerDto> customerDtoList;
        if (null == name){
            customerDtoList = customerService.getAllCustomers();
        }else {
            customerDtoList = customerService.findByName(name);
        }
        if (customerDtoList.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new Message("C002", "Empty data.").toString());
        } else {
            return ResponseEntity.status(HttpStatus.OK).body(customerDtoList);
        }
    }

    @PostMapping("/save")
    public ResponseEntity<?> saveCustomer(@RequestBody @Valid CustomerDto customerDto, BindingResult bindingResult) {
        log.info("saveCustomer --> GET: /api/customers/save");

        if(bindingResult.hasErrors()){
            return ResponseEntity.status(HttpStatus.PARTIAL_CONTENT).body(new ExceptionError(HttpStatus.NOT_FOUND.value(), new Error("VC1", new BindingError().getMessage(bindingResult))).getError().toString());
        }

        try {
            customerDto = customerService.saveCustomer(customerDto);
            return ResponseEntity.status(HttpStatus.OK).body(customerDto);
        } catch (ExceptionError e) {
            return ResponseEntity.status(HttpStatus.PRECONDITION_FAILED).body(e.getError().toString());
        }
    }

    @PutMapping("/update")
    public ResponseEntity<?> update(@RequestBody @Valid CustomerDto customerDto, BindingResult bindingResult) {
        log.info("updateCustomer --> GET: /api/customers/update");

        if(bindingResult.hasErrors()){
            return ResponseEntity.status(HttpStatus.PARTIAL_CONTENT).body(new ExceptionError(HttpStatus.NOT_FOUND.value(), new Error("VC1", new BindingError().getMessage(bindingResult))).getError().toString());
        }

        try {
            customerDto = customerService.updateCustomer(customerDto);
            return ResponseEntity.status(HttpStatus.OK).body(customerDto);
        } catch (ExceptionError e) {
            return ResponseEntity.status(HttpStatus.PRECONDITION_FAILED).body(e.getError().toString());
        }
    }

    @DeleteMapping("/delete")
    public ResponseEntity<?> deleteCustomer(@RequestParam(name = "id") Long id) {
        log.info("Client delete Endpoint GET: /api/customers/delete");

        try {
            customerService.deleteCustomer(id);
            return ResponseEntity.status(HttpStatus.OK).body(new Message("003", "Customer was be deleted.").toString());
        } catch (ExceptionError e) {
            return ResponseEntity.status(HttpStatus.PRECONDITION_FAILED).body(e.getError().toString());
        }
    }
}