package com.duckdrunk.literalura.repository;

import com.duckdrunk.literalura.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface BookRepository extends JpaRepository<Book,Integer> {

    Optional<Book> findByTitleIgnoreCase(String title);

    @Query("""
            SELECT b FROM Book b
            JOIN b.authors a
            WHERE LOWER(a.name) LIKE LOWER(CONCAT('%', :name, '%'))
            """)
    List<Book> findByAuthorName(@Param("name") String name);

    @Query("""
           SELECT b FROM Book b
           JOIN b.languages l
           WHERE LOWER(l.code) = LOWER(:code)
           """)
    List<Book> findByLanguageCode(@Param("code") String code);

    boolean existsById(Long id);
    boolean existsByTitle(String title);
}
