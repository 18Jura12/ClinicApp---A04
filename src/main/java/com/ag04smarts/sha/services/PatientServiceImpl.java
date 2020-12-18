package com.ag04smarts.sha.services;

import com.ag04smarts.sha.converters.EnlistmentFormToPatient;
import com.ag04smarts.sha.domain.Patient;
import com.ag04smarts.sha.exceptions.EmptyFileException;
import com.ag04smarts.sha.forms.EnlistmentForm;
import com.ag04smarts.sha.repositories.PatientRepository;
import com.ag04smarts.sha.util.Util;
import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
public class PatientServiceImpl implements PatientService {

    private final PatientRepository patientRepository;

    public PatientServiceImpl(PatientRepository patientRepository) {
        this.patientRepository = patientRepository;
    }

    @Override
    public void savePatient(Patient patient) {

        log.info("PatientService - saving patient");
        patientRepository.save(patient);

    }

    @Override
    public Patient updatePatient(Patient patient) {

        if(patientRepository.existsById(patient.getId())) {

            log.info("PatientService - updating patient");
            return patientRepository.save(patient);

        }

        throw new EntityNotFoundException();

    }

    @Override
    public Patient deletePatient(Long id) {

        if(patientRepository.existsById(id)) {

            Patient patient = patientRepository.findById(id).get();
            patientRepository.deleteById(id);
            return patient;

        }

        throw new EntityNotFoundException();

    }

    @Override
    public List<Patient> getAll() {

        return patientRepository.findAll();

    }

    @Override
    public List<Patient> findByAgeAndDate() {

        Util util = new Util("dd.MM.yyyy.");

        return patientRepository.findAllByAgeGreaterThanAndEnlistmentDateGreaterThan(21, util.makeDate("01.01.2020.")).get();
    }

    @Override
    public List<Patient> findBySymptoms() {

        return patientRepository.patientSymptom("Coughing", "Fever");

    }

    @Override
    public Patient findById(Long id) {

        return patientRepository.findById(id).get();

    }

    @Override
    public boolean existsById(Long id) {

        return patientRepository.existsById(id);

    }

    @Override
    public List<Patient> uploadCsv(MultipartFile file) {

        try {

            Reader reader = new BufferedReader(new InputStreamReader(file.getInputStream()));

            if(file.isEmpty()) {

                throw new EmptyFileException("The CSV file is empty.");

            } else {

                EnlistmentFormToPatient converter = new EnlistmentFormToPatient();

                CsvToBean<EnlistmentForm> csvToBean = new CsvToBeanBuilder<EnlistmentForm>(reader)
                        .withType(EnlistmentForm.class)
                        .withIgnoreLeadingWhiteSpace(true)
                        .build();

                List<EnlistmentForm> forms = csvToBean.parse();

                List<Patient> patients = new ArrayList<>();
                forms.forEach(form -> patients.add(converter.convert(form)));

                return patients;
            }

        } catch(IOException ex) {

            log.error("Handling IOException: " + ex.getLocalizedMessage());
            throw new RuntimeException("Problem with getting input stream from CSV file.");

        }

    }

    @Transactional
    @Override
    public Patient uploadPicture(Long id, MultipartFile file) {

        try {

            if(!patientRepository.existsById(id)) {

                throw new IllegalArgumentException("There is no patient with given id.");

            }

            Patient patient = patientRepository.findById(id).get();

            patient.setPicture(file.getBytes());
            return patient;

        } catch (IOException ex) {

            log.error("Handling IOException: " + ex.getLocalizedMessage());
            throw new RuntimeException("Error while trying to upload picture.");

        }

    }
}
