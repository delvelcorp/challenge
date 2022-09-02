package org.develcorp.services.account.mapper;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.Generated;
import org.develcorp.services.account.model.dto.AccountDto;
import org.develcorp.services.account.model.entity.Account;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2022-09-02T11:44:52-0500",
    comments = "version: 1.5.2.Final, compiler: javac, environment: Java 11.0.15 (Oracle Corporation)"
)
@Component
public class AccountMapperImpl implements AccountMapper {

    @Override
    public Account AccountDtoToAccount(AccountDto accountDto) {
        if ( accountDto == null ) {
            return null;
        }

        Account account = new Account();

        account.setAccountNumber( accountDto.getAccountNumber() );
        account.setAccountType( accountDto.getAccountType() );
        account.setInitialBalance( accountDto.getInitialBalance() );
        account.setStatus( accountDto.isStatus() );
        List<?> list = accountDto.getTransactions();
        if ( list != null ) {
            account.setTransactions( new ArrayList<Object>( list ) );
        }
        account.setCustomerId( accountDto.getCustomerId() );

        return account;
    }

    @Override
    public AccountDto AccountToAccountDto(Account account) {
        if ( account == null ) {
            return null;
        }

        AccountDto accountDto = new AccountDto();

        accountDto.setAccountNumber( account.getAccountNumber() );
        accountDto.setAccountType( account.getAccountType() );
        accountDto.setInitialBalance( account.getInitialBalance() );
        accountDto.setStatus( account.isStatus() );
        List<?> list = account.getTransactions();
        if ( list != null ) {
            accountDto.setTransactions( new ArrayList<Object>( list ) );
        }
        accountDto.setCustomerId( account.getCustomerId() );

        return accountDto;
    }
}
