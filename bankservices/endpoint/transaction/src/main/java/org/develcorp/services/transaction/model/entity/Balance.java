package org.develcorp.services.transaction.model.entity;

import lombok.Data;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;

@Entity
@Data
public class Balance {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String customer;

    @Temporal(TemporalType.TIMESTAMP)
    private Date date;

    private Long accountNumber;

    private String accountType;

    private BigDecimal initialBalance;

    private boolean status;

    private BigDecimal value;

    private BigDecimal balance;
}
