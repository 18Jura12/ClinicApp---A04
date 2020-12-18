package com.ag04smarts.sha.domain;

import lombok.*;

import javax.persistence.*;

@Data
@Entity
public class Doctor extends Person {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(value = EnumType.STRING)
    private DoctorExpertise doctorExpertise;

    public Doctor() {

    }

    public Doctor(String firstName, String lastName, DoctorExpertise doctorExpertise) {

        super(firstName, lastName);
        this.doctorExpertise = doctorExpertise;

    }

}
