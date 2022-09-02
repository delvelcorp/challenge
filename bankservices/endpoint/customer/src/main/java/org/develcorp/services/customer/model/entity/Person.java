package org.develcorp.services.customer.model.entity;

import lombok.*;
import org.develcorp.services.customer.model.enums.Gender;
import javax.persistence.*;

@MappedSuperclass
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class Person {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column (length = 100, nullable = false)
    private String name;

    @Column (length = 15, nullable = false)
    @Enumerated(EnumType.STRING)
    private Gender gender;

    @Column (nullable = false)
    private Integer age;

    @Column (unique = true, length = 10, nullable = false)
    private String identification;

    @Column (length = 100, nullable = false)
    private String address;

    @Column (length = 10, nullable = false)
    private String phone;
}
