package com.alek.influentialpeople.hero.controller;

import com.alek.influentialpeople.hero.domain.Hero;
import com.alek.influentialpeople.hero.service.HeroService;
import com.alek.influentialpeople.home.service.ImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class HeroController {

    @Autowired
    private ImageService imageService;
    @Autowired
    private HeroService theHeroService;

    @RequestMapping(path = "/hero", method = RequestMethod.GET)
    public ResponseEntity<List<Hero>> getAllPersons() {

        return ResponseEntity.status(HttpStatus.OK).body(theHeroService.getAllPersons());
    }

    @RequestMapping(path = "/hero", method = RequestMethod.POST)
    public void addPerson(@RequestBody Hero person) {

        theHeroService.addPerson(person);
    }

    @RequestMapping(path = "/hero/{id}", method = RequestMethod.GET)
    public ResponseEntity<Hero> getHero(@PathVariable Integer id) {

        return ResponseEntity.status(HttpStatus.OK).body(theHeroService.getHeroById(id));
    }

//	@RequestMapping(path = "/hero/{id}/profileImage", method = RequestMethod.GET)
//	public ResponseEntity<byte[]> getHeroImage(@PathVariable("id") int id) {
//
//		String heroImagePath = theHeroService.getImagePath(id);
//		if (heroImagePath == null || heroImagePath.isEmpty()) {
//			throw new NotFoundException();
//		}
//		byte[] heroImage = imageService.getImageBytes(heroImagePath); // bytes of image
//		if (heroImage == null || heroImage.length < 1) {
//			throw new NotFoundException();
//		}
//
//		return ResponseEntity.ok().header(HttpHeaders.CONTENT_TYPE, "image/jpg")
//				.header(HttpHeaders.CONTENT_LENGTH, String.valueOf(heroImage.length)).body(heroImage);
//	}
//
//	@RequestMapping(path = "/hero/{id}/profileImage", method = RequestMethod.PUT)
//	public ResponseEntity<byte[]> editAuthorImage(@PathVariable("id") int id) {
//
//		String heroImagePath = theHeroService.getImagePath(id);
//		if (heroImagePath == null || heroImagePath.isEmpty()) {
//			throw new NotFoundException();
//		}
//		byte[] heroImage = imageService.getImageBytes(heroImagePath);
//		if (heroImage == null || heroImage.length < 1) {
//			throw new NotFoundException();
//		}
//
//		return ResponseEntity.ok().header(HttpHeaders.CONTENT_TYPE, "image/jpg")
//				.header(HttpHeaders.CONTENT_LENGTH, String.valueOf(heroImage.length)).body(heroImage);
//	}


}
