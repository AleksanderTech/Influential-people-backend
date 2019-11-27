package com.alek.influentialpeople.user.service;

import com.alek.influentialpeople.article.comment.entity.ArticleComment;
import com.alek.influentialpeople.article.comment.persistence.ArticleCommentRepository;
import com.alek.influentialpeople.article.entity.Article;
import com.alek.influentialpeople.article.repository.ArticleRepository;
import com.alek.influentialpeople.hero.category.entity.Category;
import com.alek.influentialpeople.hero.category.persistence.CategoryRepository;
import com.alek.influentialpeople.hero.entity.Hero;
import com.alek.influentialpeople.hero.persistence.HeroRepository;
import com.alek.influentialpeople.hero.score.persistence.HeroScoreRepository;
import com.alek.influentialpeople.quote.entity.Quote;
import com.alek.influentialpeople.quote.persistence.QuoteRepository;
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
    public UserInitializer(HeroScoreRepository heroScoreRepository, ArticleCommentRepository articleCommentRepository, QuoteRepository quoteRepository, CategoryRepository categoryRepository, ArticleRepository articleRepository, HeroRepository heroRepository, UserRepository userRepository, PasswordEncoder passwordEncoder) {

        User admin = User.builder().username("admin")
                .password(passwordEncoder.encode("admin"))
                .email("email@email.com")
                .enabled(true)
                .roles(new HashSet(Arrays.asList(new Role(Role.Roles.ROLE_ADMIN), new Role(Role.Roles.ROLE_USER))))
                .build();
        User user = User.builder().username("user")
                .password(passwordEncoder.encode("user"))
                .email("email@email.com")
                .enabled(true)
                .roles(new HashSet(Arrays.asList(new Role(Role.Roles.ROLE_USER))))
                .build();
        Category scientist = new Category("scientist");
        Category philosopher = new Category("philosopher");
        Category tyrant = new Category("tyrant");
        Category politician = new Category("politician");
        categoryRepository.saveAll(Arrays.asList(scientist, philosopher, tyrant, politician));

        Hero edison = Hero.builder().heroCategories(new HashSet<>(Arrays.asList(scientist, philosopher))).name("Thomas Alva Edison").avatarImagePath("storage/images/heroes/Thomas_Alva_Edison/avatar/Thomas_Alva_Edison.jpg").build();
        Hero stalin = Hero.builder().heroCategories(new HashSet<>(Arrays.asList(tyrant, politician))).name("Joseph Stalin").avatarImagePath("storage/images/heroes/Joseph_Stalin/avatar/Joseph_Stalin.jpg").build();
        Hero galileo = Hero.builder().heroCategories(new HashSet<>(Arrays.asList(scientist, philosopher))).name("Galileo Galilei").avatarImagePath("storage/images/heroes/Galileo_Galilei/avatar/Galileo Galilei.jpg").build();

        Article edisonArticle = Article.builder().title("Edison the wise man").text("Edison whas not only genius in science field but ... ").hero(edison).build();

        Article edisonArticle2 = Article.builder().title("Edison and his filosophy").text("Edison whas not only genius in science field but ... ").hero(edison).build();
        Article stalinArticle = Article.builder().title("Stalin the tyrant").text("Stalin whas not only genius in science field but ... ").hero(stalin).build();
        Quote galileo1 = Quote.builder().content("I have never met a man so ignorant that I couldn't learn something from him.").hero(galileo).build();
        Quote galileo2 = Quote.builder().content("I do not feel obliged to believe that the same God who has endowed us with sense, reason, and intellect has intended us to forgo their use.").hero(galileo).build();
        heroRepository.saveAll(Arrays.asList(edison, stalin, galileo));

        quoteRepository.saveAll(Arrays.asList(galileo1, galileo2));
        articleRepository.saveAll(Arrays.asList(edisonArticle, edisonArticle2, stalinArticle));

        userRepository.save(admin);
        userRepository.save(user);
        ArticleComment articleComment1 = ArticleComment.builder().content("Yea that's true").article(edisonArticle).user(admin).build();
        ArticleComment articleComment2 = ArticleComment.builder().content("No that's false").article(edisonArticle).user(admin).build();
        ArticleComment articleComment3 = ArticleComment.builder().content("this is comment").article(edisonArticle).user(admin).build();
        ArticleComment articleComment4 = ArticleComment.builder().content("this is also a comment").article(stalinArticle).user(admin).build();
        articleCommentRepository.save(articleComment1);
        articleCommentRepository.save(articleComment2);
        articleCommentRepository.save(articleComment3);
        articleCommentRepository.save(articleComment4);
        heroScoreRepository.vote(admin.getUsername(), stalin.getName(), 6);
        heroScoreRepository.vote(user.getUsername(), stalin.getName(), 5);
        heroScoreRepository.vote(admin.getUsername(), edison.getName(), 7);
        heroScoreRepository.vote(user.getUsername(), edison.getName(), 8);
        heroScoreRepository.vote(admin.getUsername(), galileo.getName(), 7);
        heroScoreRepository.vote(user.getUsername(), galileo.getName(), 7);
        heroRepository.updateScore( stalin.getName());
        heroRepository.updateScore( edison.getName());
        heroRepository.updateScore( galileo.getName());
        quoteRepository.addToFavourites(1,admin.getUsername());
        quoteRepository.addToFavourites(2,admin.getUsername());
        quoteRepository.addToFavourites(1,user.getUsername());
    }
}