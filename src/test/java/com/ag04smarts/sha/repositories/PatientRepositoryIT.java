package com.ag04smarts.sha.repositories;

import com.ag04smarts.sha.converters.EnlistmentFormToPatient;
import com.ag04smarts.sha.domain.Patient;
import com.ag04smarts.sha.services.PatientService;
import com.ag04smarts.sha.services.PatientServiceImpl;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureTestDatabase
public class PatientRepositoryIT {

    @Autowired
    private PatientRepository patientRepository;

    @Before
    public void setUp() {

    }

    @Test
    public void findAllByAgeGreaterThanAndEnlistmentDateGreaterThan() {

        PatientService patientService = new PatientServiceImpl(patientRepository, new EnlistmentFormToPatient());

        List<Patient> patients = patientService.findByAgeAndDate();

        assertEquals(2, patients.size());

    }
}