package com.duckdrunk.literalura.mapper;

import com.duckdrunk.literalura.dto.BookDTO;
import com.duckdrunk.literalura.model.Book;
import com.duckdrunk.literalura.model.Language;
import com.duckdrunk.literalura.model.Subject;

import java.util.stream.Collectors;

public class BookMapper {
    public static BookDTO toDto(Book entity){
        return new BookDTO(
                entity.getTitle(),
                entity.getAuthors()
                        .stream()
                        .map(PersonMapper::toDto)
                        .toList(),
                entity.getSummaries(),
                entity.getLanguages()
                        .stream()
                        .map(Language::getCode)
                        .toList(),
                entity.getDownloadCount(),
                entity.getTranslators()
                        .stream()
                        .map(PersonMapper::toDto)
                        .toList(),
                entity.getSubjects()
                        .stream()
                        .map(Subject::getName)
                        .toList(),
                entity.isCopyright(),
                entity.getMediaType()
        );
    }

    public static Book toEntity(BookDTO dto) {
        Book entity = new Book();
        entity.setTitle(dto.title());
        entity.setCopyright(dto.copyright());
        entity.setMediaType(dto.mediaType());
        entity.setDownloadCount(dto.download_count());
        entity.setSummaries(dto.summaries());
        return entity;
    }
}
