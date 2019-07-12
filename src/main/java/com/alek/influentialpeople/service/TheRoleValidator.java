package com.alek.influentialpeople.service;

import org.springframework.beans.factory.annotation.Autowired;

public class TheRoleValidator implements RoleValidator {

	@Autowired
    TheUserService theUserService;
	
	@Override
	public boolean isPublisher() {
		
		return false;
	}

	@Override
	public boolean isAdmin() {
		return false;
	}

}
