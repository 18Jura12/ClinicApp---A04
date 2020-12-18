package com.ag04smarts.sha.controllers;

import com.ag04smarts.sha.converters.EnlistmentFormToPatient;
import com.ag04smarts.sha.domain.Patient;
import com.ag04smarts.sha.forms.EnlistmentForm;
import com.ag04smarts.sha.services.PatientService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/enlist-patient")
public class EnlistmentController {

    private final PatientService patientService;
    private final EnlistmentFormToPatient converter;

    public EnlistmentController(PatientService patientService, EnlistmentFormToPatient converter) {
        this.patientService = patientService;
        this.converter = converter;
    }

    @PostMapping
    public ResponseEntity<Void> savePatient(@Valid @RequestBody EnlistmentForm form) {

            patientService.savePatient(converter.convert(form));
            return new ResponseEntity<>(HttpStatus.CREATED);

    }

    @PostMapping("/csv")
    public ResponseEntity<Object> savePatientsCsv(@RequestBody MultipartFile file) {

            List<Patient> patients = patientService.uploadCsv(file);
            patients.forEach(patientService::savePatient);

            return new ResponseEntity<>(patients, HttpStatus.OK);

    }

}
