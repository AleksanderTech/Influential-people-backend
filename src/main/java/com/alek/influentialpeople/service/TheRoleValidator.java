package com.alek.influentialpeople.service;

import org.springframework.beans.factory.annotation.Autowired;

public class TheRoleValidator implements RoleValidator {

	@Autowired
	UserService userService;
	
	@Override
	public boolean isPublisher() {
		
		return false;
	}

	@Override
	public boolean isAdmin() {
		return false;
	}

}
