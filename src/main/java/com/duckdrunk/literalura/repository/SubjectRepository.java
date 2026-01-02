package com.duckdrunk.literalura.repository;

import com.duckdrunk.literalura.model.Subject;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface SubjectRepository extends JpaRepository<Subject,Long> {
    Optional<Subject> findByNameIgnoreCase(String name);
}
