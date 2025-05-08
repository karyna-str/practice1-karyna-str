package ua.opnu.practice1_template.controller;

import ua.opnu.practice1_template.model.Prescription;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ua.opnu.practice1_template.service.PrescriptionService;

import java.util.List;

@RestController
@RequestMapping("/prescription")
public class PrescriptionController {

    private PrescriptionService prescriptionService;

    @Autowired
    public PrescriptionController(PrescriptionService prescriptionService) {
        this.prescriptionService = prescriptionService;
    }

    @PostMapping
    public ResponseEntity<Prescription> addNewPrescription(@RequestBody Prescription prescription) {
        return new ResponseEntity<>(prescriptionService.addNewPrescription(prescription), HttpStatus.CREATED);
    }

    @GetMapping("/visit/{visitId}")
    public List<Prescription> getPrescriptionsByVisit(@PathVariable Long visitId) {
        return prescriptionService.getPrescriptionsByVisit(visitId);
    }

    @PutMapping("/{id}")
    public Prescription updatePrescription(@PathVariable Long id, @RequestBody Prescription prescription) {
        return prescriptionService.updatePrescription(id, prescription);
    }

    @DeleteMapping("/{id}")
    public void deletePrescription(@PathVariable Long id) {
        prescriptionService.deletePrescription(id);
    }

}
