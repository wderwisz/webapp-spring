package com.example.microservices.profession_management_service.DTO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class ProfessionCreateDto {
    private String name;
    private int baseArmor;
    private int baseStrength;
}
