package com.ag04smarts.sha.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Data
@Entity
public class PatientMedicalRecord {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(cascade = CascadeType.ALL)
    @EqualsAndHashCode.Exclude
    @JsonIgnore
    private Patient patient;

    @Lob
    private String diagnosis;

    @Lob
    private String treatment;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "record_symptom",
            joinColumns = @JoinColumn(name = "record_id"),
            inverseJoinColumns = @JoinColumn(name = "symptom_id"))
    private Set<Symptom> symptoms;

    public PatientMedicalRecord() {

    }

    public PatientMedicalRecord(Patient patient, String diagnosis, String treatment) {

        this(patient, diagnosis, treatment, new HashSet<>());

    }

    public PatientMedicalRecord(Patient patient, String diagnosis, String treatment, Set<Symptom> symptoms) {
        this.patient = patient;
        this.diagnosis = diagnosis;
        this.treatment = treatment;
        this.symptoms = symptoms;
    }

}
