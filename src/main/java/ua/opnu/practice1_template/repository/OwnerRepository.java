package ua.opnu.practice1_template.repository;

import ua.opnu.practice1_template.model.Owner;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OwnerRepository extends JpaRepository<Owner, Long> {
}
