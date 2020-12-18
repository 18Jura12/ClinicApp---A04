package com.ag04smarts.sha.forms;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.FutureOrPresent;
import javax.validation.constraints.Positive;

@Getter
@Setter
@NoArgsConstructor
public class AppointmentForm {

    @Positive
    private Long patientId;

    @Positive
    private Long doctorId;

    @FutureOrPresent
    private String appointmentDate;

}
