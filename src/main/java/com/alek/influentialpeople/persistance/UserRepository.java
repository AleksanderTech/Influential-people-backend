package com.alek.influentialpeople.persistance;

import org.springframework.data.repository.CrudRepository;

import com.alek.influentialpeople.persistance.entity.User;

public interface UserRepository extends CrudRepository<User, Long>{
	 User findByUsername(String username);
}
