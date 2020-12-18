package com.ag04smarts.sha.controllers;

import com.ag04smarts.sha.domain.Patient;
import com.ag04smarts.sha.services.PatientService;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.core.Is.is;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class PatientControllerTest {

    @InjectMocks
    private PatientController controller;

    @Mock
    private PatientService patientService;

    @Before
    public void setUp() {

        MockitoAnnotations.initMocks(this);

        controller = new PatientController(patientService);

    }

    @Test
    public void getPatients() throws Exception {

        List<Patient> patients = new ArrayList<>();
        patients.add(new Patient());
        patients.get(0).setFirstName("Jurica");
        patients.get(0).setLastName("Juric");

        when(patientService.getAll()).thenReturn(patients);

        MockMvc mockMvc = MockMvcBuilders.standaloneSetup(controller).build();

        mockMvc.perform(get("/api/patient"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].firstName",is("Jurica")))
                .andExpect(jsonPath("$[0].lastName",is("Juric")));


        assertEquals(1, patients.size());
        verify(patientService, times(1)).getAll();


    }

}