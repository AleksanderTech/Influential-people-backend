//package com.alek.influentialpeople.hero;
//
//import com.alek.influentialpeople.home.service.ImageService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.HttpHeaders;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.*;
//
//import java.util.List;
//
//@RestController
//public class HeroController {
//
//	@Autowired
//	private ImageService imageService;
//	@Autowired
//	private HeroService theHeroService;
//// /influential-people/src/main/resources/static/storage/hero/1/profileImage/1.jpg
//	@RequestMapping(path = "/hero", method = RequestMethod.GET)
//	public List<Hero> getAllPersons() {
//
//		return theHeroService.getAllPersons();
//	}
//
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
//
//	@RequestMapping(path = "/hero", method = RequestMethod.POST)
//	public void addPerson(@RequestBody Hero person) {
//		System.out.println(person.toString() + "  !!!!!!!!!!!!!!!!!!!!!");
//		System.out.println();
//		theHeroService.addPerson(person);
//	}
//
//	@RequestMapping(path = "/hero/{id}", method = RequestMethod.GET)
//	public Hero getHero(@PathVariable Integer id) {
//
//		return theHeroService.getHeroById(id);
//	}
//
//}
