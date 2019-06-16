package com.alek.influentialpeople.controller;

import java.util.List;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import com.alek.influentialpeople.persistance.entity.Article;
import com.alek.influentialpeople.persistance.entity.Hero;
import com.alek.influentialpeople.service.BiographyService;

@RestController
public class BiographyController { // potrrzebuje jsona

	@Autowired
	BiographyService biographyService;

	@RequestMapping(path = "/person/{id}/biography", method = RequestMethod.GET)
	public List<Article> getAllBiographies(HttpServletResponse s) {

		return biographyService.getAllBiographies();
	}

	@RequestMapping(path = "/person/{id}/biography", method = RequestMethod.POST)
	public void addBiography(@RequestBody Article biography, @PathVariable String id) {
		int biographyId = Integer.valueOf(id);
		biography.setPerson(new Hero(biographyId, "", 0L));
		System.out.println(biography.toString());
//		long userId = biography.getUser().getId();
//		biography.setUser(new User("", "", "", userId, 0, ""));
		biographyService.addBiography(biography);
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
