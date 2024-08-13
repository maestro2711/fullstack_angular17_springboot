package fr.afrogeek.Geekhrconnct.entity;

import fr.afrogeek.Geekhrconnct.dto.EmployeeResponse;
import fr.afrogeek.Geekhrconnct.enums.Gender;
import fr.afrogeek.Geekhrconnct.enums.Position;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
@Getter
@Setter
@Entity
@Table(name = "employees")

public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(columnDefinition = "UUID")
    private UUID id;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Gender gender;

    @Column(name = "first_name", nullable = false)
    private String firstName;

    @Column(name = "last_name", nullable = false)
    private String lastName;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false, unique = true)
    private String phone;

    @Column(nullable = false)
    private String city;

    @Column(nullable = false)
    private String country;

    @Column(name = "date_of_birth", nullable = false)
    private LocalDateTime dateOfBirth;

    @Column(name = "remaining_vacation_days", nullable = false)
    private Long remainingVacationDays;

    @Column(name = "on_vacation", nullable = false)
    private Boolean onVacation;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Position position;

    /*@Column(nullable = false)
    private String imageURL;*/

    @ManyToOne
    private Employee superior;


    public EmployeeResponse toResponse(){
        EmployeeResponse employeeResponse = EmployeeResponse.builder()
                .id(this.id)
                .gender(this.gender)
                .firstName(this.firstName)
                .lastName(this.lastName)
                .email(this.email)
                .phone(this.phone)
                .dateOfBirth(this.dateOfBirth)
                .city(this.city)
                .country(this.country)
                .remainingVacationDays(this.remainingVacationDays)
                .onVacation(this.onVacation)
                .position(this.position)
                //.imageURL(this.imageURL)
                .build();

        if(this.superior!= null){
            String gender = this.superior.gender==Gender.MEN?"Mr. ":"Mlle ";
            String superiorName = gender + this.superior.lastName + " "+ this.superior.firstName;
            employeeResponse.setSuperiorName(superiorName);
            employeeResponse.setSuperiorId(this.superior.getId());
        }

        return employeeResponse;
    }

    public enum Gender {
        MEN, WOMEN
    }

}