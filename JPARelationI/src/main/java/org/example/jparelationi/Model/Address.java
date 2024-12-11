package org.example.jparelationi.Model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@Entity
@NoArgsConstructor
public class Address {

    @Id
    private Integer id;

    @NotEmpty(message = "Please enter an area")
    @Column(columnDefinition = "varchar(100) not null")
    private String area;

    @NotEmpty(message = "Please enter street")
    @Column(columnDefinition = "varchar(100) not null")
    private String street;

    @NotEmpty(message = "Please enter building number")
    @Pattern(regexp = "^[0-9]+$")
    @Column(columnDefinition = "varchar(10) not null")
    private String buildingNumber;

    @OneToOne
    @MapsId
    @JsonIgnore
    private Teacher teacher;
}
