package org.develcorp.services.transaction.mapper;

import javax.annotation.Generated;
import org.develcorp.services.transaction.model.dto.BalanceDto;
import org.develcorp.services.transaction.model.entity.Balance;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2022-09-02T11:47:10-0500",
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
        balance.setCustomer( balanceDto.getCustomer() );
        balance.setDate( balanceDto.getDate() );
        balance.setAccountNumber( balanceDto.getAccountNumber() );
        balance.setAccountType( balanceDto.getAccountType() );
        balance.setInitialBalance( balanceDto.getInitialBalance() );
        balance.setStatus( balanceDto.isStatus() );
        balance.setValue( balanceDto.getValue() );
        balance.setBalance( balanceDto.getBalance() );

        return balance;
    }

    @Override
    public BalanceDto BalanceToBalanceDto(Balance balance) {
        if ( balance == null ) {
            return null;
        }

        BalanceDto balanceDto = new BalanceDto();

        balanceDto.setId( balance.getId() );
        balanceDto.setCustomer( balance.getCustomer() );
        balanceDto.setDate( balance.getDate() );
        balanceDto.setAccountNumber( balance.getAccountNumber() );
        balanceDto.setAccountType( balance.getAccountType() );
        balanceDto.setInitialBalance( balance.getInitialBalance() );
        balanceDto.setStatus( balance.isStatus() );
        balanceDto.setValue( balance.getValue() );
        balanceDto.setBalance( balance.getBalance() );

        return balanceDto;
    }
}
