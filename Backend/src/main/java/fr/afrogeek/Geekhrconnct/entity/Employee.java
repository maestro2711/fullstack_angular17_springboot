package fr.afrogeek.Geekhrconnct.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
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

    public enum Gender {
        MEN, WOMEN
    }

    public enum Position {
        CEO, DIRECTOR, PROJECT_MANAGER, DEVELOPER, SYSTEM_ANALYST, IT_SUPPORT, NETWORK_ENGINEER
    }

}
