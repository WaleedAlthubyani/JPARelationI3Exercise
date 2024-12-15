package org.example.jparelationi.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class TeacherDTO {

    private String name;
    private Integer age;
    private String email;
    private Double salary;
    private AddressODTO address;
    private List<CourseDTO> courses;
}
