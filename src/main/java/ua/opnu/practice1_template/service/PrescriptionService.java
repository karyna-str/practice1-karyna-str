package ua.opnu.practice1_template.service;

import ua.opnu.practice1_template.model.Prescription;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.opnu.practice1_template.repository.PrescriptionRepository;

import java.util.List;

@Service
public class PrescriptionService {

    private PrescriptionRepository prescriptionRepository;

    @Autowired
    public PrescriptionService(PrescriptionRepository prescriptionRepository) {
        this.prescriptionRepository = prescriptionRepository;
    }

    public Prescription addNewPrescription(Prescription prescription) {
        return prescriptionRepository.save(prescription);
    }

    public List<Prescription> getPrescriptionsByVisit(Long visitId) {
        return prescriptionRepository.findAllByVisitId(visitId);
    }

    public Prescription updatePrescription(Long id, Prescription prescription) {
        Prescription existingPrescription = prescriptionRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Prescription not found"));
        existingPrescription.setMedication(prescription.getMedication());
        existingPrescription.setDosage(prescription.getDosage());
        existingPrescription.setVisit(prescription.getVisit());
        return prescriptionRepository.save(existingPrescription);
    }

    public void deletePrescription(Long id) {
        prescriptionRepository.deleteById(id);
    }
}
