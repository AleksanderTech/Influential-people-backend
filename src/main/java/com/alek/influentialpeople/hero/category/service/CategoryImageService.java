package com.alek.influentialpeople.hero.category.service;

import com.alek.influentialpeople.common.ImageService;
import com.alek.influentialpeople.exception.ExceptionMessages;
import com.alek.influentialpeople.hero.category.persistence.CategoryImageRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.EntityNotFoundException;
import java.io.File;

@Service
public class CategoryImageService {

    private final CategoryImageRepository categoryRepository;
    private final ImageService imageService;

    public CategoryImageService(CategoryImageRepository categoryRepository, ImageService imageService) {
        this.categoryRepository = categoryRepository;
        this.imageService = imageService;
    }

    public byte[] getImage(String fullName) {
        String path = categoryRepository.findImagePath(fullName);
        if (path == null || !(new File(path).exists())) {
            throw new EntityNotFoundException(ExceptionMessages.NOT_FOUND_IMAGE_MESSAGE);
        }
        File directory = new File(path);
        byte[] image = imageService.getImage(path);
        return image;
    }

    public String storeImage(String name, MultipartFile image) {
        boolean exists = categoryRepository.existsById(name);
        if (!exists) {
            throw new EntityNotFoundException(ExceptionMessages.NOT_FOUND_CATEGORY_MESSAGE);
        }
        String path = categoryRepository.findImagePath(name);
        if (path == null || !new File(path).exists()) {
            path = imageService.createPath(ImageService.StorageOf.CATEGORY, name);
            categoryRepository.updateImagePath(imageService.appendImageName(name, path), name);
            imageService.storeImage(ImageService.StorageOf.CATEGORY, name, image);
        } else {
            imageService.storeImage(path, image);
        }
        return imageService.createUrl(ImageService.StorageOf.CATEGORY, name);
    }
}
