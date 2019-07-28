package com.alek.influentialpeople.home.service;

import com.alek.influentialpeople.user.TheUserService;
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
