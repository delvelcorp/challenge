package org.develcorp.services.customer.model.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class CustomerDto extends PersonDto {

    @NotNull(message = "Password is required.")
    @Size(min = 4, max = 12, message = "Should specify 8 to 12 characters.")
    private String password;

    @NotNull(message = "Status is required.")
    private boolean status;

    private List<?> accounts;
}