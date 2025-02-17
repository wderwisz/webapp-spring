package com.example.microservices.character_management_service.controllers;

import com.example.microservices.character_management_service.Character;
import com.example.microservices.character_management_service.DTO.CharacterCreateDto;
import com.example.microservices.character_management_service.DTO.CharacterReadDto;
import com.example.microservices.character_management_service.Profession;
import com.example.microservices.character_management_service.services.CharacterService;
//import com.example.microservices.character_management_service.services.ProfessionService;
import com.example.microservices.character_management_service.services.ProfessionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
//@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping("/api/characters")
public class CharacterController {

    @Autowired
    private CharacterService characterService;
    @Autowired
    private ProfessionService professionService;

    // GET /localhost:8080/api/characters
    @GetMapping
    public ResponseEntity<List<CharacterReadDto>> getAllCharacters() {
        List<CharacterReadDto> characters = characterService.getAllCharacterDtos();
        return ResponseEntity.ok(characters);
    }

    // GET /localhost:8080/api/characters/{id}
    @GetMapping("/{id}")
    public ResponseEntity<CharacterReadDto> getCharacterById(@PathVariable UUID id) {
        Optional<CharacterReadDto> character = characterService.getCharacterDtoById(id);
        return character.map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping("/{professionName}")
    public ResponseEntity<CharacterReadDto> addCharacterToProfession(@PathVariable String professionName,
                                                                     @RequestBody CharacterCreateDto characterDto) {
        Optional<Profession> profession = professionService.getProfessionByName(professionName);
        if (profession.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }

        Character newCharacter = new Character.CharacterBuilder(characterDto.getName(), characterDto.getLevel()).build();
        newCharacter.setProfession(profession.get());

        characterService.saveCharacter(newCharacter, profession.get());

        CharacterReadDto responseDto = characterService.mapToReadDto(newCharacter);
        return ResponseEntity.status(HttpStatus.CREATED).body(responseDto);
    }


    // PUT /localhost:8080/api/characters/{id}
    @PutMapping("/{id}")
    public ResponseEntity<CharacterReadDto> updateCharacter(@PathVariable UUID id,
                                                            @RequestBody CharacterCreateDto characterDto) {
        Optional<Character> characterOpt = characterService.getCharacterById(id);
        if (characterOpt.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        Character character = characterOpt.get();
        character.setName(characterDto.getName());
        character.setLevel(characterDto.getLevel());
        characterService.deleteCharacterById(id);
        characterService.saveCharacter(character, character.getProfession());

        CharacterReadDto responseDto = characterService.mapToReadDto(character);
        return ResponseEntity.ok(responseDto);
    }

    // DELETE /localhost:8080/api/characters/{id}
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteCharacter(@PathVariable UUID id) {
        if (characterService.getCharacterDtoById(id).isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        characterService.deleteCharacterById(id);
        String response = "Character deleted successfully!";

        return ResponseEntity.ok(response);
    }
}