package com.ag04smarts.sha.services;

import com.ag04smarts.sha.domain.*;
import com.ag04smarts.sha.repositories.AppointmentRepository;
import com.ag04smarts.sha.repositories.DoctorRepository;
import com.ag04smarts.sha.repositories.PatientRepository;
import com.ag04smarts.sha.repositories.SymptomRepository;
import com.ag04smarts.sha.util.Util;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

@Profile("test")
@Service("InitializeService")
public class InitializeServiceTest implements  InitializeService {

    private final PatientRepository patientRepository;
    private final AppointmentRepository appointmentRepository;
    private final DoctorRepository doctorRepository;
    private final SymptomRepository symptomRepository;

    public InitializeServiceTest(PatientRepository patientRepository, AppointmentRepository appointmentRepository, DoctorRepository doctorRepository, SymptomRepository symptomRepository) {
        this.patientRepository = patientRepository;
        this.appointmentRepository = appointmentRepository;
        this.doctorRepository = doctorRepository;
        this.symptomRepository = symptomRepository;
    }

    @Override
    public void initialization() {

        Util util = new Util("dd.MM.yyyy. HH:mm");

        Patient p1 = new Patient("John", "Johnson", "a@b.com", 42, "123654789", Gender.MALE, util.makeDate("01.01.2001. 11:30"), Status.ENLISTED, null);
        Patient p2 = new Patient("Jessica", "Bree", "a@b.com", 25, "163654789", Gender.FEMALE, util.makeDate("01.01.1976. 11:30"), Status.CURED, null);

        List<Symptom> symptoms = new ArrayList<>();
        symptoms.add(new Symptom("Coughing"));
        symptoms.add(new Symptom("Fever"));
        symptoms.add(new Symptom("Temperature"));
        symptoms.add(new Symptom("Diarrhea"));
        symptoms.add(new Symptom("Rash"));
        symptoms.add(new Symptom("Heavy breathing"));

        symptomRepository.saveAll(symptoms);

        p1.setPatientMedicalRecord(new PatientMedicalRecord(p1, "infection", "hospitalized till the cause is found", new HashSet<Symptom>(symptoms)));
        symptoms.remove(2);
        p2.setPatientMedicalRecord(new PatientMedicalRecord(p2, "possible child worm", "proceed to further testing", new HashSet<Symptom>(symptoms)));

        Doctor d1 = new Doctor("George", "Smith", DoctorExpertise.GENERAL_DOCTOR);
        Doctor d3 = new Doctor("Peter", "McDonald", DoctorExpertise.PLASTIC_SURGEON);

        doctorRepository.save(d1);
        doctorRepository.save(d3);

        List<PatientTreatmentHistory> histories = new ArrayList<>();
        histories.add(new PatientTreatmentHistory(p1, d1, "Patient was accepted due to heavy breathing symptoms and is to be tested by general-doctor.", Status.ENLISTED, Status.UNDER_DIAGNOSIS));
        histories.add(new PatientTreatmentHistory(p1, d3, "After all tests for signs of infection were negative, it was assumed that it was a post-surgical reaction to former breast-implant-surgery. Accepted by plastic surgeon that concluded the implants were pressing patient's chest.", Status.UNDER_DIAGNOSIS, Status.DIAGNOSED));
        histories.add(new PatientTreatmentHistory(p1, d3, "The implants are due to be replaced.", Status.DIAGNOSED, Status.UNDER_TREATMENT));
        p1.setPatientTreatmentHistories(new HashSet<>(histories));
        histories.clear();
        histories.add(new PatientTreatmentHistory(p2, d3, "Patient's rash is gone after two weeks of ointment application.", Status.UNDER_TREATMENT, Status.CURED));
        p2.setPatientTreatmentHistories(new HashSet<>(histories));

        patientRepository.save(p1);
        patientRepository.save(p2);

        appointmentRepository.save(new Appointment(p1, d1, util.makeDate("01.01.2021. 11:30")));

    }
}
