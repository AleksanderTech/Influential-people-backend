package com.alek.influentialpeople.hero.category.controller;

import com.alek.influentialpeople.hero.category.service.CategoryImageService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/category")
public class CategoryImageController {

    private CategoryImageService categoryService;

    public CategoryImageController(CategoryImageService categoryService) {
        this.categoryService = categoryService;
    }

    @RequestMapping(path = "/{name}/image", method = RequestMethod.PUT)
    public ResponseEntity uploadImage(@PathVariable String name, @RequestPart(value = "image", required = false) MultipartFile image) {

        categoryService.storeImage(name, image);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @RequestMapping(path = "/{name}/image", method = RequestMethod.GET, produces = MediaType.IMAGE_JPEG_VALUE)
    public ResponseEntity<byte[]> getImage(@PathVariable("name") String name) {

        byte[] image = categoryService.getImage(name);
        return ResponseEntity.ok().contentType(MediaType.IMAGE_JPEG).body(image);
    }
}
