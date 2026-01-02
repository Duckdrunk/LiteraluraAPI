package com.duckdrunk.literalura.service;

import com.duckdrunk.literalura.model.Language;
import com.duckdrunk.literalura.repository.LanguageRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class LanguageService {

    private final LanguageRepository languageRepo;

    public Language getOrCreateLanguage(String code) {
        return languageRepo.findById(code)
                .orElseGet(() -> languageRepo.save(new Language(code)));
    }
}
