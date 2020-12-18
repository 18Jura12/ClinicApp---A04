package com.ag04smarts.sha.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.persistence.*;
import java.util.Date;

@Data
@EqualsAndHashCode(exclude = {"patient", "doctor"})
@ToString(exclude = {"patient", "doctor"})
@Entity
public class Appointment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Patient patient;

    @ManyToOne
    private Doctor doctor;

    private Date appointmentDate;

    public Appointment() {

    }

    public Appointment(Patient patient, Doctor doctor, Date appointmentDate) {

        this.patient = patient;
        this.doctor = doctor;
        this.appointmentDate = appointmentDate;

    }
}
