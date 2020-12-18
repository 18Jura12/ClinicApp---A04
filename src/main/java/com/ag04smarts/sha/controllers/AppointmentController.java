package com.ag04smarts.sha.controllers;

import com.ag04smarts.sha.converters.AppointmentFormToAppointment;
import com.ag04smarts.sha.forms.AppointmentForm;
import com.ag04smarts.sha.services.AppointmentService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/create-appointment")
public class AppointmentController {

    private final AppointmentService appointmentService;
    private final AppointmentFormToAppointment converter;

    public AppointmentController(AppointmentService appointmentService, AppointmentFormToAppointment converter) {
        this.appointmentService = appointmentService;
        this.converter = converter;
    }

    @PostMapping
    public ResponseEntity<Void> saveAppointment(@Valid @RequestBody AppointmentForm form) {

        appointmentService.saveAppointment(converter.convert(form));
        return new ResponseEntity<>(HttpStatus.CREATED);

    }

}
