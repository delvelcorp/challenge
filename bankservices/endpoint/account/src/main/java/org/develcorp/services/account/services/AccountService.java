package org.develcorp.services.account.services;

import org.develcorp.services.account.model.dto.AccountDto;
import org.develcorp.services.account.model.dto.ReportDto;
import java.util.List;

public interface AccountService {

    List<AccountDto> listAllAccounts();

    AccountDto byAccountNumber(Long id);

    AccountDto saveAccount(AccountDto account);

    AccountDto updateAccount(AccountDto account);

    AccountDto deleteAccount(Long id);

    List<AccountDto> findByCustomer(Long customerId);

    List<ReportDto> getReport(Long customerId, String fromDate, String toDate);
}
