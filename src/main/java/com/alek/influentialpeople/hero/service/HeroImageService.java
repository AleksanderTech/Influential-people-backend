package com.alek.influentialpeople.hero.service;

import com.alek.influentialpeople.common.ImageType;
import com.alek.influentialpeople.common.abstraction.ImageService;
import com.alek.influentialpeople.common.ImageManager;
import com.alek.influentialpeople.exception.ExceptionMessages;
import com.alek.influentialpeople.hero.persistence.HeroImageRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.EntityNotFoundException;
import java.io.File;

@Service("heroImageService")
public class HeroImageService implements ImageService<String> {

    private final ImageManager imageManager;
    private final HeroImageRepository heroRepository;

    public HeroImageService(ImageManager imageManager, HeroImageRepository heroRepository) {
        this.imageManager = imageManager;
        this.heroRepository = heroRepository;
    }

    @Override
    public byte[] getImage(String heroName) {
        String path = heroRepository.findAvatarPath(heroName);
        if (path == null || !(new File(path).exists())) {
            throw new EntityNotFoundException(ExceptionMessages.NOT_FOUND_IMAGE_MESSAGE);
        }
        File directory = new File(path);
        byte[] image = imageManager.getImage(path);
        return image;
    }

    @Override
    public String storeImage(String heroName, MultipartFile image) {
        boolean exists = heroRepository.existsById(heroName);
        if (!exists) {
            throw new EntityNotFoundException(ExceptionMessages.NOT_FOUND_HERO_MESSAGE);
        }
        String path = heroRepository.findAvatarPath(heroName);
        if (path == null || !new File(path).exists()) {
            path = imageManager.createPath(ImageType.HERO, heroName);
            heroRepository.updateImagePath(imageManager.appendImageName(heroName, path), heroName);
            imageManager.storeImage(ImageType.HERO, heroName, image);
        } else {
            imageManager.storeImage(path, image);
        }
        return imageManager.createUrl(ImageType.HERO, heroName);
    }
}
