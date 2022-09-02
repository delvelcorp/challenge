package org.develcorp.services.account.mapper;

import org.develcorp.services.account.mockData.AccountMock;
import org.develcorp.services.account.model.dto.AccountDto;
import org.develcorp.services.account.model.entity.Account;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;

@SpringBootTest
@ContextConfiguration(classes = {AccountMapperTest.class})
public class AccountMapperTest {

    private final AccountMapper accountMapper = Mappers.getMapper(AccountMapper.class);

    @Test
    void whenEntityThenDto(){
        Account account = AccountMock.getAccount();
        AccountDto accountDto = accountMapper.AccountToAccountDto(account);

        Assertions.assertEquals(account.getInitialBalance(), accountDto.getInitialBalance());
    }

    @Test
    void whenDtoThenEntity(){
        AccountDto accountDto = AccountMock.getAccountDto();
        Account account = accountMapper.AccountDtoToAccount(accountDto);

        Assertions.assertEquals(account.getAccountType(), accountDto.getAccountType());
    }

}
