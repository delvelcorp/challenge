package org.develcorp.services.account.services;

import org.develcorp.services.account.model.dto.AccountDto;
import org.develcorp.services.account.model.dto.ReportDto;

import java.net.UnknownHostException;
import java.util.List;

public interface AccountService {

    List<AccountDto> getAllAccounts();

    AccountDto getByAccountId(Long id);

    AccountDto saveAccount(AccountDto account) throws UnknownHostException;

    AccountDto updateAccount(AccountDto account);

    AccountDto deleteAccount(Long id);

    List<AccountDto> findByCustomer(Long customerId);

    List<ReportDto> getReport(Long customerId, String fromDate, String toDate);
}
