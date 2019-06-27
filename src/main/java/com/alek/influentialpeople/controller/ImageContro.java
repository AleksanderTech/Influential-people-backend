package com.alek.influentialpeople.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import com.alek.influentialpeople.exception.BadRequestException;
import com.alek.influentialpeople.exception.NotFoundException;
import com.alek.influentialpeople.service.HeroService;
import com.alek.influentialpeople.service.ImageService;

@RestController
public class ImageContro {

	@Autowired
	HeroService heroService;
	@Autowired
	ImageService imageService;

	@RequestMapping(path = "/hero/{id}/profileImage", method = RequestMethod.GET)
	public ResponseEntity<byte[]> getAuthorImage(@PathVariable("id") int id) {

		if (id < 0) {
			throw new BadRequestException();
		}

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

//	    @PutMapping("/{id}/image")
//	    public Response putAuthorImage(@PathVariable("id") long id, @RequestPart("image") MultipartFile multipartFile) {
//		if (id < 1) {
//		    throw new BadRequestException();
//		}
//		validateRole();
//		byte[] image = imageService.getImageBytes(multipartFile, AUTHOR_IMAGE_WIDTH, AUTHOR_IMAGE_HEIGHT);
//		if ((image == null || image.length < 1)) {
//		    throw new WrongDataException();
//		}
//		String authorImagePath = authorsImagesPath + "/" + imageService.getImageFileNameWithFormat(String.valueOf(id));
//		File imageFile = imageService.createImageFileFromBytes(authorImagePath, image);
//		if (imageFile == null || !imageFile.exists()) {
//		    throw new InternalErrorException();
//		}
//		if (!getAuthorService().saveImagePath(id, authorImagePath)) {
//		    throw new WrongDataException("Can not save image path!");
//		}
//		return new Response(Response.OK);
//	    }
}

//    @GetMapping("/author/{id}/image")
//    public ResponseEntity<byte[]> getAuthorImage(@PathVariable("id") long id) {
//	if (id < 0) {
//	    throw new BadRequestException();
//	}
//	String authorImagePath = authorService.getImagePath(id);
//	if (authorImagePath == null || authorImagePath.isEmpty()) {
//	    throw new NotFoundException();
//	}
//	byte[] authorImage = imageService.getImageBytes(authorImagePath);
//	if (authorImage == null || authorImage.length < 1) {
//	    throw new NotFoundException();
//	}
//	return ResponseEntity.ok().header(HttpHeaders.CONTENT_TYPE, "image/jpg")
//		.header(HttpHeaders.CONTENT_LENGTH, String.valueOf(authorImage.length)).body(authorImage);
//    }