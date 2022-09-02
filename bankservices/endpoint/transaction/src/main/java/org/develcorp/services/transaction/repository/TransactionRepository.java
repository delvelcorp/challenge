package org.develcorp.services.transaction.repository;

import org.develcorp.services.transaction.model.entity.Transaction;
import org.develcorp.services.transaction.model.enums.TransactionType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, Long> {

    List<Transaction> findByAccountNumber(Long accountNumber);

    List<Transaction> findByAccountNumberAndDateAndTransactionType
            (Long accountNumber, Date date, TransactionType transactionType);

    List<Transaction> findByAccountNumberAndDateBetween
            (Long accountNumber, Date fromDate, Date toDate);
}
