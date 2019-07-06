package com.alek.influentialpeople.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import com.alek.influentialpeople.exception.NotFoundException;
import com.alek.influentialpeople.jsonview.View;
import com.alek.influentialpeople.persistence.entity.Hero;
import com.alek.influentialpeople.service.HeroService;
import com.alek.influentialpeople.service.ImageService;
import com.fasterxml.jackson.annotation.JsonView;

@RestController
public class HeroController {

	@Autowired
	private ImageService imageService;
	@Autowired
	private HeroService heroService;
// /influential-people/src/main/resources/static/storage/hero/1/profileImage/1.jpg
	@JsonView(View.Public.class)
	@RequestMapping(path = "/hero", method = RequestMethod.GET)
	public List<Hero> getAllPersons() {

		return heroService.getAllPersons();
	}

	@RequestMapping(path = "/hero/{id}/profileImage", method = RequestMethod.GET)
	public ResponseEntity<byte[]> getHeroImage(@PathVariable("id") int id) {

		String heroImagePath = heroService.getImagePath(id);
		if (heroImagePath == null || heroImagePath.isEmpty()) {
			throw new NotFoundException();
		}
		byte[] heroImage = imageService.getImageBytes(heroImagePath); // bytes of image
		if (heroImage == null || heroImage.length < 1) {
			throw new NotFoundException();
		}

		return ResponseEntity.ok().header(HttpHeaders.CONTENT_TYPE, "image/jpg")
				.header(HttpHeaders.CONTENT_LENGTH, String.valueOf(heroImage.length)).body(heroImage);
	}
	
	@RequestMapping(path = "/hero/{id}/profileImage", method = RequestMethod.PUT)
	public ResponseEntity<byte[]> editAuthorImage(@PathVariable("id") int id) {

		String heroImagePath = heroService.getImagePath(id);
		if (heroImagePath == null || heroImagePath.isEmpty()) {
			throw new NotFoundException();
		}
		byte[] heroImage = imageService.getImageBytes(heroImagePath); 
		if (heroImage == null || heroImage.length < 1) {
			throw new NotFoundException();
		}

		return ResponseEntity.ok().header(HttpHeaders.CONTENT_TYPE, "image/jpg")
				.header(HttpHeaders.CONTENT_LENGTH, String.valueOf(heroImage.length)).body(heroImage);
	}

	@RequestMapping(path = "/hero", method = RequestMethod.POST)
	public void addPerson(@RequestBody Hero person) {
		System.out.println(person.toString() + "  !!!!!!!!!!!!!!!!!!!!!");
		System.out.println();
		heroService.addPerson(person);
	}

	@JsonView(View.Profile.class)
	@RequestMapping(path = "/hero/{id}", method = RequestMethod.GET)
	public Hero getHero(@PathVariable Integer id) {

		return heroService.getHeroById(id);
	}

}
