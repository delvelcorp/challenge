package org.develcorp.services.transaction.controller;

import lombok.extern.slf4j.Slf4j;
import org.develcorp.services.transaction.error.BindingError;
import org.develcorp.services.transaction.error.Error;
import org.develcorp.services.transaction.error.ExceptionError;
import org.develcorp.services.transaction.error.Message;
import org.develcorp.services.transaction.model.dto.TransactionDto;
import org.develcorp.services.transaction.services.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping(value = "/transactions")
@Slf4j
public class TransactionController {

    @Autowired
    private TransactionService transactionService;

    @GetMapping (value = "/{id}")
    public ResponseEntity<?> getByTransactionId(@PathVariable("id") Long id) {
        log.info("findByTransactionId --> GET: /api/transactions/?id");

        TransactionDto transactionDto = transactionService.byTransactionId(id);
        if (transactionDto == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new Message("C001", "Transaction with id " + id + " not found").toString());
        } else {
            return new ResponseEntity<>(transactionDto, HttpStatus.OK);
        }
    }

    @GetMapping
    public ResponseEntity<?> listAllTransactions(Long accountNumber){
        log.info("listAllTransactions --> GET: /api/transactions");

        List<TransactionDto> transactionDtoList;
        if (null == accountNumber){
            transactionDtoList = transactionService.listAllTransactions();
        }else {
            transactionDtoList = transactionService.findByAccountNumber(accountNumber);
        }
        if (transactionDtoList.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new Message("C002", "Empty data.").toString());
        } else {
            return ResponseEntity.status(HttpStatus.OK).body(transactionDtoList);
        }
    }

    @GetMapping(value = "/report")
    public ResponseEntity<?> getReport(@RequestParam(name = "cliente") Long accountNumber,
                                       @RequestParam(name = "desde") String fromDate,
                                       @RequestParam(name = "hasta") String toDate){
        List<TransactionDto> transactionDtoList;

        Date from;
        Date to;

        try {
            from = new SimpleDateFormat("yyyy-MM-dd").parse(fromDate);
            to = new SimpleDateFormat("yyyy-MM-dd").parse(toDate);
        } catch (ParseException e) {
            throw new UnsupportedOperationException(e);
        }

        transactionDtoList = transactionService.findByAccountNumberAndDateBetween(accountNumber, from, to);

        if(transactionDtoList == null || transactionDtoList.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new Message("C001", "Transactions not found").toString());

        }
        return new ResponseEntity<>(transactionDtoList, HttpStatus.OK);
    }

    @GetMapping (path = "/account")
    public ResponseEntity<?> getLastTransactionByAccount(@RequestParam(name = "accountNumber") Long accoutId){
        log.info("getLastTransactionByAccount --> GET: /api/transactions/?accountNumber");

        TransactionDto transactionDto = transactionService.findByAccountNumberLast(accoutId);
        if (transactionDto == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new Message("C001", "Transaction with id " + accoutId + " not found").toString());
        } else {
            return new ResponseEntity<>(transactionDto, HttpStatus.OK);
        }
    }

    @PostMapping(value = "/opening")
    public ResponseEntity<?> postInitialTransaction(@Valid @RequestBody TransactionDto transactionDto, BindingResult bindingResult){
        log.info("postInitialTransaction --> POST: /api/opening");

        if(bindingResult.hasErrors()){
            return ResponseEntity.status(HttpStatus.PARTIAL_CONTENT).body(new ExceptionError(HttpStatus.NOT_FOUND.value(), new Error("VC1", new BindingError().getMessage(bindingResult))).getError().toString());
        }

        try {
            transactionDto = transactionService.postInitialTransaction(transactionDto);
            return ResponseEntity.status(HttpStatus.CREATED).body(transactionDto);
        } catch (ExceptionError e) {
            return ResponseEntity.status(HttpStatus.PRECONDITION_FAILED).body(e.getError().toString());
        }
    }

    @PostMapping(value = "/save")
    public ResponseEntity<?> saveTransaction(@Valid @RequestBody TransactionDto transactionDto, BindingResult bindingResult){
        log.info("saveTransaction --> GET: /api/transactions/save");

        if(bindingResult.hasErrors()){
            return ResponseEntity.status(HttpStatus.PARTIAL_CONTENT).body(new ExceptionError(HttpStatus.NOT_FOUND.value(), new Error("VC1", new BindingError().getMessage(bindingResult))).getError().toString());
        }

        try {
            transactionDto = transactionService.saveTransaction(transactionDto);
            return ResponseEntity.status(HttpStatus.OK).body(transactionDto);
        } catch (ExceptionError e) {
            return ResponseEntity.status(HttpStatus.PRECONDITION_FAILED).body(e.getError().toString());
        }
    }

    @PutMapping(value = "/update")
    public ResponseEntity<?> updateTransaction(@RequestBody @Valid TransactionDto transactionDto, BindingResult bindingResult) {
        log.info("updateTransaction --> GET: /api/transactions/update");

        if(bindingResult.hasErrors()){
            return ResponseEntity.status(HttpStatus.PARTIAL_CONTENT).body(new ExceptionError(HttpStatus.NOT_FOUND.value(), new Error("VC1", new BindingError().getMessage(bindingResult))).getError().toString());
        }

        try {
            transactionDto = transactionService.updateTransaction(transactionDto);
            return ResponseEntity.status(HttpStatus.OK).body(transactionDto);
        } catch (ExceptionError e) {
            return ResponseEntity.status(HttpStatus.PRECONDITION_FAILED).body(e.getError().toString());
        }
    }

    @DeleteMapping("delete")
    public ResponseEntity<?> deleteTransaction(@RequestParam(name = "id") Long id) {
        log.info("deleteTransaction --> DELETE: /api/transactions/delete");

        try {
            transactionService.deleteTransaction(id);
            return ResponseEntity.status(HttpStatus.OK).body(new Message("003", "Transaction was be deleted.").toString());
        } catch (ExceptionError e) {
            return ResponseEntity.status(HttpStatus.PRECONDITION_FAILED).body(e.getError().toString());
        }
    }
}