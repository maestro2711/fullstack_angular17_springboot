package fr.afrogeek.Geekhrconnct.dto;


import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.util.UUID;

@Data
@Builder
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class SuperiorResponse {
    @NotNull
    private UUID id;
    private String name;


}
