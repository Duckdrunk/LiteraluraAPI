package com.duckdrunk.literalura.dto;

import java.util.List;

public record AuthorWithBooksDTO(
        String author,
        Integer birthYear,
        Integer deathYear,
        List<String> books
) {}
