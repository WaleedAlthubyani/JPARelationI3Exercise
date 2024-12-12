package org.example.jparelationi.Model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.hibernate.annotations.Check;

import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@Entity
@NoArgsConstructor
@Check(constraints = "age>=18 and salary>=4000")
public class Teacher {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotEmpty(message = "Please enter a name")
    @Column(columnDefinition = "varchar(100) not null")
    private String name;

    @NotNull(message = "Please enter an age")
    @Min(value = 18,message = "Teacher age can't be less than 18")
    @Column(columnDefinition = "int not null")
    private Integer age;

    @NotEmpty(message = "Please enter an email")
    @Email(message = "Please enter a valid email address")
    @Column(columnDefinition = "varchar(100) not null unique")
    private String email;

    @NotNull(message = "Please enter salary")
    @Min(value = 4000,message = "Salary can't be less than 4000")
    @Column(columnDefinition = "double not null")
    private Double salary;

    @OneToOne(cascade = CascadeType.ALL,orphanRemoval = true)
    @PrimaryKeyJoinColumn
    private Address address;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "teacher")
    private Set<Course> courses;
}
