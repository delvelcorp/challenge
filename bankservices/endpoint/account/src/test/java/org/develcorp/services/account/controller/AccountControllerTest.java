package org.develcorp.services.account.controller;

import org.develcorp.services.account.mockData.AccountMock;
import org.develcorp.services.account.model.dto.AccountDto;
import org.develcorp.services.account.services.impl.AccountServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.validation.BindingResult;

import java.util.List;

@SpringBootTest
@ContextConfiguration(classes = {AccountControllerTest.class})
public class AccountControllerTest {

    @Mock
    AccountServiceImpl accountService;

    @InjectMocks
    private AccountController accountController;

    @MockBean
    private BindingResult bindingResult;

    private final Long id = 1L;

    @Test
    void whenFindAllThenGetResponseOk(){
        Mockito.when(accountService.listAllAccounts()).thenReturn((List<AccountDto>) AccountMock.getAccountDto());

        Assertions.assertEquals(accountController.listAllAccounts().getStatusCode(), HttpStatus.OK);
    }

    @Test
    void whenFindByIdThenGetResponseOk(){
        Mockito.when(accountService.byAccountNumber(Mockito.anyLong())).thenReturn(AccountMock.getAccountDto());

        Assertions.assertEquals(accountController.findByAccountId(id).getStatusCode(), HttpStatus.OK);
    }

    @Test
    void whenSaveThenGetResponseOk(){
        Mockito.when(accountService.saveAccount(Mockito.any())).thenReturn(AccountMock.getAccountDto());

        Assertions.assertEquals(accountController.saveAccount(AccountMock.getAccountDto(), bindingResult).getStatusCode(), HttpStatus.OK);
    }

    @Test
    void whenUpdateThenGetResponseOk(){
        Mockito.when(accountService.updateAccount(Mockito.any())).thenReturn(AccountMock.getAccountDto());

        Assertions.assertEquals(accountController.updateAccount(AccountMock.getAccountDto(), bindingResult).getStatusCode(), HttpStatus.OK);
    }

    @Test
    void whenDeleteThenGetResponseOk(){
        Mockito.when(accountService.deleteAccount(Mockito.anyLong())).thenReturn(AccountMock.getAccountDto());

        Assertions.assertEquals(accountController.deleteAccount(id).getStatusCode(), HttpStatus.OK);
    }
}
