package com.alek.influentialpeople.hero.service;

import com.alek.influentialpeople.common.ImageService;
import com.alek.influentialpeople.exception.ExceptionMessages;
import com.alek.influentialpeople.hero.entity.Hero;
import com.alek.influentialpeople.hero.persistence.HeroRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.EntityNotFoundException;
import java.io.File;

@Service
public class TheHeroService implements HeroService {

    private final ImageService imageService;
    private final HeroRepository heroRepository;

    public TheHeroService(final ImageService imageService, final HeroRepository heroRepository) {
        this.imageService = imageService;
        this.heroRepository = heroRepository;
    }

    @Override
    public Hero findHero(String fullName) {

        Hero hero = heroRepository.findByName(fullName);
        if (hero == null) {
            throw new EntityNotFoundException(ExceptionMessages.NOT_FOUND_HERO_MESSAGE);
        }
        return heroRepository.findByName(fullName);
    }

    @Override
    public Page<Hero> findHeroes(Pageable pageable) {

        return heroRepository.findAllHeroes(pageable);
    }

    @Override
    public Hero createHero(Hero hero) {

        return heroRepository.save(hero);
    }


    @Override
    public byte[] getHeroImage(String fullName) {

        String path = heroRepository.findAvatarPath(fullName);
        if (path == null || !(new File(path).exists())) {
            throw new EntityNotFoundException(ExceptionMessages.NOT_FOUND_IMAGE_MESSAGE);
        }
        File directory = new File(path);
        byte[] image = imageService.getImage(path);
        return image;
    }

    @Override
    public String storeHeroImage(String fullName, MultipartFile image) {

        String url = null;
        boolean exists = heroRepository.existsById(fullName);
        if (!exists) {
            throw new EntityNotFoundException(ExceptionMessages.NOT_FOUND_HERO_MESSAGE);
        }
        String path = heroRepository.findAvatarPath(fullName);
        if (path == null || !new File(path).exists()) {
            path = imageService.createHeroAvatarPath(fullName);
            heroRepository.updateImagePath(imageService.appendImageName(fullName, path), fullName);
            imageService.storeImage(fullName, image);
        } else {
            imageService.storeImage(path, fullName, image);
        }
        return imageService.createHeroAvatarUrl(fullName);
    }
}
