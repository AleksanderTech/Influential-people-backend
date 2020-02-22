package com.alek.influentialpeople.user.service;

import com.alek.influentialpeople.common.ImageManager;
import com.alek.influentialpeople.common.abstraction.ImageService;
import com.alek.influentialpeople.exception.ExceptionMessages;
import com.alek.influentialpeople.user.entity.User;
import com.alek.influentialpeople.user.persistence.UserImageRepository;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.EntityNotFoundException;
import java.io.File;

@Service("UserImageService")
public class UserImageService implements ImageService<String> {

    private final UserImageRepository userRepository;
    private final UserDataHolder<User> userHolder;
    private final ImageManager imageManager;

    private static final int FIXED_GENERATED_PASSWORD_LENGTH = 10;

    public UserImageService(UserImageRepository userRepository, CurrentUserHolder userHolder, ImageManager imageManager) {
        this.userRepository = userRepository;
        this.userHolder = userHolder;
        this.imageManager = imageManager;
    }

    @Override
    public byte[] getImage(String username) {
        String path = userRepository.findAvatarPath(username);
        if (path == null || !(new File(path).exists())) {
            throw new EntityNotFoundException(ExceptionMessages.NOT_FOUND_IMAGE_MESSAGE);
        }
        File directory = new File(path);
        byte[] image = imageManager.getImage(path);
        return image;
    }

    @Override
    public String storeImage(String name, MultipartFile image) {
        if (!name.equals(userHolder.getUsername())) {
            throw new AccessDeniedException(ExceptionMessages.ACCESS_DENIED_MESSAGE);
        }
        boolean exists = userRepository.existsById(name);
        if (!exists) {
            throw new EntityNotFoundException(ExceptionMessages.NOT_FOUND_HERO_MESSAGE);
        }
        String path = userRepository.findAvatarPath(name);
        if (path == null || !new File(path).exists()) {
            path = imageManager.createPath(ImageManager.StorageOf.USER, name);
            userRepository.updateImagePath(imageManager.appendImageName(name, path), name);
            imageManager.storeImage(ImageManager.StorageOf.USER, name, image);
        } else {
            imageManager.storeImage(path, image);
        }
        return imageManager.createUrl(ImageManager.StorageOf.USER, name);
    }
}
