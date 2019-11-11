package com.alek.influentialpeople.user.service;

import com.alek.influentialpeople.article.domain.Article;
import com.alek.influentialpeople.article.repository.ArticleRepository;
import com.alek.influentialpeople.hero.category.persistence.CategoryRepository;
import com.alek.influentialpeople.hero.category.persistence.HeroCategoryRepository;
import com.alek.influentialpeople.hero.entity.Hero;
import com.alek.influentialpeople.hero.persistence.HeroRepository;
import com.alek.influentialpeople.user.entity.User;
import com.alek.influentialpeople.user.persistence.UserRepository;
import com.alek.influentialpeople.user.role.entity.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.HashSet;

@Service
public class UserInitializer {

    @Autowired
    public UserInitializer(HeroCategoryRepository heroCategoryRepository, CategoryRepository categoryRepository, ArticleRepository articleRepository, HeroRepository heroRepository, UserRepository userRepository, PasswordEncoder passwordEncoder) {

        Hero edison = Hero.builder().fullName("Thomas Alva Edison").avatarImagePath("storage/images/heroes/Thomas_Alva_Edison/avatar/Thomas_Alva_Edison.jpg").build();
        Hero stalin = Hero.builder().fullName("Joseph Stalin").avatarImagePath("storage/images/heroes/Joseph_Stalin/avatar/Joseph_Stalin.jpg").build();

        Article edisonArticle = Article.builder().title("Edison the rich man").content("Edison whas not only genius in science field but ... ").hero(edison).build();
        Article edisonArticle2 = Article.builder().title("Edison and his filosophy").content("Edison whas not only genius in science field but ... ").hero(edison).build();
        Article stalinArticle = Article.builder().title("Stalin the rich man").content("Stalin whas not only genius in science field but ... ").hero(stalin).build();
        heroRepository.saveAll(Arrays.asList(edison, stalin));
        articleRepository.saveAll(Arrays.asList(edisonArticle, edisonArticle2, stalinArticle));
        User admin = User.builder().username("admin")
                .password(passwordEncoder.encode("admin"))
                .email("email@email.com")
                .enabled(true)
                .roles(new HashSet(Arrays.asList(new Role(Role.Roles.ROLE_ADMIN), new Role(Role.Roles.ROLE_USER))))
                .build();

        userRepository.save(admin);
    }
}