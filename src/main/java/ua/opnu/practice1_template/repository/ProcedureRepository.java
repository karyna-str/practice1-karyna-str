package ua.opnu.practice1_template.repository;

import org.springframework.data.jpa.repository.Query;
import ua.opnu.practice1_template.model.Procedure;
import org.springframework.data.jpa.repository.JpaRepository;

import java.math.BigDecimal;
import java.util.List;

public interface ProcedureRepository extends JpaRepository<Procedure, Long> {
    List<Procedure> findAllByVisitId(Long visitId);

    @Query("SELECT SUM(procedure.cost) FROM Procedure procedure INNER JOIN Visit visit ON visit.id = procedure.id WHERE visit.pet.id = :petId")
    BigDecimal getAllExpensesPerPet(Long petId);

    @Query("SELECT p.name, COUNT(p) FROM Procedure p GROUP BY p.name ORDER BY COUNT(p) DESC")
    List<Object[]> getAllPopularProcedures();
}
