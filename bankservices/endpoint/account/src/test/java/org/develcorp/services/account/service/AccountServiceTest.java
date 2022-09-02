package org.develcorp.services.account.service;

import org.develcorp.services.account.error.ExceptionError;
import org.develcorp.services.account.mapper.AccountMapper;
import org.develcorp.services.account.mockData.AccountMock;
import org.develcorp.services.account.repository.AccountRepository;
import org.develcorp.services.account.services.impl.AccountServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;

import java.util.List;
import java.util.Optional;

@SpringBootTest
@ContextConfiguration(classes = {AccountServiceTest.class})
public class AccountServiceTest {

    @Mock
    private AccountRepository accountRepository;

    @Mock
    AccountMapper accountMapper;

    @InjectMocks
    private AccountServiceImpl accountService;

    private final Long id = 1L;

    @Test
    void whenFindAllThenGetAccountList(){
        Mockito.when(accountRepository.findAll()).thenReturn(List.of(AccountMock.getAccount()));
        Mockito.when(accountMapper.AccountToAccountDto(Mockito.any())).thenReturn(AccountMock.getAccountDto());

        Assertions.assertNotNull(accountService.getAllAccounts());
    }

    @Test
    void whenFindByIdThenGetAccount(){
        Mockito.when(accountRepository.findById(Mockito.anyLong())).thenReturn(Optional.of(AccountMock.getAccount()));
        Mockito.when(accountMapper.AccountToAccountDto(Mockito.any())).thenReturn(AccountMock.getAccountDto());

        Assertions.assertNotNull(accountService.getByAccountId(id));
    }

    @Test
    void whenSaveThenGetAccountPersist(){
        Mockito.when(accountRepository.findById(Mockito.anyLong())).thenReturn(null);
        Mockito.when(accountRepository.save(Mockito.any())).thenReturn(AccountMock.getAccount());

        Mockito.when(accountMapper.AccountToAccountDto(Mockito.any())).thenReturn(AccountMock.getAccountDto());
        Mockito.when(accountMapper.AccountDtoToAccount(Mockito.any())).thenReturn(AccountMock.getAccount());

        Assertions.assertNotNull(accountService.saveAccount(AccountMock.getAccountDto()));
    }

    @Test
    void whenSaveThenGetAccountError(){
        Mockito.when(accountRepository.findById(Mockito.anyLong())).thenReturn(Optional.of(AccountMock.getAccount()));
        Mockito.when(accountRepository.save(Mockito.any())).thenReturn(AccountMock.getAccount());

        Mockito.when(accountMapper.AccountToAccountDto(Mockito.any())).thenReturn(AccountMock.getAccountDto());
        Mockito.when(accountMapper.AccountDtoToAccount(Mockito.any())).thenReturn(AccountMock.getAccount());

        ExceptionError thrown = Assertions.assertThrows
                (ExceptionError.class, () -> accountService.saveAccount(AccountMock.getAccountDto()));

        Assertions.assertEquals("E002", thrown.getError().getCode());
    }

    @Test
    void whenUpdateThenGetAccountModified(){
        Mockito.when(accountRepository.findById(Mockito.anyLong())).thenReturn(Optional.of(AccountMock.getAccount()));
        Mockito.when(accountRepository.save(Mockito.any())).thenReturn(AccountMock.getAccount());

        Mockito.when(accountMapper.AccountToAccountDto(Mockito.any())).thenReturn(AccountMock.getAccountDto());
        Mockito.when(accountMapper.AccountDtoToAccount(Mockito.any())).thenReturn(AccountMock.getAccount());

        Assertions.assertNotNull(accountService.updateAccount(AccountMock.getAccountDto()));
    }

    @Test
    void whenDeleteThenGetTrue(){
        Mockito.when(accountRepository.findById(Mockito.anyLong())).thenReturn(Optional.of(AccountMock.getAccount()));
        Mockito.when(accountMapper.AccountDtoToAccount(Mockito.any())).thenReturn(AccountMock.getAccount());

        accountService.deleteAccount(id);
    }
}