package com.duckdrunk.literalura.controller;

import com.duckdrunk.literalura.service.BookService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/import")
public class ImportController {

    private final BookService bookService;

    public ImportController(BookService bookService){
        this.bookService = bookService;
    }


    @GetMapping("/gutendex")
    public ResponseEntity<String> importBooks(
            @RequestParam(defaultValue = "1") int page) {

        bookService.importBooks(page);
        return ResponseEntity.ok("Libros importados correctamente");
    }
}
