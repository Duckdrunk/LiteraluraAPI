package com.duckdrunk.literalura.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "languages")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Language {
    @Id
    @Column(length = 5)
    private String code;
}
