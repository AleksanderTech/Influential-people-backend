package com.alek.influentialpeople.persistance;

import org.springframework.data.repository.CrudRepository;

import com.alek.influentialpeople.persistence.entity.User;

public interface UserRepository extends CrudRepository<User, Long>{
	 User findByUsername(String username);
}
