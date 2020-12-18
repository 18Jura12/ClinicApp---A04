package com.ag04smarts.sha.converters;

import com.ag04smarts.sha.domain.Patient;
import com.ag04smarts.sha.domain.Status;
import com.ag04smarts.sha.exceptions.BadRequestException;
import com.ag04smarts.sha.forms.EnlistmentForm;
import com.sun.istack.Nullable;
import lombok.Synchronized;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import javax.validation.Valid;
import java.util.Date;
import java.util.HashSet;

@Component
public class EnlistmentFormToPatient implements Converter<EnlistmentForm, Patient> {

    @Synchronized
    @Nullable
    @Override
    public Patient convert(@Valid EnlistmentForm source) {

        if(source == null) {

            throw new BadRequestException("Given null Object to converter");

        }

        final Patient patient = new Patient();
        patient.setFirstName(source.getFirstName());
        patient.setLastName(source.getLastName());
        patient.setEmail(source.getEmail());
        patient.setPhoneNumber(source.getPhoneNumber());
        patient.setAge(source.getAge());
        patient.setGender(source.getGender());
        patient.setEnlistmentDate(new Date());
        patient.setStatus(Status.ENLISTED);
        patient.setPatientTreatmentHistories(new HashSet<>());

        return patient;

    }

}
