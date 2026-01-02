package com.duckdrunk.literalura.repository;

import com.duckdrunk.literalura.model.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface PersonRepository extends JpaRepository<Person,Long> {
    Optional<Person> findByNameIgnoreCase(String name);
    List<Person> findByDeathYearIsNull();
    List<Person> findByBirthYearBetween(Integer start, Integer end);

    @Query("""
        SELECT DISTINCT p
        FROM Book b
        JOIN b.authors p
        WHERE p.birthYear <= :year
          AND (p.deathYear IS NULL OR p.deathYear >= :year)
    """)
    List<Person> findAuthorsAliveInYear(@Param("year") int year);

    @Query("""
        SELECT DISTINCT p
        FROM Book b
        JOIN b.authors p
    """)
    List<Person> findAllAuthors();

    @Query("""
    SELECT DISTINCT p
    FROM Person p
    JOIN FETCH p.books b
    WHERE LOWER(p.name) LIKE LOWER(CONCAT('%', :name, '%'))
    """)
    Optional<Person> findAuthorWithBooks(@Param("name") String name);
}
