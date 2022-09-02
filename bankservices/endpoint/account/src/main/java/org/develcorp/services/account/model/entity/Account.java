package org.develcorp.services.account.model.entity;

import lombok.*;
import org.develcorp.services.account.model.enums.AccountType;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Table(name = "ACCOUNTS")
@Entity
public class Account {

    @Id
    private Long accountNumber;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 1)
    private AccountType accountType;

    @Column(nullable = false)
    private BigDecimal initialBalance;

    @Column(nullable = false)
    private boolean status;

    @Transient
    private List<?> transactions;

    @Column(nullable = false)
    private Long customerId;
}
