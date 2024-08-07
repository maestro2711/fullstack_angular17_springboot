package fr.afrogeek.Geekhrconnct.dto;

import fr.afrogeek.Geekhrconnct.entity.Employee;
import fr.afrogeek.Geekhrconnct.enums.Position;
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
    public class EmployeeResponse {

        private UUID id;
        private Employee.Gender gender;
        private String firstName;
        private String lastName;
        private String email;
        private String phone;
        private LocalDateTime dateOfBirth;
        private String city;
        private String country;
        private Long remainingVacationDays;
        private boolean onVacation;
        private Position position;
        private String imageURL;
        private String superiorName;
        private UUID superiorId;



    }

