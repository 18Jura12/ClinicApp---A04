package com.ag04smarts.sha.services;

import com.ag04smarts.sha.domain.Appointment;
import com.ag04smarts.sha.exceptions.AlreadyExistsException;
import com.ag04smarts.sha.repositories.AppointmentRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class AppointmentServiceImpl implements AppointmentService {

    private final AppointmentRepository appointmentRepository;

    public AppointmentServiceImpl(AppointmentRepository appointmentRepository) {
        this.appointmentRepository = appointmentRepository;
    }

    @Override
    public void saveAppointment(Appointment appointment) {

        if(appointment == null) {

            throw new NullPointerException("given null Object as an appointment to be saved.");

        }

        if(appointmentRepository.findFirstByAppointmentDateAndDoctor_IdAndPatient_Id(appointment.getAppointmentDate(), appointment.getPatient().getId(), appointment.getDoctor().getId()).isEmpty()) {

            appointmentRepository.save(appointment);
            return;

        }

        throw new AlreadyExistsException("The suggested appointment already exists for the given date!");

    }
}
