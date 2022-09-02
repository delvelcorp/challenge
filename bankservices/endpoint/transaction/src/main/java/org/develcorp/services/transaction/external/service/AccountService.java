package org.develcorp.services.transaction.external.service;

import org.develcorp.services.transaction.external.dto.account.AccountDto;

import java.util.List;

public interface AccountService {

    AccountDto getAccountNumber(Long id);
}
