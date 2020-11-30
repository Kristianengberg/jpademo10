package com.eikholm.jpademo10.service.springdatajpa;

import com.eikholm.jpademo10.model.Dog;
import com.eikholm.jpademo10.model.Owner;
import com.eikholm.jpademo10.repositories.DogRepository;
import com.eikholm.jpademo10.repositories.OwnerRepository;
import com.eikholm.jpademo10.service.DogService;
import com.eikholm.jpademo10.service.OwnerService;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.awt.print.Pageable;
import java.util.*;

@Service  // den gør, at en instans fra denne klasse bliver oprettet, og
// sat ind i OwnerController via konstruktor.
public class DogJPA implements DogService {
    private final DogRepository dogRepository;

    // Spring vil *selv* oprette en instans, som implementerer OwnerRepository
    public DogJPA(DogRepository dogRepository) {
        this.dogRepository = dogRepository;
    }

    @Override
    public Set<Dog> findAll() {
        Set<Dog> set = new HashSet<>();
        dogRepository.findAll().forEach(set::add); // tilføjer alle elementer til set
        return set;
    }

    @Override
    public Dog save(Dog object) {
        return dogRepository.save(object);
    }

    @Override
    public void delete(Dog object) {
        dogRepository.delete(object);
    }

    @Override
    public void deleteById(Long aLong) {
        dogRepository.deleteById(aLong);
    }


    @Override
    public Optional<Dog> findById(Long aLong) {
        return dogRepository.findById(aLong);
    }


    public List<Dog> getOwnerlessDogs() {
        List<Dog> list = new ArrayList<>();
        dogRepository.findAll().forEach(dog -> {
            if(dog.getOwner() == null) {
                list.add(dog);
            }
        });
        return list;
    }

    public List<Dog> getOwnedDogs(){
        List<Dog> list = new ArrayList<>();
        dogRepository.findAll().forEach(dog -> {
            if(dog.getOwner() != null){
                list.add(dog);
            }
        });
        return list;
    }

    @Override
    public List<Dog> getTwoDogs(int start, int number) {
        List<Dog> returnDogs = new ArrayList<>();
        PageRequest getNDogs = PageRequest.of(start, number, Sort.by("name"));
        return null;
    }
}
