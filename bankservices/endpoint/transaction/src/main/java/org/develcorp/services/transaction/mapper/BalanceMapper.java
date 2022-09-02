package org.develcorp.services.transaction.mapper;

import org.develcorp.services.transaction.model.dto.BalanceDto;
import org.develcorp.services.transaction.model.entity.Balance;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface BalanceMapper {

    Balance BalanceDtoToBalance(BalanceDto balanceDto);

    BalanceDto BalanceToBalanceDto(Balance balance);
}
