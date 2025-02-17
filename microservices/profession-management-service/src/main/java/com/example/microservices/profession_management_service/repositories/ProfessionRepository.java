package com.example.microservices.profession_management_service.repositories;

import com.example.microservices.profession_management_service.Profession;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface ProfessionRepository extends JpaRepository<Profession, UUID> {

    Optional<Profession> findByName(String name);

    List<Profession> deleteByName(String name);

}
