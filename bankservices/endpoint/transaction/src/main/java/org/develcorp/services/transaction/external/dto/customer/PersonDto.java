package org.develcorp.services.transaction.external.dto.customer;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.develcorp.services.transaction.external.dto.customer.enums.Gender;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class PersonDto {

    private Long id;
    private String name;
    private Gender gender;
    private Integer age;
    private String identification;
    private String address;
    private String phone;

}
