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
    public ResponseEntity<?> getTransaction(@RequestParam(name = "id") Long id){
        log.info("TransactionById --> GET: /api/transactions/?id");

        TransactionDto transactionDto = transactionService.getTransaction(id);
        if (transactionDto == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new Message("C001", "Transaction with id " + id + " not found").toString());
        } else {
            return new ResponseEntity<>(transactionDto, HttpStatus.OK);
        }
    }

    @GetMapping
    public ResponseEntity<?> listTransactions(Long accountId){
        log.info("getAllTransaction --> GET: /api/transactions");

        List<TransactionDto> transactionDtoList;
        if (null == accountId){
            transactionDtoList = transactionService.listAllTransactions();
        }else {
            transactionDtoList = transactionService.findByAccountId(accountId);
        }
        if (transactionDtoList.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new Message("C002", "Empty data.").toString());
        } else {
            return ResponseEntity.status(HttpStatus.OK).body(transactionDtoList);
        }
    }

    @GetMapping(value = "/report")
    public ResponseEntity<?> reportList(@RequestParam(name = "cliente") Long accountId,
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

        transactionDtoList = transactionService.findByAccountIdAndDateBetween(accountId, from, to);

        if(transactionDtoList == null || transactionDtoList.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new Message("C001", "Transactions not found").toString());

        }
        return new ResponseEntity<>(transactionDtoList, HttpStatus.OK);
    }

    @GetMapping (path = "/account")
    public ResponseEntity<?> getLastTransactionByAccount(@RequestParam(name = "accountId") Long accoutId){
        log.info("TransactionById --> GET: /api/transactions/?accountId");

        TransactionDto transactionDto = transactionService.findByAccountIdLast(accoutId);
        if (transactionDto == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new Message("C001", "Transaction with id " + accoutId + " not found").toString());
        } else {
            return new ResponseEntity<>(transactionDto, HttpStatus.OK);
        }
    }

    @PostMapping(value = "/opening")
    public ResponseEntity<?> openTransaction(@Valid @RequestBody TransactionDto transactionDto, BindingResult bindingResult){
        log.info("openTransaction --> POST: /api/opening");

        if(bindingResult.hasErrors()){
            System.out.println("Ingresa al error");
            System.out.println(new BindingError().getMessage(bindingResult).toString());
            return ResponseEntity.status(HttpStatus.PARTIAL_CONTENT).body(new ExceptionError(HttpStatus.NOT_FOUND.value(), new Error("VC1", new BindingError().getMessage(bindingResult))).getError().toString());
        }

        try {
            System.out.println("Pasa al try");
            transactionDto = transactionService.openTransaction(transactionDto);
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
        log.info("Client delete Endpoint GET: /api/transactions/delete");

        try {
            transactionService.deleteTransaction(id);
            return ResponseEntity.status(HttpStatus.OK).body(new Message("003", "Transaction was be deleted.").toString());
        } catch (ExceptionError e) {
            return ResponseEntity.status(HttpStatus.PRECONDITION_FAILED).body(e.getError().toString());
        }
    }
}