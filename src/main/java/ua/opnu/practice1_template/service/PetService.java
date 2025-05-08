package ua.opnu.practice1_template.service;

import ua.opnu.practice1_template.model.Pet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.opnu.practice1_template.repository.PetRepository;

import java.util.List;

@Service
public class PetService {

    private PetRepository petRepository;

    @Autowired
    public PetService(PetRepository petRepository) {
        this.petRepository = petRepository;
    }

    public Pet addNewPet(Pet pet) {
        return petRepository.save(pet);
    }

    public List<Pet> getPetsByOwner(Long id) {
        return petRepository.findByOwnerId(id);
    }

    public Pet updatePet(Long id, Pet pet) {
        Pet existingPet = petRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Pet not found"));
        existingPet.setName(pet.getName());
        existingPet.setSpecies(pet.getSpecies());
        existingPet.setBirthDate(pet.getBirthDate());
        existingPet.setOwner(pet.getOwner());
        return petRepository.save(existingPet);
    }

    public void deletePet(Long id) {
        petRepository.deleteById(id);
    }

    public List<Object[]> getPetTypeStatistics() {
        return petRepository.getPetTypeStatistics();
    }
}
