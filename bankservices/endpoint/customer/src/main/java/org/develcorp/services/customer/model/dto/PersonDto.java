package org.develcorp.services.customer.model.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.develcorp.services.customer.model.enums.Gender;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class PersonDto {

    private Long id;

    @NotNull (message = "Name is required.")
    private String name;

    @NotNull (message = "Gender is required.")
    private Gender gender;

    @NotNull(message = "Age is required.")
    @Positive(message = "Age has an incorrect value.")
    private Integer age;

    @NotNull(message = "Identification is required.")
    @Size(min = 10, max = 10, message = "Identification format is incorrect")
    private String identification;

    @NotNull (message = "Address is required.")
    private String address;

    @NotNull (message = "Phone is required.")
    @Size (min = 7, max = 10, message = "Phone format is incorrect")
    private String phone;

}
