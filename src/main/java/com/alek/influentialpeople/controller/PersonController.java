package com.alek.influentialpeople.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.alek.influentialpeople.persistance.entity.Hero;
import com.alek.influentialpeople.service.PersonService;

@RestController
public class PersonController { // potrrzebuje jsona 
	
	@Autowired
	PersonService personService;
	
	@RequestMapping(path = "/person", method = RequestMethod.GET)
	public List<Hero> getAllPersons() {
		
		return personService.getAllPersons();
	}

	@RequestMapping(path = "/person", method = RequestMethod.POST)
	public void addPerson(@RequestBody Hero person) {
		personService.addPerson(person);
	}

//	@RequestMapping(path = "/user", method = RequestMethod.GET)
//	public User getUser3() {
//
//		return new User();
//	}
//
//	@RequestMapping(path = "/user", method = RequestMethod.GET)
//	public User getUser4() {
//
//		return new User();
//	}
//
//	@RequestMapping(path = "/user", method = RequestMethod.GET)
//	public User getUser5() {
//
//		return new User();
//	}
//
//	@RequestMapping(path = "/user", method = RequestMethod.GET)
//	public User getUs1er() {
//
//		return new User();
//	}

}
