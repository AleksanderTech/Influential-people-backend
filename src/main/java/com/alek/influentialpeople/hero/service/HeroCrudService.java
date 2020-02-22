package com.alek.influentialpeople.hero.service;

import com.alek.influentialpeople.common.abstraction.CrudService;
import com.alek.influentialpeople.exception.ExceptionMessages;
import com.alek.influentialpeople.exception.exceptions.EntityExistsException;
import com.alek.influentialpeople.hero.entity.Hero;
import com.alek.influentialpeople.hero.persistence.HeroCrudRepository;
import com.alek.influentialpeople.user.entity.User;
import com.alek.influentialpeople.user.service.UserDataHolder;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.Optional;

@Service
public class HeroCrudService implements CrudService<Hero, String> {

    private final HeroCrudRepository heroRepository;
    private final UserDataHolder<User> userHolder;

    public HeroCrudService(HeroCrudRepository heroRepository, UserDataHolder<User> userHolder) {
        this.heroRepository = heroRepository;
        this.userHolder = userHolder;
    }

    @Override
    public Hero findOne(String name) {
        Hero hero = heroRepository.findByName(name);
        if (hero == null) {
            throw new EntityNotFoundException(ExceptionMessages.NOT_FOUND_HERO_MESSAGE);
        }
        return hero;
    }

    @Override
    public Page<Hero> findAll(Pageable pageable) {
        return heroRepository.findAllHeroes(pageable);
    }

    @Override
    public Hero create(Hero hero) {
        if (heroRepository.existsById(hero.getName())) {
            throw new EntityExistsException(ExceptionMessages.HERO_EXISTS_MESSAGE);
        }
        return heroRepository.save(hero);
    }

    @Override
    public Hero update(String name, Hero changes) {
        Optional<Hero> optionalHero = heroRepository.findById(name);
        if (optionalHero.isPresent()) {
            Hero hero = optionalHero.get();
            hero = setChanges(hero, changes);
            return heroRepository.save(hero);
        }
        throw new EntityNotFoundException(ExceptionMessages.NOT_FOUND_HERO_MESSAGE);
    }

    private Hero setChanges(Hero hero, Hero changes) {
        hero.setHeroCategories(changes.getHeroCategories());
        return hero;
    }

    @Override
    public void delete(String name) {
        if (heroRepository.existsById(name)) {
            heroRepository.deleteById(name);
            return;
        }
        throw new EntityNotFoundException(ExceptionMessages.NOT_FOUND_HERO_MESSAGE);
    }
}
