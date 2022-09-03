package org.develcorp.services.transaction.services;

import org.develcorp.services.transaction.model.dto.TransactionDto;
import org.develcorp.services.transaction.model.enums.TransactionType;

import java.util.Date;
import java.util.List;

public interface TransactionService {

    List<TransactionDto> listAllTransactions();

    TransactionDto getTransaction(Long id);

    TransactionDto openTransaction(TransactionDto transactionDto);

    TransactionDto saveTransaction(TransactionDto transactionDto);

    TransactionDto updateTransaction(TransactionDto transactionDto);

    TransactionDto deleteTransaction(Long id);

    List<TransactionDto> findByAccountId(Long accountId);

    TransactionDto findByAccountIdLast(Long accountId);

    List<TransactionDto> findByAccountIdAndDateAndTransactionType(Long accountId, Date date, TransactionType transactionType);

    List<TransactionDto> findByAccountIdAndDateBetween(Long accountId, Date fromDate, Date toDate);
}