package org.develcorp.services.account.external.dto.transaction;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.develcorp.services.account.external.dto.transaction.enums.TransactionType;

import java.math.BigDecimal;
import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Getter @Setter
public class TransactionDto {

    private Long id;
    private Date date;
    private TransactionType transactionType;
    private BigDecimal value;
    private Boolean status;
    private String message;
    private Long accountNumber;
}