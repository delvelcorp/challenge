package org.develcorp.services.account.model.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.develcorp.services.account.model.enums.AccountType;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.math.BigDecimal;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter @Setter
public class AccountDto {

    private Long accountNumber;

    @NotNull(message = "Account type is required.")
    @Enumerated(EnumType.STRING)
    private AccountType accountType;

    @NotNull(message = "Initial value is required.")
    @Positive(message = "The balance should be more than 0.")
    private BigDecimal initialBalance;

    @NotNull(message = "Status is required.")
    private boolean status;

    private List<?> transactions;

    @NotNull(message = "Customer is required.")
    private Long customerId;
}