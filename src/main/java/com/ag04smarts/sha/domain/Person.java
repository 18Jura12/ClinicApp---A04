package com.ag04smarts.sha.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.persistence.MappedSuperclass;

@Data
@MappedSuperclass
public class Person {

    private String firstName;
    private String lastName;

    public Person() {
    }

    public Person(String firstName, String lastName) {

        this.firstName = firstName;
        this.lastName = lastName;

    }
}
