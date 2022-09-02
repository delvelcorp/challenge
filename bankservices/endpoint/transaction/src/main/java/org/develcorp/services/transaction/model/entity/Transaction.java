package org.develcorp.services.transaction.model.entity;

import lombok.*;
import org.develcorp.services.transaction.model.enums.TransactionType;

import javax.persistence.*;
import javax.validation.constraints.Positive;
import java.math.BigDecimal;
import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Table(name = "TRANSACTIONS")
@Entity
public class Transaction {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(length = 100, nullable = false)
    @Temporal(TemporalType.DATE)
    private Date date;

    @Column (length = 15, nullable = false)
    @Enumerated(EnumType.STRING)
    private TransactionType transactionType;

    @Positive(message = "El valor debe ser superior a 0")
    private BigDecimal value;

    private BigDecimal balance;

    private boolean status;

    private String message;

    private Long accountId;
}
