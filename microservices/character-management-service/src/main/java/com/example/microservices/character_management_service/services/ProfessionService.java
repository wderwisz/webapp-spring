package com.example.microservices.character_management_service.services;

import com.example.microservices.character_management_service.DTO.ProfessionCollectionDto;
import com.example.microservices.character_management_service.DTO.ProfessionCreateDto;
import com.example.microservices.character_management_service.DTO.ProfessionReadDto;
import com.example.microservices.character_management_service.Profession;
import com.example.microservices.character_management_service.repositories.ProfessionRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class ProfessionService {

    @Autowired
    private ProfessionRepository professionRepository;

    // Znajdź wszystkie profesje i zamapuj na DTO
    public List<ProfessionReadDto> getAllProfessionDtos() {
        return professionRepository.findAll().stream()
                .map(this::mapToReadDto)
                .collect(Collectors.toList());
    }

    // Znajdź wszystkie profesje w formie kolekcji DTO
    public List<ProfessionCollectionDto> getAllProfessionCollectionDtos() {
        return professionRepository.findAll().stream()
                .map(this::mapToCollectionDto)
                .collect(Collectors.toList());
    }

    // Znajdź profesję po ID i zamapuj na DTO
    public Optional<ProfessionReadDto> getProfessionDtoById(UUID id) {
        return professionRepository.findById(id)
                .map(this::mapToReadDto);
    }

    public Optional<Profession> getProfessionById(UUID id){
        return professionRepository.findById(id);
    }

    public Optional<Profession> getProfessionByName(String name){
        return professionRepository.findByName(name);
    }

    public Optional<ProfessionReadDto> getProfessionDtoByName(String name) {
        return professionRepository.findByName(name)
                .map(this::mapToReadDto);
    }

    // Zapisz lub zaktualizuj profesję
    public Profession saveProfessionFromDto(ProfessionCreateDto professionDto) {
        Profession profession = new Profession();
        profession.setName(professionDto.getName());
        profession.setBaseArmor(professionDto.getBaseArmor());
        profession.setBaseStrength(professionDto.getBaseStrength());
        return professionRepository.save(profession);
    }

    @Transactional
    public Profession saveProfession(Profession profession) {
//        Profession newProfession = new Profession();
//        newProfession.setName(profession.getName());
//        newProfession.setBaseArmor(profession.getBaseArmor());
//        newProfession.setBaseStrength(profession.getBaseStrength());
//        return professionRepository.save(newProfession);

        return professionRepository.save(profession);
    }

    // Usuń profesję po ID
    public void deleteProfessionById(UUID id) {
        professionRepository.deleteById(id);
    }

    @Transactional
    public void deleteProfessionByName(String name) { professionRepository.deleteByName(name); }

    // Mapowanie Profession na ProfessionReadDto
    public ProfessionReadDto mapToReadDto(Profession profession) {
//        List<String> characterNames = profession.getCharacters().stream()
//                .map(Character::getName)
//                .collect(Collectors.toList());

        return new ProfessionReadDto(
                profession.getId(),
                profession.getName(),
                profession.getBaseArmor(),
                profession.getBaseStrength()
                //characterNames
        );
    }

    // Mapowanie Profession na ProfessionCollectionDto
    public ProfessionCollectionDto mapToCollectionDto(Profession profession) {
        return new ProfessionCollectionDto(
                profession.getId(),
                profession.getName()
        );
    }
}
