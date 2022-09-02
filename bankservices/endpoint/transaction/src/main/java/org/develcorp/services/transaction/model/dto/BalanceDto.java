package org.develcorp.services.transaction.model.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Getter @Setter
public class BalanceDto {

    private Long id;

    private String customer;

    private Date date;

    private Long accountNumber;

    private String accountType;

    private BigDecimal initialBalance;

    private boolean status;

    private BigDecimal value;

    private BigDecimal balance;
}
