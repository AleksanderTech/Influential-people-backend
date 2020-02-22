package com.alek.influentialpeople.user.controller;

import com.alek.influentialpeople.common.abstraction.ImageService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/user")
public class UserImageController {

    private final ImageService<String> userService;

    public UserImageController(@Qualifier("UserImageService") ImageService<String> userService) {
        this.userService = userService;
    }

    @RequestMapping(path = "/{username}/image", method = RequestMethod.PUT)
    public ResponseEntity uploadAvatarImage(@PathVariable(name = "username") String username, @RequestPart(value = "image", required = false) MultipartFile image) {
        userService.storeImage(username,image);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @RequestMapping(path = "/{username}/image", method = RequestMethod.GET, produces = MediaType.IMAGE_JPEG_VALUE)
    public ResponseEntity<byte[]> getAvatarImage(@PathVariable(name = "username") String username) {
        byte[] image = userService.getImage(username);
        return ResponseEntity.ok().contentType(MediaType.IMAGE_JPEG).body(image);
    }
}
