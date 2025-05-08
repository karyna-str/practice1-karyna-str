package ua.opnu.practice1_template.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import ua.opnu.practice1_template.model.Visit;

import java.time.LocalDate;
import java.util.List;

public interface VisitRepository extends JpaRepository<Visit, Long> {
    List<Visit> findAllByPetId(Long id);

    List<Visit> findByDate(LocalDate date);

    @Query("SELECT v FROM Visit v WHERE v.pet.id = :petId ORDER BY v.date DESC")
    List<Visit> findLastVisitByPetId(@Param("petId") Long petId);
}
