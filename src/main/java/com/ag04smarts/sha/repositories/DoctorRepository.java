package com.ag04smarts.sha.repositories;

import com.ag04smarts.sha.domain.Doctor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface DoctorRepository extends JpaRepository<Doctor, Long> {

    Optional<Doctor> findById(@Param("id") Long id);

    boolean existsById(@Param("id") Long id);

}
