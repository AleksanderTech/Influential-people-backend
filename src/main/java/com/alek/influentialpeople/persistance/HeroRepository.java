package com.alek.influentialpeople.persistance;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.alek.influentialpeople.persistence.entity.Hero;

public interface HeroRepository extends CrudRepository<Hero, Integer> {

	Hero findByFullName(String fullname);

	@Query("SELECT hero.profileImagePath FROM Hero hero where hero.id = :id") 
    String findProfileImagePathById(@Param("id") Integer id);
	
	
}
