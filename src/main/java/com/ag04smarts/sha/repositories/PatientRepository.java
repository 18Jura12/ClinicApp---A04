package com.ag04smarts.sha.repositories;

import com.ag04smarts.sha.domain.Patient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Repository
public interface PatientRepository extends JpaRepository<Patient, Long> {

    Optional<List<Patient>> findAllByAgeGreaterThanAndEnlistmentDateGreaterThan(@Param("age") Integer age, @Param("enlistmentDate") Date enlistmentDate);

    @Query("SELECT DISTINCT p FROM Patient p JOIN p.patientMedicalRecord.symptoms s WHERE s.description = :firstSymptom OR s.description = :secondSymptom")
    List<Patient> patientSymptom(@Param("firstSymptom") String first, @Param("secondSymptom") String second);

    Optional<Patient> findById(@Param("id") Long id);

    boolean existsById(@Param("id") Long id);

}
