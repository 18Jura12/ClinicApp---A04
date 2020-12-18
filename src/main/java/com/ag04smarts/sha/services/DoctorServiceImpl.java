package com.ag04smarts.sha.services;

import com.ag04smarts.sha.domain.Doctor;
import com.ag04smarts.sha.repositories.DoctorRepository;
import org.springframework.stereotype.Service;

@Service
public class DoctorServiceImpl implements DoctorService {

    private final DoctorRepository doctorRepository;

    public DoctorServiceImpl(DoctorRepository doctorRepository) {
        this.doctorRepository = doctorRepository;
    }

    @Override
    public Doctor findById(Long id) {

        return doctorRepository.findById(id).get();

    }

    @Override
    public boolean existsById(Long id) {

        return doctorRepository.existsById(id);

    }
}
