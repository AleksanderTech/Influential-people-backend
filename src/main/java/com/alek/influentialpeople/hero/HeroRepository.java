package com.alek.influentialpeople.hero;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface HeroRepository extends CrudRepository<Hero, Integer> {

	Hero findByFullName(String fullname);

	@Query("SELECT hero.profileImagePath FROM Hero hero where hero.id = :id")
	String findProfileImagePathById(@Param("id") Integer id);

	

}
