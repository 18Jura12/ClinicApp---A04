package com.ag04smarts.sha.services;

import com.ag04smarts.sha.domain.Patient;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface PatientService {

    List<Patient> getAll();
    void savePatient(Patient patient);
    Patient updatePatient(Patient patient);
    Patient deletePatient(Long id);
    List<Patient> findByAgeAndDate();
    List<Patient> findBySymptoms();
    Patient findById(Long id);
    boolean existsById(Long id);
    List<Patient> uploadCsv(MultipartFile file);
    Patient uploadPicture(Long id, MultipartFile file);

}
