package org.develcorp.services.transaction.services;

import org.develcorp.services.transaction.model.dto.TransactionDto;
import org.develcorp.services.transaction.model.enums.TransactionType;

import java.util.Date;
import java.util.List;

public interface TransactionService {

    List<TransactionDto> listAllTransactions();

    TransactionDto byTransactionId(Long id);

    TransactionDto postInitialTransaction(TransactionDto transactionDto);

    TransactionDto saveTransaction(TransactionDto transactionDto);

    TransactionDto updateTransaction(TransactionDto transactionDto);

    TransactionDto deleteTransaction(Long id);

    List<TransactionDto> findByAccountNumber(Long accountNumber);

    TransactionDto findByAccountNumberLast(Long accountNumber);

    List<TransactionDto> findByAccountNumberAndDateAndTransactionType(Long accountNumber, Date date, TransactionType transactionType);

    List<TransactionDto> findByAccountNumberAndDateBetween(Long accountNumber, Date fromDate, Date toDate);
}