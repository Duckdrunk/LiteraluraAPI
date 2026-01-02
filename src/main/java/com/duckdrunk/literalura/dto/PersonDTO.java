package com.duckdrunk.literalura.dto;

import java.util.List;

public record PersonDTO(
        Long id,
        String name,
        Integer birth_year,
        Integer death_year
) {
}
