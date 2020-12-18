package com.ag04smarts.sha.repositories;

import com.ag04smarts.sha.domain.PatientTreatmentHistory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PatientTreatmentHistoryRepository extends JpaRepository<PatientTreatmentHistory, Long> {
}
