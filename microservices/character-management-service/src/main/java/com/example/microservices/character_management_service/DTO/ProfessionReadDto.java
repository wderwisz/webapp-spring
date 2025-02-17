package com.example.microservices.character_management_service.DTO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.util.List;
import java.util.UUID;

@Data
@Builder
@AllArgsConstructor
public class ProfessionReadDto {
    private UUID id;
    private String name;
    private int baseArmor;
    private int baseStrength;
    //private List<String> characterNames;

}