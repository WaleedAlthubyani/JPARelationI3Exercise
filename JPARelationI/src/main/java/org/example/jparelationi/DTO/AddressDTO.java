package org.example.jparelationi.DTO;

import jakarta.persistence.Column;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class AddressDTO {

    @NotNull(message = "Please enter teacher id")
    private Integer teacher_id;

    @NotEmpty(message = "Please enter an area")
    private String area;

    @NotEmpty(message = "Please enter street")
    private String street;

    @NotEmpty(message = "Please enter building number")
    @Pattern(regexp = "^[0-9]+$")
    private String buildingNumber;
}
