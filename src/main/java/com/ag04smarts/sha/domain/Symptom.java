package com.ag04smarts.sha.domain;

import lombok.*;

import javax.persistence.*;

@Data
@Entity
public class Symptom {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Lob
    private String description;

    public Symptom() {

    }

    public Symptom(String description) {
        this.description = description;
    }

}
