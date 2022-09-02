package org.develcorp.services.transaction.model.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.develcorp.services.transaction.model.enums.TransactionType;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Getter @Setter
public class TransactionDto {

    private Long id;

    @NotNull (message = "Date is required.")
    private Date date;

    @NotNull(message = "Transaction type is required.")
    private TransactionType transactionType;

    @NotNull(message = "Value is required.")
    private BigDecimal value;

    private BigDecimal balance;

    private Boolean status;

    private String message;

    @NotNull (message = "Account is required.")
    private Long accountNumber;
}
