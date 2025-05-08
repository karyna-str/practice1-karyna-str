package ua.opnu.practice1_template.controller;

import ua.opnu.practice1_template.model.Visit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ua.opnu.practice1_template.service.VisitService;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/visit")
public class VisitController {

    private VisitService visitService;

    @Autowired
    public VisitController(VisitService visitService) {
        this.visitService = visitService;
    }

    @PostMapping
    public ResponseEntity<Visit> addNewVisit(@RequestBody Visit visit) {
        return new ResponseEntity<>(visitService.addNewVisit(visit), HttpStatus.CREATED);
    }

    @GetMapping("/pet/{id}")
    public List<Visit> getVisitsByPet(@PathVariable Long id) {
        return visitService.getVisitsByPet(id);
    }

    @PutMapping("/{id}")
    public Visit updateVisit(@PathVariable Long id, @RequestBody Visit visit) {
        return visitService.updateVisit(id, visit);
    }

    @DeleteMapping("/{id}")
    public void deleteVisit(@PathVariable Long id) {
        visitService.deleteVisit(id);
    }

    @GetMapping("/{date}")
    public List<Visit> getVisitsByDate(@PathVariable LocalDate date) {
        return visitService.getVisitsByDate(date);
    }

    @GetMapping("/pets/{petId}/last-visit")
    public Visit getLastVisit(@PathVariable Long petId) {
        return visitService.getLastVisit(petId);
    }
}
