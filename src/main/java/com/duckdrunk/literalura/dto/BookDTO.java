package com.duckdrunk.literalura.dto;

import java.util.List;

public record BookDTO(
        String title,
        List<PersonDTO> authors,
        List<String> summaries,
        List<String> languages,
        Integer download_count,
        List<PersonDTO> translators,
        List<String> subjects,
        boolean copyright,
        String mediaType
) {}
