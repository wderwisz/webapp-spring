package com.example.microservices.character_management_service.services;

import com.example.microservices.character_management_service.Character;
import com.example.microservices.character_management_service.DTO.CharacterCollectionDto;
import com.example.microservices.character_management_service.DTO.CharacterCreateDto;
import com.example.microservices.character_management_service.DTO.CharacterReadDto;
import com.example.microservices.character_management_service.Profession;
import com.example.microservices.character_management_service.repositories.CharacterRepository;
//import com.example.microservices.character_management_service.repositories.ProfessionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class CharacterService {

    @Autowired
    private CharacterRepository characterRepository;

    //@Autowired
    //private ProfessionRepository professionRepository;

    // Znajdź postacie według profesji i zamapuj na DTO
    public List<CharacterReadDto> getCharactersByProfession(Profession profession) {
        return characterRepository.findByProfession(profession)
                .stream()
                .map(this::mapToReadDto)
                .collect(Collectors.toList());
    }

    // Znajdź wszystkie postacie i zamapuj na DTO
    public List<CharacterReadDto> getAllCharacterDtos() {
        return characterRepository.findAll().stream()
                .map(this::mapToReadDto)
                .collect(Collectors.toList());
    }

    // Znajdź wszystkie postacie w formie kolekcji DTO
    public List<CharacterCollectionDto> getAllCharacterCollectionDtos() {
        return characterRepository.findAll().stream()
                .map(this::mapToCollectionDto)
                .collect(Collectors.toList());
    }

    // Znajdź profesję po ID
//    public Optional<Profession> getProfessionById(UUID id) {
//        return professionRepository.findById(id);
//    }

    // Zapisz lub zaktualizuj postać
    public Character saveCharacter(Character character, Profession profession) {
        Character newCharacter = new Character();
        newCharacter.setName(character.getName());
        newCharacter.setLevel(character.getLevel());
        newCharacter.setProfession(profession);
        return characterRepository.save(newCharacter);
    }

    public Character saveCharacterFromDto(CharacterCreateDto characterDto, Profession profession) {
        Character character = new Character();
        character.setName(characterDto.getName());
        character.setLevel(characterDto.getLevel());
        character.setProfession(profession);
        return characterRepository.save(character);
    }

    // Usuń postać po ID
    public void deleteCharacterById(UUID id) {
        characterRepository.deleteById(id);
    }

    // Znajdź postać po ID i zamapuj na DTO
    public Optional<CharacterReadDto> getCharacterDtoById(UUID id) {
        return characterRepository.findById(id)
                .map(this::mapToReadDto);
    }

    public Optional<Character> getCharacterById(UUID id) {
        return characterRepository.findById(id);
    }

    // Mapowanie Character na CharacterReadDto
    public CharacterReadDto mapToReadDto(Character character) {
        return new CharacterReadDto(
                character.getId(),
                character.getName(),
                character.getLevel(),
                character.getProfession() != null ? character.getProfession().getName() : null
        );
    }

    // Mapowanie Character na CharacterCollectionDto
    public CharacterCollectionDto mapToCollectionDto(Character character) {
        return new CharacterCollectionDto(
                character.getId(),
                character.getName()
        );
    }

}
