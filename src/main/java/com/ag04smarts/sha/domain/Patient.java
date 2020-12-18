package com.ag04smarts.sha.domain;

import lombok.*;

import javax.persistence.*;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;


@Data
@Entity
public class Patient extends Person {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String email;
    private Integer age;
    private String phoneNumber;

    @Enumerated(value = EnumType.STRING)
    private Gender gender;

    private Date enlistmentDate;

    @Enumerated(value = EnumType.STRING)
    private Status status;

    @OneToOne(cascade = CascadeType.ALL)
    private PatientMedicalRecord patientMedicalRecord;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "patient")
    private Set<PatientTreatmentHistory> patientTreatmentHistories;

    @Lob
    private byte[] picture;

    public Patient(){

    }

    public Patient(String firstName, String lastName, String email, Integer age, String phoneNumber, Gender gender, Date enlistmentDate, Status status, PatientMedicalRecord patientMedicalRecord) {

        this(firstName, lastName, email, age, phoneNumber, gender, enlistmentDate, status, patientMedicalRecord, new HashSet<>());

    }

    public Patient(String firstName, String lastName, String email, Integer age, String phoneNumber, Gender gender, Date enlistmentDate, Status status, PatientMedicalRecord patientMedicalRecord, Set<PatientTreatmentHistory> patientTreatmentHistories) {

        super(firstName, lastName);
        this.email = email;
        this.age = age;
        this.phoneNumber = phoneNumber;
        this.gender = gender;
        this.enlistmentDate = enlistmentDate;
        this.status = status;
        this.patientMedicalRecord = patientMedicalRecord;
        this.patientTreatmentHistories = patientTreatmentHistories;

    }

}
