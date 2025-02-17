package com.example.microservices.character_management_service.DTO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.util.UUID;

@Data
@Builder
@AllArgsConstructor
public class CharacterCollectionDto {
    private UUID id;
    private String name;
}
