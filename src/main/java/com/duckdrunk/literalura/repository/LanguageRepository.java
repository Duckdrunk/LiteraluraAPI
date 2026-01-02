package com.duckdrunk.literalura.repository;

import com.duckdrunk.literalura.model.Language;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface LanguageRepository extends JpaRepository<Language,String> {
    //Optional<Language> findByCodeIgnoreCase(String code);
    //Optional<Language> findByIdIgnoreCase(String code);
}
