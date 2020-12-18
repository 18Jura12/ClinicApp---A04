package com.ag04smarts.sha.converters;

import com.ag04smarts.sha.domain.Appointment;
import com.ag04smarts.sha.exceptions.BadRequestException;
import com.ag04smarts.sha.forms.AppointmentForm;
import com.ag04smarts.sha.services.DoctorService;
import com.ag04smarts.sha.services.PatientService;
import com.ag04smarts.sha.util.Util;
import com.sun.istack.Nullable;
import lombok.Synchronized;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import javax.validation.Valid;

@Component
public class AppointmentFormToAppointment implements Converter<AppointmentForm, Appointment> {

    private final PatientService patientService;
    private final DoctorService doctorService;

    public AppointmentFormToAppointment(PatientService patientService, DoctorService doctorService) {
        this.patientService = patientService;
        this.doctorService = doctorService;
    }

    @Synchronized
    @Nullable
    @Override
    public Appointment convert(@Valid AppointmentForm source) {

        if(source == null) {

            throw new BadRequestException("Given null Object to converter");

        }

        if(doctorService.existsById(source.getDoctorId()) && patientService.existsById(source.getPatientId())) {

            Util util = new Util("dd.MM.yyyy. HH:mm");

            Appointment appointment = new Appointment();
            appointment.setDoctor(doctorService.findById(source.getDoctorId()));
            appointment.setPatient(patientService.findById(source.getPatientId()));
            appointment.setAppointmentDate(util.makeDate(source.getAppointmentDate()));

            return appointment;

        }

        throw new IllegalArgumentException("Row with provided id is not in database.");

    }
}
