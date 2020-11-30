package com.eikholm.jpademo10.service;

import com.eikholm.jpademo10.model.Dog;
import com.eikholm.jpademo10.model.Owner;

import java.util.List;

public interface OwnerService extends CrudService<Owner, Long>{

    Object getAllOwnerById(List<Dog> ownedDogs);
}
