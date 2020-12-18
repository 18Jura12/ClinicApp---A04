package com.ag04smarts.sha.controllers;

import com.ag04smarts.sha.domain.Patient;
import com.ag04smarts.sha.services.PatientService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api/patient")
public class PatientController {

    private final PatientService patientService;

    public PatientController(PatientService patientService) {
        this.patientService = patientService;
    }

    @GetMapping
    public ResponseEntity<List<Patient>> getPatients() {

        List<Patient> patients = patientService.getAll();

        return new ResponseEntity<>(patients, HttpStatus.OK);

    }

    @GetMapping
    @RequestMapping("/age-date")
    public ResponseEntity<List<Patient>> getPatientsByAgeAndDate() {

        List<Patient> patients = patientService.findByAgeAndDate();

        return new ResponseEntity<>(patients, HttpStatus.OK);

    }

    @GetMapping
    @RequestMapping("/symptoms")
    public ResponseEntity<List<Patient>> getPatientsBySymptoms() {

        List<Patient> patients = patientService.findBySymptoms();

        return new ResponseEntity<>(patients, HttpStatus.OK);

    }

    @PostMapping
    public ResponseEntity<Void> savePatient(@RequestBody Patient patient) {

        patientService.savePatient(patient);
        return new ResponseEntity<>(HttpStatus.CREATED);

    }

    @PutMapping
    @RequestMapping("/upload-picture/{id}")
    public ResponseEntity<Patient> updatePatientPicture(@PathVariable("id") Long id, @RequestBody MultipartFile file) {

        return new ResponseEntity<>(patientService.updatePatient(patientService.uploadPicture(id, file)), HttpStatus.OK);

    }

    @PutMapping
    public ResponseEntity<Patient> updatePatient(@RequestBody Patient patient) {

        return new ResponseEntity<>(patientService.updatePatient(patient), HttpStatus.OK);

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePatient(@PathVariable Long id) {

        patientService.deletePatient(id);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);

    }

}
