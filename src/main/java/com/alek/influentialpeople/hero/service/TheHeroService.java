package com.alek.influentialpeople.hero.service;

import com.alek.influentialpeople.common.ImageService;
import com.alek.influentialpeople.exception.ExceptionMessages;
import com.alek.influentialpeople.exception.exceptions.EntityExistsException;
import com.alek.influentialpeople.hero.entity.Hero;
import com.alek.influentialpeople.hero.persistence.HeroRepository;
import com.alek.influentialpeople.user.entity.User;
import com.alek.influentialpeople.user.service.UserDataHolder;
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
    private final UserDataHolder<User> userHolder;

    public TheHeroService(final ImageService imageService, final HeroRepository heroRepository, final UserDataHolder userHolder) {
        this.imageService = imageService;
        this.heroRepository = heroRepository;
        this.userHolder = userHolder;
    }

    @Override
    public Hero findHero(String name) {
        Hero hero = heroRepository.findByName(name);
        if (hero == null) {
            throw new EntityNotFoundException(ExceptionMessages.NOT_FOUND_HERO_MESSAGE);
        }
        return heroRepository.findByName(name);
    }

    @Override
    public Page<Hero> findHeroes(Pageable pageable) {
        return heroRepository.findAllHeroes(pageable);
    }

    @Override
    public Hero createHero(Hero hero) {
        if (heroRepository.existsById(hero.getName())) {
            throw new EntityExistsException(ExceptionMessages.HERO_EXISTS_MESSAGE);
        }
        return heroRepository.save(hero);
    }

    @Override
    public void addToFavourites(String heroName) {
        heroRepository.addToFavourites(heroName, userHolder.getUsername());
    }

    @Override
    public Page<Hero> findFavourites(Pageable pageable) {
        return heroRepository.findFavourites(pageable, userHolder.getUsername());
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
    public String storeHeroImage(String heroName, MultipartFile image) {
        String url = null;
        boolean exists = heroRepository.existsById(heroName);
        if (!exists) {
            throw new EntityNotFoundException(ExceptionMessages.NOT_FOUND_HERO_MESSAGE);
        }
        String path = heroRepository.findAvatarPath(heroName);
        if (path == null || !new File(path).exists()) {
            path = imageService.createHeroAvatarPath(heroName);
            heroRepository.updateImagePath(imageService.appendImageName(heroName, path), heroName);
            imageService.storeImage(heroName, image);
        } else {
            imageService.storeImage(path, heroName, image);
        }
        return imageService.createHeroAvatarUrl(heroName);
    }
}
