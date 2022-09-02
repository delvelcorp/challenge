package org.develcorp.services.account.mockData;

import org.develcorp.services.account.model.dto.AccountDto;
import org.develcorp.services.account.model.entity.Account;
import org.develcorp.services.account.model.enums.AccountType;

import java.math.BigDecimal;

public class AccountMock {

    public static Account getAccount() {
        Account account = new Account();
        account.setAccountNumber(1L);
        account.setCustomerId(1L);
        account.setAccountType(AccountType.C);
        account.setInitialBalance(BigDecimal.valueOf(100));
        account.setStatus(true);
        account.setTransactions(null);

        return account;
    }

    public static AccountDto getAccountDto() {
        AccountDto accountDto = new AccountDto();
        accountDto.setAccountNumber(1L);
        accountDto.setCustomerId(1L);
        accountDto.setAccountType(AccountType.C);
        accountDto.setInitialBalance(BigDecimal.valueOf(100));
        accountDto.setStatus(true);
        accountDto.setTransactions(null);

        return accountDto;
    }
}
