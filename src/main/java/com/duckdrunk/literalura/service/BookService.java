package com.duckdrunk.literalura.service;

import com.duckdrunk.literalura.component.GutendexClient;
import com.duckdrunk.literalura.dto.BookDTO;
import com.duckdrunk.literalura.dto.PersonDTO;
import com.duckdrunk.literalura.mapper.BookMapper;
import com.duckdrunk.literalura.mapper.PersonMapper;
import com.duckdrunk.literalura.model.Book;
import com.duckdrunk.literalura.model.Language;
import com.duckdrunk.literalura.model.Person;
import com.duckdrunk.literalura.model.Subject;
import com.duckdrunk.literalura.repository.BookRepository;
import com.duckdrunk.literalura.repository.LanguageRepository;
import com.duckdrunk.literalura.repository.PersonRepository;
import com.duckdrunk.literalura.repository.SubjectRepository;
import org.springframework.transaction.annotation.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@AllArgsConstructor
@Service
public class BookService {
    private final GutendexClient client;
    private final BookRepository bookRepo;
    private final PersonService personService;
    private final LanguageService languageService;
    private final SubjectService subjectService;

    @Transactional
    public void importBooks(int page){
        List<BookDTO> books = client.getBooks(page).results();

        for (BookDTO dto : books) {
            if (bookRepo.existsByTitle(dto.title())) {
                continue;
            }

            Book book = BookMapper.toEntity(dto);

            Set<Language> languages = new HashSet<>();
            for (String code : dto.languages()) {
                languages.add(languageService.getOrCreateLanguage(code));
            }

            Set<Subject> subjects = new HashSet<>();
            for (String code : dto.subjects()) {
                subjects.add(subjectService.getOrCreateSubject(code));
            }

            Set<Person> authors = new HashSet<>();
            for (PersonDTO author : dto.authors()){
                authors.add(personService.getOrCreatePerson(author));
            }

            Set<Person> translators = new HashSet<>();
            for (PersonDTO translator : dto.translators()){
                translators.add(personService.getOrCreatePerson(translator));
            }

            book.setLanguages(languages);
            book.setSubjects(subjects);
            book.setAuthors(authors);
            book.setTranslators(translators);

            bookRepo.save(book);
        }
    }

    @Transactional(readOnly = true)
    public List<BookDTO> findAll(){
        return bookRepo.findAll()
                .stream()
                .map(BookMapper::toDto)
                .toList();
    }

    @Transactional(readOnly = true)
    public List<BookDTO> findByLanguage(String code){
        return bookRepo.findByLanguageCode(code)
                .stream()
                .map(BookMapper::toDto)
                .toList();
    }

    @Transactional(readOnly = true)
    public Optional<BookDTO> findByTitle(String title){
        return bookRepo.findByTitleIgnoreCase(title)
                .map(BookMapper::toDto);
    }

}
