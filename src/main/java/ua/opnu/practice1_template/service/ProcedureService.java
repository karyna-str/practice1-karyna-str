package ua.opnu.practice1_template.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.opnu.practice1_template.model.Procedure;
import ua.opnu.practice1_template.model.Visit;
import ua.opnu.practice1_template.repository.ProcedureRepository;
import ua.opnu.practice1_template.repository.VisitRepository;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Service
public class ProcedureService {

    private ProcedureRepository procedureRepository;

    @Autowired
    public ProcedureService(ProcedureRepository procedureRepository) {
        this.procedureRepository = procedureRepository;
    }

    public Procedure addNewProcedure(Procedure procedure) {
        return procedureRepository.save(procedure);
    }

    public List<Procedure> getProceduresByVisit(Long visitId) {
        return procedureRepository.findAllByVisitId(visitId);
    }

    @Autowired
    private VisitRepository visitRepository;

    public Procedure updateProcedure(Long id, Procedure procedure) {
        Procedure existingProcedure = procedureRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Procedure not found"));
        existingProcedure.setName(procedure.getName());
        existingProcedure.setCost(procedure.getCost());
        if (procedure.getVisit() != null && procedure.getVisit().getId() != null) {
            Visit visit = visitRepository.findById(procedure.getVisit().getId())
                .orElseThrow(() -> new RuntimeException("Visit not found"));
            existingProcedure.setVisit(visit);
        }
        return procedureRepository.save(existingProcedure);
    }

    public void deleteProcedure(Long id) {
        procedureRepository.deleteById(id);
    }

    public BigDecimal getTotalExpensesByPet(Long petId) {
        return Optional.ofNullable(procedureRepository.getAllExpensesPerPet(petId))
            .orElse(BigDecimal.ZERO);
    }

    public List<Object[]> getPopularProcedures(){
        return procedureRepository.getAllPopularProcedures();
    }
}
