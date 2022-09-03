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
public class BalanceDto {

    private Long id;

    @NotNull (message = "Account is required.")
    private Long accountId;

    @NotNull (message = "Value is required.")
    private BigDecimal actualBalance;

    @NotNull(message = "Date is required.")
    private Date modifiedAt;
}
