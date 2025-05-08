package ua.opnu.practice1_template.controller;

import ua.opnu.practice1_template.model.Procedure;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ua.opnu.practice1_template.service.ProcedureService;

import java.math.BigDecimal;
import java.util.List;

@RestController
@RequestMapping("/procedure")
public class ProcedureController {

    private ProcedureService procedureService;

    @Autowired
    public ProcedureController(ProcedureService procedureService) {
        this.procedureService = procedureService;

    }

    @PostMapping
    public ResponseEntity<Procedure> addNewProcedure(@RequestBody Procedure procedure) {
        return new ResponseEntity<>(procedureService.addNewProcedure(procedure), HttpStatus.CREATED);
    }

    @GetMapping("/visit/{visitId}")
    public List<Procedure> getProceduresByVisit(@PathVariable Long visitId) {
        return procedureService.getProceduresByVisit(visitId);
    }

    @PutMapping("/{id}")
    public Procedure updateProcedure(@PathVariable Long id, @RequestBody Procedure procedure) {
        return procedureService.updateProcedure(id, procedure);
    }

    @DeleteMapping("/{id}")
    public void deleteProcedure(@PathVariable Long id) {
        procedureService.deleteProcedure(id);
    }

    @GetMapping("/expenses/total/{petId}")
    public BigDecimal totalExpensesByPet(@PathVariable Long petId) {
        return procedureService.getTotalExpensesByPet(petId);
    }

    @GetMapping("/popular")
    public List<Object[]> popularProcedures() {
        return procedureService.getPopularProcedures();
    }
}
