package com.duckdrunk.literalura.service;

import com.duckdrunk.literalura.model.Subject;
import com.duckdrunk.literalura.repository.SubjectRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class SubjectService {
    private SubjectRepository subjectRepo;

    public Subject getOrCreateSubject(String name){
        return subjectRepo.findByNameIgnoreCase(name)
                .orElseGet(() -> {
                    Subject s = new Subject();
                    s.setName(name);
                    return subjectRepo.save(s);
                });
    }
}
