package com.ag04smarts.sha.repositories;

import com.ag04smarts.sha.domain.Appointment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.Optional;

@Repository
public interface AppointmentRepository extends JpaRepository<Appointment, Long> {

    Optional<Appointment> findFirstByAppointmentDateAndDoctor_IdAndPatient_Id(@Param("appointmentDate") Date appointmentDate, @Param("patientId") Long pId, @Param("doctorId") Long dId);

}
