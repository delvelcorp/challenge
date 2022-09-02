package org.develcorp.services.transaction.services;

import org.develcorp.services.transaction.model.dto.BalanceDto;

public interface BalanceService {

    BalanceDto findById(Long id);

    BalanceDto saveBalance(BalanceDto balanceDto);

    BalanceDto updateBalance(BalanceDto balanceDto);

    BalanceDto findByAccountNumber(Long accountNumber);
}