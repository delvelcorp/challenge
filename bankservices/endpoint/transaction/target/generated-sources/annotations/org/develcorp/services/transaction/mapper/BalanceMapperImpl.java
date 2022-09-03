package org.develcorp.services.transaction.mapper;

import javax.annotation.Generated;
import org.develcorp.services.transaction.model.dto.BalanceDto;
import org.develcorp.services.transaction.model.entity.Balance;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2022-09-02T17:03:43-0500",
    comments = "version: 1.5.2.Final, compiler: javac, environment: Java 11.0.15 (Oracle Corporation)"
)
@Component
public class BalanceMapperImpl implements BalanceMapper {

    @Override
    public Balance BalanceDtoToBalance(BalanceDto balanceDto) {
        if ( balanceDto == null ) {
            return null;
        }

        Balance balance = new Balance();

        balance.setId( balanceDto.getId() );
        balance.setAccountId( balanceDto.getAccountId() );
        balance.setActualBalance( balanceDto.getActualBalance() );
        balance.setModifiedAt( balanceDto.getModifiedAt() );

        return balance;
    }

    @Override
    public BalanceDto BalanceToBalanceDto(Balance balance) {
        if ( balance == null ) {
            return null;
        }

        BalanceDto balanceDto = new BalanceDto();

        balanceDto.setId( balance.getId() );
        balanceDto.setAccountId( balance.getAccountId() );
        balanceDto.setActualBalance( balance.getActualBalance() );
        balanceDto.setModifiedAt( balance.getModifiedAt() );

        return balanceDto;
    }
}
