package com.ag04smarts.sha.forms;

import com.ag04smarts.sha.domain.Gender;
import com.opencsv.bean.CsvBindByName;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Positive;

@Getter
@Setter
@NoArgsConstructor
public class EnlistmentForm {

    @CsvBindByName(required = true, column = "name")
    @Length(min = 2, max = 30, message = "{Length.enlistmentForm.firstName}")
    private String firstName;

    @CsvBindByName(required = true, column = "surname")
    @NotBlank(message = "{NonBlank.enlistmentForm.lastName}")
    private String lastName;

    @CsvBindByName(required = true)
    @Email(message = "{Email.enlistmentForm.email}")
    private String email;

    @CsvBindByName(required = true)
    @Positive
    private Integer age;

    @CsvBindByName(required = true, column = "phone")
    @Length(min = 6, max = 20)
    private String phoneNumber;

    @CsvBindByName(required = true)
    private Gender gender;

}
