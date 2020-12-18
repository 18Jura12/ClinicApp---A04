package com.ag04smarts.sha.services;

import com.ag04smarts.sha.domain.Doctor;
import org.springframework.stereotype.Service;

public interface DoctorService {

    Doctor findById(Long id);
    boolean existsById(Long id);

}
