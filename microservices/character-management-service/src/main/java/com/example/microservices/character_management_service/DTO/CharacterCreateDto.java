package com.example.microservices.character_management_service.DTO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class CharacterCreateDto {
    private String name;
    private int level;

}