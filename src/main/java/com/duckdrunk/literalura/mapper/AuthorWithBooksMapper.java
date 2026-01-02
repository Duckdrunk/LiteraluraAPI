package com.duckdrunk.literalura.mapper;

import com.duckdrunk.literalura.dto.AuthorWithBooksDTO;
import com.duckdrunk.literalura.model.Book;
import com.duckdrunk.literalura.model.Person;


public class AuthorWithBooksMapper {

    public static AuthorWithBooksDTO toDto(Person author) {
        return new AuthorWithBooksDTO(
                author.getName(),
                author.getBirthYear(),
                author.getDeathYear(),
                author.getBooks()
                        .stream()
                        .map(Book::getTitle)
                        .toList()
        );
    }

}
