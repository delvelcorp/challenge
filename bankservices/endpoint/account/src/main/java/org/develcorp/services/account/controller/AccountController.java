package org.develcorp.services.account.controller;

import lombok.extern.slf4j.Slf4j;
import org.develcorp.services.account.error.BindingError;
import org.develcorp.services.account.error.Error;
import org.develcorp.services.account.error.ExceptionError;
import org.develcorp.services.account.error.Message;
import org.develcorp.services.account.model.dto.AccountDto;
import org.develcorp.services.account.services.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import java.net.UnknownHostException;
import java.util.List;

@RestController
@RequestMapping(value = "/accounts")
@Slf4j
public class AccountController {

    @Autowired
    private AccountService accountService;

    @GetMapping (value = "/{id}")
    public ResponseEntity<?> getByAccountId(@PathVariable("id") Long id){
        log.info("AccountById --> GET: /api/customers/?identification");

        AccountDto accountDto = accountService.getByAccountId(id);
        if (accountDto == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new Message("C001", "Account with id " + id + " not found").toString());
        } else {
            return new ResponseEntity<>(accountDto, HttpStatus.OK);
        }
    }

    @GetMapping
    public ResponseEntity<?> getAllAccounts(){
        log.info("Clients list Endpoint GET: /api/accounts");

        List<AccountDto> accountDtos = accountService.getAllAccounts();
        if (accountDtos.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new Message("C002", "Empty data.").toString());
        } else {
            return ResponseEntity.status(HttpStatus.OK).body(accountDtos);
        }
    }


    @GetMapping(value = "/customer/{customerId}")
    public ResponseEntity<?> listAccountCustomer(@RequestParam(name = "customerId") Long customerId){
        List<AccountDto> accounts = accountService.findByCustomer(customerId);

        if(accounts == null || accounts.isEmpty()){
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(accounts);
    }

    @PostMapping("/save")
    public ResponseEntity<?> saveAccount(@Valid @RequestBody AccountDto accountDto, BindingResult bindingResult) {
        log.info("saveAccounts --> POST: /api/accounts/save");

        if(bindingResult.hasErrors()){
            return ResponseEntity.status(HttpStatus.PARTIAL_CONTENT).body(new ExceptionError(HttpStatus.NOT_FOUND.value(), new Error("VC1", new BindingError().getMessage(bindingResult))).getError().toString());
        }

        try {
            accountDto = accountService.saveAccount(accountDto);
            return ResponseEntity.status(HttpStatus.OK).body(accountDto);
        } catch (ExceptionError e) {
            return ResponseEntity.status(HttpStatus.PRECONDITION_FAILED).body(e.getError().toString());
        } catch (UnknownHostException e) {
            throw new RuntimeException(e);
        }
    }

    @PutMapping(value = "/update")
    public ResponseEntity<?> updateAccount(@RequestBody @Valid AccountDto accountDto, BindingResult bindingResult) {
        log.info("updateAccount --> PUT: /api/accounts/update");

        if(bindingResult.hasErrors()){
            return ResponseEntity.status(HttpStatus.PARTIAL_CONTENT).body(new ExceptionError(HttpStatus.NOT_FOUND.value(), new Error("VC1", new BindingError().getMessage(bindingResult))).getError().toString());
        }

        try {
            accountDto = accountService.updateAccount(accountDto);
            return ResponseEntity.status(HttpStatus.OK).body(accountDto);
        } catch (ExceptionError e) {
            return ResponseEntity.status(HttpStatus.PRECONDITION_FAILED).body(e.getError().toString());
        }
    }

    @DeleteMapping("/delete")
    public ResponseEntity<?> deleteAccount(@RequestParam(name = "id") Long id){
        log.info("deleteAccount --> DELETE: /api/accounts/delete");

        try {
            accountService.deleteAccount(id);
            return ResponseEntity.status(HttpStatus.OK).body(new Message("003", "Customer was be deleted.").toString());
        } catch (ExceptionError e) {
            return ResponseEntity.status(HttpStatus.PRECONDITION_FAILED).body(e.getError().toString());
        }
    }

    @GetMapping(value = "/report")
    public ResponseEntity<?> generateReport(@RequestParam(name = "customer") Long accountId,
                                                 @RequestParam(name = "from") String fromDate,
                                                 @RequestParam(name = "to") String toDate){
        log.info("report --> GET: /api/accounts/report");

        try {
            return ResponseEntity.ok(accountService.getReport(accountId, fromDate, toDate));
        } catch (ExceptionError e) {
            return ResponseEntity.status(HttpStatus.PRECONDITION_FAILED).body(e.getError().toString());
        }
    }
}