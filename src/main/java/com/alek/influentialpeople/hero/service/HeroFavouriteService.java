package com.alek.influentialpeople.hero.service;

import com.alek.influentialpeople.common.abstraction.FavouriteService;
import com.alek.influentialpeople.exception.ExceptionMessages;
import com.alek.influentialpeople.exception.exceptions.EntityExistsException;
import com.alek.influentialpeople.hero.entity.Hero;
import com.alek.influentialpeople.hero.persistence.HeroFavouriteRepository;
import com.alek.influentialpeople.user.entity.User;
import com.alek.influentialpeople.user.service.UserDataHolder;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;

@Service
public class HeroFavouriteService implements FavouriteService<Hero, String> {

    private final HeroFavouriteRepository heroRepository;
    private final UserDataHolder<User> userHolder;

    public HeroFavouriteService(HeroFavouriteRepository heroRepository, UserDataHolder<User> userHolder) {
        this.heroRepository = heroRepository;
        this.userHolder = userHolder;
    }

    @Override
    public Hero find(String name) {
        Hero hero = heroRepository.find(name, userHolder.getUsername());
        if (hero == null) {
            throw new EntityNotFoundException(ExceptionMessages.NOT_FOUND_HERO_FAVOURITE_MESSAGE);
        }
        return heroRepository.find(name, userHolder.getUsername());
    }

    @Override
    public Page<Hero> findAll(Pageable pageable) {
        return heroRepository.find(pageable, userHolder.getUsername());
    }

    @Override
    public void add(String name) {
        if (heroRepository.find(name, userHolder.getUsername())!=null) {
            throw new EntityExistsException(ExceptionMessages.ENTITY_ALREADY_EXIST_MESSAGE);
        }
        heroRepository.add(name, userHolder.getUsername());
    }

    @Override
    public void delete(String name) {
        if (heroRepository.find(name, userHolder.getUsername())!=null) {
            heroRepository.delete(userHolder.getUsername(), name);
             return;
         }
         throw new EntityNotFoundException(ExceptionMessages.NOT_FOUND_HERO_MESSAGE);
    }
}
