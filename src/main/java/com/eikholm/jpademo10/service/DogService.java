package com.eikholm.jpademo10.service;

import com.eikholm.jpademo10.model.Dog;
import com.eikholm.jpademo10.model.Owner;

import java.util.List;

public interface DogService extends CrudService<Dog, Long>{

    public List<Dog> getOwnerlessDogs();

    public List<Dog> getOwnedDogs();

    public List<Dog> getTwoDogs(int start, int number);

}
