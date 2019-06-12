package com.alek.influentialpeople.persistance;

import org.springframework.data.repository.CrudRepository;

import com.alek.influentialpeople.persistance.entity.Person;

public interface PersonRepository extends CrudRepository<Person, Long>{

}
