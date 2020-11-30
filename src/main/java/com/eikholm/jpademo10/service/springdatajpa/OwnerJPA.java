package com.eikholm.jpademo10.service.springdatajpa;

import com.eikholm.jpademo10.model.Dog;
import com.eikholm.jpademo10.model.Owner;
import com.eikholm.jpademo10.repositories.OwnerRepository;
import com.eikholm.jpademo10.service.OwnerService;
import org.springframework.stereotype.Service;

import java.util.*;

@Service  // den gør, at en instans fra denne klasse bliver oprettet, og
// sat ind i OwnerController via konstruktor.
public class OwnerJPA implements OwnerService {
    private final OwnerRepository ownerRepository;

    // Spring vil *selv* oprette en instans, som implementerer OwnerRepository
    public OwnerJPA(OwnerRepository ownerRepository) {
        this.ownerRepository = ownerRepository;
    }

    @Override
    public Set<Owner> findAll() {
        Set<Owner> set = new HashSet<>();
        ownerRepository.findAll().forEach(set::add); // tilføjer alle elementer til set
        return set;
    }

    @Override
    public Owner save(Owner object) {
        return ownerRepository.save(object);
    }

    @Override
    public void delete(Owner object) {
        ownerRepository.delete(object);
    }

    @Override
    public void deleteById(Long aLong) {
        ownerRepository.deleteById(aLong);
    }


    public List<Owner> getAllOwnerById(List<Dog> dogs){
        List<Owner> list = new ArrayList<>();

        for (Dog dog:
             dogs) {
            list.add(dog.getOwner());

        }

        return list;
    }

    @Override
    public Optional<Owner> findById(Long aLong) {
        return ownerRepository.findById(aLong);
    }
}
