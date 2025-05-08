package ua.opnu.practice1_template.controller;

import ua.opnu.practice1_template.model.Pet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ua.opnu.practice1_template.service.PetService;

import java.util.List;

@RestController
@RequestMapping("/pet")
public class PetController {

    private PetService petService;

    @Autowired
    public PetController(PetService petService) {
        this.petService = petService;
    }

    @PostMapping
    public ResponseEntity<Pet> addNewPet(@RequestBody Pet pet) {
        return new ResponseEntity<>(petService.addNewPet(pet), HttpStatus.CREATED);
    }

    @GetMapping("/owner/{id}")
    public List<Pet> getPetsByOwner(@PathVariable Long id) {
        return petService.getPetsByOwner(id);
    }

    @PutMapping("/{id}")
    public Pet updatePet(@PathVariable Long id, @RequestBody Pet pet) {
        return petService.updatePet(id, pet);
    }

    @DeleteMapping("/{id}")
    public void deletePet(@PathVariable Long id) {
        petService.deletePet(id);
    }

    @GetMapping("/statistics")
    public List<Object[]> getPetTypeStatistics() {
        return petService.getPetTypeStatistics();
    }
}
