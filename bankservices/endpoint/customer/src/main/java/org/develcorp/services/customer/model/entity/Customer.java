package org.develcorp.services.customer.model.entity;

import lombok.*;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Table(name = "CUSTOMERS")
@Entity
public class Customer extends Person implements Serializable {

    @Column (length = 12, nullable = false)
    private String password;

    @Column (nullable = false)
    private boolean status;

    @Transient
    List<?> accounts;
}
