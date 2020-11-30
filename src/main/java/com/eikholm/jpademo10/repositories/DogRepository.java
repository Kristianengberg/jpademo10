package com.eikholm.jpademo10.repositories;

import com.eikholm.jpademo10.model.Dog;
import com.eikholm.jpademo10.model.Owner;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface DogRepository extends PagingAndSortingRepository<Dog, Long> {

}
