package org.develcorp.services.account.model.dto;

import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

@Data
public class ReportDto {

    private Date date;
    private String account;
    private String customer;
    private Long accountNumber;
    private String accountType;
    private BigDecimal initialBalance;
    private boolean status;
    private BigDecimal transactionValue;
    private BigDecimal transactionBalance;
}