package com.alek.influentialpeople.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.alek.influentialpeople.persistance.entity.BiographyComment;
import com.alek.influentialpeople.service.BiographyCommentService;

@RestController //serializacja
public class BiographyCommentController { // potrrzebuje jsona 
	
	@Autowired
	BiographyCommentService biographyCommentService;
	
	@RequestMapping(path = "/person/{id}/biography/{id}/comment", method = RequestMethod.GET)
	public List<BiographyComment> getAllUsers() {
		
		return biographyCommentService.getAllBiographyComments();
	}

	@RequestMapping(path = "/person/{id}/biography/{id}/comment", method = RequestMethod.POST)
	public void addUser(@RequestBody BiographyComment biographyComment) { //deserializacja
		biographyCommentService.addBiographyComment(biographyComment);
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
