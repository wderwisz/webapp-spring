package com.example.microservices.character_management_service.controllers;

import com.example.microservices.character_management_service.DTO.CharacterReadDto;
import com.example.microservices.character_management_service.DTO.ProfessionCreateDto;
import com.example.microservices.character_management_service.DTO.ProfessionReadDto;
import com.example.microservices.character_management_service.Profession;
import com.example.microservices.character_management_service.services.CharacterService;
import com.example.microservices.character_management_service.services.ProfessionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
//@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping("/api/professions")
public class ProfessionController {

    @Autowired
    private ProfessionService professionService;

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private CharacterService characterService;

    // GET /localhost:8080/api/professions
    @GetMapping
    public ResponseEntity<List<ProfessionReadDto>> getAllProfessions() {
        List<ProfessionReadDto> professions = professionService.getAllProfessionDtos();
        return ResponseEntity.ok(professions);
    }

    // GET /localhost:8080/api/professions/{id}
//    @GetMapping("/{id}")
//    public ResponseEntity<ProfessionReadDto> getProfessionById(@PathVariable UUID id) {
//        Optional<ProfessionReadDto> profession = professionService.getProfessionDtoById(id);
//        return profession.map(ResponseEntity::ok)
//                .orElse(ResponseEntity.notFound().build());
//    }

//    @GetMapping("/{name}")
//    public ResponseEntity<ProfessionReadDto> getProfessionByName(@PathVariable String name) {
//        Optional<ProfessionReadDto> profession = professionService.getProfessionDtoByName(name);
//        return profession.map(ResponseEntity::ok)
//                .orElse(ResponseEntity.notFound().build());
//    }

    // POST /localhost:8080/api/professions
    @PostMapping
    public ResponseEntity<ProfessionReadDto> createProfession(@RequestBody ProfessionCreateDto professionDto) {
        Profession newProfession = new Profession.ProfessionBuilder(professionDto.getName(), professionDto.getBaseArmor(), professionDto.getBaseStrength()).build();
        professionService.saveProfession(newProfession);

        //restTemplate.postForEntity("http://localhost:8082/api/professions", newProfession, Void.class);

        ProfessionReadDto responseDto = professionService.mapToReadDto(newProfession);
        return ResponseEntity.status(HttpStatus.CREATED).body(responseDto);
    }

    // PUT /localhost:8080/api/professions/{id}
    @PutMapping("/{name}")
    public ResponseEntity<ProfessionReadDto> updateProfession(@PathVariable String name,
                                                              @RequestBody ProfessionCreateDto professionDto) {
        Optional<Profession> professionOpt = professionService.getProfessionByName(name);
        if (professionOpt.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        Profession profession = professionOpt.get();
        profession.setName(professionDto.getName());
        profession.setBaseArmor(professionDto.getBaseArmor());
        profession.setBaseStrength(professionDto.getBaseStrength());
        professionService.saveProfession(profession);

        ProfessionReadDto responseDto = professionService.mapToReadDto(profession);
        return ResponseEntity.ok(responseDto);
    }

    // DELETE /localhost:8080/api/professions/{id}
    @DeleteMapping("/{name}")
    public ResponseEntity<String> deleteProfession(@PathVariable String name) {
        Optional<Profession> professionOpt = professionService.getProfessionByName(name);
        if (professionOpt.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        // Usunięcie profesji wraz z powiązanymi postaciami
        professionService.deleteProfessionByName(name);

        String response = "Profession deleted with all of its characters";
        return ResponseEntity.ok(response);
    }

    // GET /localhost:8080/api/professions/{id}/characters
    @GetMapping("/{name}/characters")
    public ResponseEntity<List<CharacterReadDto>> getCharactersByProfession(@PathVariable String name) {
        Optional<Profession> professionOpt = professionService.getProfessionByName(name);
        if (professionOpt.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        List<CharacterReadDto> characters = characterService.getCharactersByProfession(professionOpt.get());
        return ResponseEntity.ok(characters);
    }
}
