package com.alek.influentialpeople.persistance;

import org.springframework.data.repository.CrudRepository;

import com.alek.influentialpeople.persistence.entity.Hero;

public interface HeroRepository extends CrudRepository<Hero, Integer>{
	Hero findByFullName(String fullname);
}
