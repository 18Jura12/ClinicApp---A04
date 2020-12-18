package com.ag04smarts.sha.services;

import com.ag04smarts.sha.domain.*;
import com.ag04smarts.sha.repositories.*;
import com.ag04smarts.sha.util.Util;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.util.*;

@Primary
@Profile("dev")
@Service("InitializeService")
public class InitializeServiceDev implements  InitializeService {

    private final PatientRepository patientRepository;
    private final AppointmentRepository appointmentRepository;
    private final DoctorRepository doctorRepository;
    private final SymptomRepository symptomRepository;

    public InitializeServiceDev(PatientRepository patientRepository, AppointmentRepository appointmentRepository, DoctorRepository doctorRepository, SymptomRepository symptomRepository) {
        this.patientRepository = patientRepository;
        this.appointmentRepository = appointmentRepository;
        this.doctorRepository = doctorRepository;
        this.symptomRepository = symptomRepository;
    }

    @Override
    public void initialization() {

        Util util = new Util("dd.MM.yyyy. HH:mm");

        Patient p1 = new Patient("John", "Johnson", "a@b.com", 42, "123654789", Gender.MALE, util.makeDate("01.02.2020. 11:30"), Status.ENLISTED, null);
        Patient p2 = new Patient("Jessica", "Bree", "a@b.com", 25, "163654789", Gender.FEMALE, util.makeDate("01.01.2005. 11:30"), Status.CURED, null);
        Patient p3 = new Patient("Ivan", "Lue", "a@b.com", 37, "128654789", Gender.MALE, util.makeDate("02.01.2020. 11:30"), Status.DIAGNOSED, null);
        Patient p4 = new Patient("Jessy", "Pure", "a@b.com", 12, "127654789", Gender.FEMALE, util.makeDate("01.01.2020. 11:30"), Status.UNDER_DIAGNOSIS, null);

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
        symptoms.remove(1);
        p3.setPatientMedicalRecord(new PatientMedicalRecord(p3, "respiratory infection", "hospitalization, intubation", new HashSet<Symptom>(symptoms)));
        symptoms.remove(0);
        p4.setPatientMedicalRecord(new PatientMedicalRecord(p4, "still not diagnosed", "further testing", new HashSet<Symptom>(symptoms)));

        Doctor d1 = new Doctor("George", "Smith", DoctorExpertise.GENERAL_DOCTOR);
        Doctor d2 = new Doctor("Marry", "Jay", DoctorExpertise.PEDIATRICIAN);
        Doctor d3 = new Doctor("Peter", "McDonald", DoctorExpertise.PLASTIC_SURGEON);
        Doctor d4 = new Doctor("Ivonne", "Klee", DoctorExpertise.SPORTS_MEDICINE_SPECIALIST);

        doctorRepository.save(d1);
        doctorRepository.save(d2);
        doctorRepository.save(d3);
        doctorRepository.save(d4);

        List<PatientTreatmentHistory> histories = new ArrayList<>();
        histories.add(new PatientTreatmentHistory(p1, d1, "Patient was accepted due to heavy breathing symptoms and is to be tested by general-doctor.", Status.ENLISTED, Status.UNDER_DIAGNOSIS));
        histories.add(new PatientTreatmentHistory(p1, d3, "After all tests for signs of infection were negative, it was assumed that it was a post-surgical reaction to former breast-implant-surgery. Accepted by plastic surgeon that concluded the implants were pressing patient's chest.", Status.UNDER_DIAGNOSIS, Status.DIAGNOSED));
        histories.add(new PatientTreatmentHistory(p1, d3, "The implants are due to be replaced.", Status.DIAGNOSED, Status.UNDER_TREATMENT));
        p1.setPatientTreatmentHistories(new HashSet<>(histories));
        histories.clear();
        histories.add(new PatientTreatmentHistory(p2, d3, "Patient's rash is gone after two weeks of ointment application.", Status.UNDER_TREATMENT, Status.CURED));
        p2.setPatientTreatmentHistories(new HashSet<>(histories));
        histories.clear();
        histories.add(new PatientTreatmentHistory(p3, d1, "Patient was admitted due to post-surgical examination.", Status.CURED, Status.ENLISTED));
        histories.add(new PatientTreatmentHistory(p3, d4, "The operated leg has no further infections and the patient is to undergo therapy sessions with our sports medicine specialist.", Status.ENLISTED, Status.DIAGNOSED));
        p3.setPatientTreatmentHistories(new HashSet<>(histories));
        histories.clear();
        histories.add(new PatientTreatmentHistory(p4, d2, "After a quick checkup, the patient is diagnosed with post-injury-trauma.", Status.UNDER_DIAGNOSIS, Status.DIAGNOSED));
        p4.setPatientTreatmentHistories(new HashSet<>(histories));

        patientRepository.save(p1);
        patientRepository.save(p2);
        patientRepository.save(p3);
        patientRepository.save(p4);

        appointmentRepository.save(new Appointment(p1, d1, util.makeDate("01.01.2021. 11:30")));
        appointmentRepository.save(new Appointment(p2, d2, util.makeDate("01.01.2010. 11:30")));
        appointmentRepository.save(new Appointment(p3, d3, util.makeDate("01.01.2004. 11:30")));
        appointmentRepository.save(new Appointment(p4, d4, util.makeDate("02.07.2020. 11:30")));
        appointmentRepository.save(new Appointment(p2, d4, util.makeDate("01.01.2025. 11:30")));
        appointmentRepository.save(new Appointment(p4, d1, util.makeDate("01.01.2019. 11:30")));

    }

}
