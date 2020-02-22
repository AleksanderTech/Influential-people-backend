package com.alek.influentialpeople.hero.category.controller;

import com.alek.influentialpeople.common.Url;
import com.alek.influentialpeople.common.abstraction.ImageService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/category")
public class CategoryImageController {

    private final ImageService<String> categoryService;

    public CategoryImageController(@Qualifier("categoryImageService") ImageService<String> categoryService) {
        this.categoryService = categoryService;
    }

    @PreAuthorize("hasRole('ADMIN')")
    @RequestMapping(path = "/{name}/image", method = RequestMethod.PUT)
    public ResponseEntity<Url> uploadImage(@PathVariable String name, @RequestPart(value = "image", required = false) MultipartFile image) {
        return new ResponseEntity<>(new Url(categoryService.storeImage(name, image)), HttpStatus.OK);
    }

    @RequestMapping(path = "/{name}/image", method = RequestMethod.GET, produces = MediaType.IMAGE_JPEG_VALUE)
    public ResponseEntity<byte[]> getImage(@PathVariable("name") String name) {
        return ResponseEntity.ok().contentType(MediaType.IMAGE_JPEG).body(categoryService.getImage(name));
    }
}
