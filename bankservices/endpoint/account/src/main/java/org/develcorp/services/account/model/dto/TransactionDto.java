package org.develcorp.services.account.model.dto;

import lombok.Data;
import org.develcorp.services.account.model.enums.TransactionType;

import java.math.BigDecimal;
import java.util.Date;

@Data
public class TransactionDto {

    private Long id;

    private Long accountId;

    private Date date;

    private TransactionType transactionType;

    private BigDecimal value;

    private BigDecimal balance;

    private boolean status;

    private String message;
}
