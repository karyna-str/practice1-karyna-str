package ua.opnu.practice1_template.repository;

import ua.opnu.practice1_template.model.Prescription;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PrescriptionRepository extends JpaRepository<Prescription, Long> {
    List<Prescription> findAllByVisitId(Long visitId);
}
