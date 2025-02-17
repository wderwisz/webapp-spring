package com.example.microservices.character_management_service;

import jakarta.persistence.*;

import java.io.Serializable;
import java.util.Objects;
import java.util.UUID;

@Entity
@Table(name = "characters")
public class Character implements Comparable<Character>, Serializable {
    @Id
    @Column(name = "id", nullable = false, updatable = false)
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;
    @Column(name = "name", nullable = false)
    private String name;
    @Column(name = "level", nullable = false)
    private int level;
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.MERGE)
    @JoinColumn(name = "profession_id")
    private Profession profession;

    public static class CharacterBuilder{
        String name;
        int level;
        Profession profession;

        public CharacterBuilder(String p_name, int p_level){
            name = p_name;
            level = p_level;
        }

        public Character build(){
            return new Character(this);
        }
    }

    private Character(CharacterBuilder builder){
        //this.id = UUID.randomUUID();
        name = builder.name;
        level = builder.level;
    }

    public Character(){};

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public int getLevel() {
        return level;
    }
    public void setLevel(int level) {
        this.level = level;
    }

    public Profession getProfession() {
        return profession;
    }

    public UUID getId(){
        return id;
    }

    public void setProfession(Profession profession) {
        this.profession = profession;
    }

    @Override
    public String toString(){
        return "Character["+ name + ", lvl:" + level + ", " + profession.getName() + ", ID: " + id +"]";
    }

    @Override
    public int compareTo(Character o) {
        return o.level - this.level;
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, level, profession);
    }
}
