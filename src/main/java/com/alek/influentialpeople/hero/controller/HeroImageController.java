package com.alek.influentialpeople.hero.controller;

import com.alek.influentialpeople.common.Url;
import com.alek.influentialpeople.common.abstraction.ImageService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/hero")
public class HeroImageController {

    private final ImageService<String> imageService;

    public HeroImageController(@Qualifier("heroImageService") ImageService<String> imageService) {
        this.imageService = imageService;
    }

    @RequestMapping(path = "/{name}/image", method = RequestMethod.PUT)
    public ResponseEntity<Url> uploadAvatarImage(@PathVariable String name, @RequestPart(value = "image", required = false) MultipartFile image) {
        return new ResponseEntity<>(new Url(imageService.storeImage(name, image)),HttpStatus.OK);
    }

    @RequestMapping(path = "/{name}/image", method = RequestMethod.GET, produces = MediaType.IMAGE_JPEG_VALUE)
    public ResponseEntity<byte[]> getAvatarImage(@PathVariable("name") String name) {
        byte[] image = imageService.getImage(name);
        return ResponseEntity.ok().contentType(MediaType.IMAGE_JPEG).body(image);
    }
}
