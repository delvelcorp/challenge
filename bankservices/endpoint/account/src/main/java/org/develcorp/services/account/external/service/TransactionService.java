package org.develcorp.services.account.external.service;

import org.develcorp.services.account.external.dto.transaction.TransactionDto;

public interface TransactionService {

    TransactionDto getTransactionId(Long id);
}
