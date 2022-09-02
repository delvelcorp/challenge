package org.develcorp.services.account.mapper;

import org.develcorp.services.account.model.dto.AccountDto;
import org.develcorp.services.account.model.entity.Account;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface AccountMapper {

    Account AccountDtoToAccount(AccountDto accountDto);

    AccountDto AccountToAccountDto(Account account);
}
