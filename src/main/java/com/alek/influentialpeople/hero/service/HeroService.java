package com.alek.influentialpeople.hero.service;

import com.alek.influentialpeople.hero.entity.Hero;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.multipart.MultipartFile;

public interface HeroService {

    Page<Hero> findHeroes(Pageable pageable);

    Hero createHero(Hero person);

    Hero findHero(String fullName);

    byte[] getHeroImage(String fullName);

    String  storeHeroImage(String fullName, MultipartFile image);
}
