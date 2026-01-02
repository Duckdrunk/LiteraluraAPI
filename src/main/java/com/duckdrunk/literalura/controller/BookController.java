package com.duckdrunk.literalura.controller;

import com.duckdrunk.literalura.dto.BookDTO;
import com.duckdrunk.literalura.mapper.BookMapper;
import com.duckdrunk.literalura.repository.BookRepository;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@AllArgsConstructor
public class BookController {

    private BookRepository bookRepository;

    @GetMapping("/books")
    public List<BookDTO> getBooks(){
        return bookRepository.findAll()
                            .stream()
                            .map(BookMapper::toDto)
                            .toList();
    }


}
