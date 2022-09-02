package org.develcorp.services.transaction.external.dto.account;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.develcorp.services.transaction.external.dto.account.enums.AccountType;
import java.math.BigDecimal;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter @Setter
public class AccountDto {

    private Long accountNumber;
    private AccountType accountType;
    private BigDecimal initialBalance;
    private boolean status;
    private List<?> transactions;
    private Long customerId;

}