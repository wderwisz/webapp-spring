package com.example.microservices.character_management_service.DTO;

import java.util.UUID;

public class CharacterDto implements Comparable<CharacterDto>{

    UUID id;
    String name;
    int level;
    String profession;

    public CharacterDto(String name, int level, String profession, UUID id) {
        this.name = name;
        this.level = level;
        this.profession = profession;
        this.id = id;
    }

    @Override
    public String toString() {
        return "CharacterDTO[" + name + " lvl: " + level + ", " + profession + ", ID: " + id + "]";
    }

    @Override
    public int compareTo(CharacterDto o) {
        return o.level - this.level;
    }
}
