package org.example.jparelationi.DTO;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class StudentDTO {

    @NotEmpty(message = "Please enter a name")
    @Size(max = 100,message = "name can't be more than 100 characters")
    private String name;

    @NotNull(message = "Please enter an age")
    @Min(value = 18,message = "age can't be less than 18")
    private Integer age;

    @NotEmpty(message = "Please enter a major")
    private String major;

    private List<CourseDTO> courses;
}
