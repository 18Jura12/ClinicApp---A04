package com.ag04smarts.sha.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import lombok.extern.slf4j.Slf4j;

import javax.persistence.*;

@Slf4j
@Data
@Entity
public class PatientTreatmentHistory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @EqualsAndHashCode.Exclude
    @JsonIgnore
    private Patient patient;

    @ManyToOne
    private Doctor doctor;

    @Lob
    private String treatmentRemark;

    @Enumerated(value = EnumType.STRING)
    private Status oldStatus;

    @Enumerated(value = EnumType.STRING)
    private Status newStatus;

    private String dbStatus;

    public PatientTreatmentHistory() {

    }

    public PatientTreatmentHistory(Patient patient, Doctor doctor, String treatmentRemark, Status oldStatus, Status newStatus) {
        this.patient = patient;
        this.doctor = doctor;
        this.treatmentRemark = treatmentRemark;
        this.oldStatus = oldStatus;
        this.newStatus = newStatus;
    }

    @PrePersist
    public void insertTreatmentHistory() {

        setDbStatus("New treatment history inserted for patient " + patient.getFirstName() + " " + patient.getLastName() + ".");
        log.debug(dbStatus);

    }

    @PreUpdate
    public void updateTreatmentHistory() {

        setDbStatus("Updated treatment history for " + patient.getFirstName() + " " + patient.getLastName() + ".");
        log.debug(dbStatus);

    }

}
