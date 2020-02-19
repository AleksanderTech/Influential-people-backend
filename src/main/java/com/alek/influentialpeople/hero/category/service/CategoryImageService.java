package com.alek.influentialpeople.hero.category.service;

import com.alek.influentialpeople.common.abstraction.ImageService;
import com.alek.influentialpeople.common.ImageManager;
import com.alek.influentialpeople.exception.ExceptionMessages;
import com.alek.influentialpeople.hero.category.persistence.CategoryImageRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.EntityNotFoundException;
import java.io.File;

@Service("categoryImageService")
public class CategoryImageService implements ImageService<String> {

    private final CategoryImageRepository categoryRepository;
    private final ImageManager imageManager;

    public CategoryImageService(CategoryImageRepository categoryRepository, ImageManager imageManager) {
        this.categoryRepository = categoryRepository;
        this.imageManager = imageManager;
    }

    @Override
    public byte[] getImage(String fullName) {
        String path = categoryRepository.findImagePath(fullName);
        if (path == null || !(new File(path).exists())) {
            throw new EntityNotFoundException(ExceptionMessages.NOT_FOUND_IMAGE_MESSAGE);
        }
        File directory = new File(path);
        byte[] image = imageManager.getImage(path);
        return image;
    }

    @Override
    public String storeImage(String name, MultipartFile image) {
        boolean exists = categoryRepository.existsById(name);
        if (!exists) {
            throw new EntityNotFoundException(ExceptionMessages.NOT_FOUND_CATEGORY_MESSAGE);
        }
        String path = categoryRepository.findImagePath(name);
        if (path == null || !new File(path).exists()) {
            path = imageManager.createPath(ImageManager.StorageOf.CATEGORY, name);
            categoryRepository.updateImagePath(imageManager.appendImageName(name, path), name);
            imageManager.storeImage(ImageManager.StorageOf.CATEGORY, name, image);
        } else {
            imageManager.storeImage(path, image);
        }
        return imageManager.createUrl(ImageManager.StorageOf.CATEGORY, name);
    }
}
