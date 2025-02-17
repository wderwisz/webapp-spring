package com.example.microservices.profession_management_service;

import jakarta.persistence.*;
import jakarta.transaction.Transactional;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

@Entity
@Transactional
@Table(name = "professions")
public class Profession implements Comparable<Profession>, Serializable {
    @Id
    @Column(name = "id", nullable = false, updatable = false)
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;
    @Column(name = "name", nullable = false)
    private String name;
    @Column(name = "base_armor", nullable = false)
    private int baseArmor;
    @Column(name = "base_strength", nullable = false)
    private int baseStrength;
//    @OneToMany(mappedBy = "profession", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
//    private List<Character> characters;

    public UUID getId() {
        return id;
    }

    public void setBaseArmor(int baseArmor) {
        this.baseArmor = baseArmor;
    }

    public void setBaseStrength(int baseStrength) {
        this.baseStrength = baseStrength;
    }

    public static class ProfessionBuilder{
        String name;
        int baseArmor;
        int baseStrength;
        List<Character> characters;

        public ProfessionBuilder(String p_name, int p_baseArmor, int p_baseStrength){
            this.name = p_name;
            this.baseArmor = p_baseArmor;
            this.baseStrength = p_baseStrength;
            this.characters = new ArrayList<>();
        }

        public Profession build(){
            return new Profession(this);
        }
    }

    private Profession(ProfessionBuilder builder){
        //this.id = UUID.randomUUID();
        name = builder.name;
        baseArmor = builder.baseArmor;
        baseStrength = builder.baseStrength;
        //characters = builder.characters;
    }

    public Profession(){};

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public int getBaseArmor() {
        return baseArmor;
    }
    public int getBaseStrength() {
        return baseStrength;
    }
//    public List<Character> getCharacters() {
//        return characters;
//    }

//    public void addCharacter(Character c){
//        if(c.getProfession() == this){
//            return;
//        }
//        if(c.getProfession() != null){
//            c.getProfession().removeCharacter(c);
//        }
//        c.setProfession(this);
//        characters.add(c);
//    }
//
//    public void removeCharacter(Character c){
//        c.setProfession(null);
//        characters.remove(c);
//    }

//    public void displayCharacters(){
//        //Collections.sort(characters);
//        System.out.println(characters);
//    }

    @Override
    public String toString(){
        return "Prof["+ name + ", A:" + baseArmor + ", S:" + baseStrength + ", ID: " + id +"]";
    }

    @Override
    public int compareTo(Profession o) {
        int strengthComparison = Integer.compare(this.baseStrength, o.baseStrength);
        return strengthComparison != 0 ? strengthComparison : Integer.compare(this.baseArmor, o.baseArmor);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof Profession other)) return false;
        return baseArmor == other.baseArmor &&
                baseStrength == other.baseStrength &&
                Objects.equals(name, other.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, baseArmor, baseStrength);
    }
}
