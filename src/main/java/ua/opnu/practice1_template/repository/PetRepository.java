package ua.opnu.practice1_template.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import ua.opnu.practice1_template.model.Pet;

import java.util.List;

public interface PetRepository extends JpaRepository<Pet, Long> {

  List<Pet> findByOwnerId(Long id);

  @Query("SELECT p.species, COUNT(p) FROM Pet p GROUP BY p.species")
  List<Object[]> getPetTypeStatistics();

}
