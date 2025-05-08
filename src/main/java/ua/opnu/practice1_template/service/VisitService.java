package ua.opnu.practice1_template.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.opnu.practice1_template.model.Pet;
import ua.opnu.practice1_template.model.Visit;
import ua.opnu.practice1_template.repository.PetRepository;
import ua.opnu.practice1_template.repository.VisitRepository;

import java.time.LocalDate;
import java.util.List;

@Service
public class VisitService {

    private VisitRepository visitRepository;

    @Autowired
    public VisitService(VisitRepository visitRepository) {
        this.visitRepository = visitRepository;
    }

    public Visit addNewVisit(Visit visit) {
        return visitRepository.save(visit);
    }

    public List<Visit> getVisitsByPet(Long id) {
        return visitRepository.findAllByPetId(id);
    }

    @Autowired
    private PetRepository petRepository;

    public Visit updateVisit(Long id, Visit visit) {
        Visit existingVisit = visitRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Visit not found"));
        existingVisit.setDate(visit.getDate());
        existingVisit.setReason(visit.getReason());
        if (visit.getPet() != null && visit.getPet().getId() != null) {
            Pet pet = petRepository.findById(visit.getPet().getId())
                .orElseThrow(() -> new RuntimeException("Pet not found"));
            existingVisit.setPet(pet);
        }
        return visitRepository.save(existingVisit);
    }

    public void deleteVisit(Long id) {
        visitRepository.deleteById(id);
    }

    public List<Visit> getVisitsByDate(LocalDate date) {
        return visitRepository.findByDate(date);
    }

    public Visit getLastVisit(Long petId) {
        List<Visit> visits = visitRepository.findLastVisitByPetId(petId);
        if (visits.isEmpty()) {
            throw new RuntimeException("No visits found for pet");
        }
        return visits.get(0);
    }
}
