package com.example.microservices.character_management_service.repositories;

import com.example.microservices.character_management_service.Character;
import com.example.microservices.character_management_service.Profession;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface CharacterRepository extends JpaRepository<Character, UUID> {
    List<Character> findByProfession(Profession profession);

    @EntityGraph(attributePaths = {"profession"})
    List<Character> findAll();
}
