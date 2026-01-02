package com.duckdrunk.literalura.mapper;

import com.duckdrunk.literalura.dto.PersonDTO;
import com.duckdrunk.literalura.model.Person;

public class PersonMapper {
    public static PersonDTO toDto(Person entity){
        return new PersonDTO(
                entity.getId(),
                entity.getName(),
                entity.getBirthYear(),
                entity.getDeathYear());
    }

    public static Person toEntity(PersonDTO dto){
        Person entity = new Person();
        entity.setId(entity.getId());
        entity.setName(dto.name());
        entity.setBirthYear(dto.birth_year());
        entity.setDeathYear(dto.death_year());
        return entity;
    }
}
