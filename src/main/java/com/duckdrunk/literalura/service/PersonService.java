package com.duckdrunk.literalura.service;

import com.duckdrunk.literalura.dto.AuthorWithBooksDTO;
import com.duckdrunk.literalura.dto.PersonDTO;
import com.duckdrunk.literalura.mapper.AuthorWithBooksMapper;
import com.duckdrunk.literalura.mapper.PersonMapper;
import com.duckdrunk.literalura.model.Person;
import com.duckdrunk.literalura.repository.PersonRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@AllArgsConstructor
@Service
public class PersonService {
    private final PersonRepository personRepo;

    @Transactional
    public List<AuthorWithBooksDTO> findAllAuthors(){
        return personRepo.findAllAuthors()
                .stream()
                .map(AuthorWithBooksMapper::toDto)
                .toList();
    }

    @Transactional(readOnly = true)
    public List<AuthorWithBooksDTO> findAuthorsAliveInYear(int year){
        return personRepo.findAuthorsAliveInYear(year)
                .stream()
                .map(AuthorWithBooksMapper::toDto)
                .toList();
    }

    public Person getOrCreatePerson(PersonDTO dto) {
        return personRepo.findByNameIgnoreCase(dto.name())
                .orElseGet(() -> personRepo.save(PersonMapper.toEntity(dto)));
    }

    @Transactional
    public AuthorWithBooksDTO findAuthorWithBooks(String name) {
        Person author = personRepo.findAuthorWithBooks(name)
                .orElseThrow(() -> new RuntimeException("Autor no encontrado"));

        return AuthorWithBooksMapper.toDto(author);
    }

}
