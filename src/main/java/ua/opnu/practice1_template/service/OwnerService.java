package ua.opnu.practice1_template.service;

import ua.opnu.practice1_template.model.Owner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.opnu.practice1_template.repository.OwnerRepository;

import java.util.List;

@Service
public class OwnerService {

    private OwnerRepository ownerRepository;

    @Autowired
    public OwnerService(OwnerRepository ownerRepository) {
       this.ownerRepository = ownerRepository;
    }

    public Owner addNewOwner(Owner owner) {
        return ownerRepository.save(owner);
    }

    public List<Owner> getAllOwners(){
        return ownerRepository.findAll();
    }

    public Owner updateOwner(Long id, Owner owner) {
        Owner existingOwner = ownerRepository.findById(id).orElseThrow(() -> new RuntimeException("Owner not found"));
        existingOwner.setName(owner.getName());
        existingOwner.setPhone(owner.getPhone());
        return ownerRepository.save(existingOwner);
    }

    public void deleteOwner(Long id) {
        ownerRepository.deleteById(id);
    }
}
