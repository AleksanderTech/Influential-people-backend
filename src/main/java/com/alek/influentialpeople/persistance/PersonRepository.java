package com.alek.influentialpeople.persistance;

import org.springframework.data.repository.CrudRepository;

import com.alek.influentialpeople.persistance.entity.Hero;

public interface PersonRepository extends CrudRepository<Hero, Long>{

}
