package com.alek.influentialpeople.init;

import com.alek.influentialpeople.article.comment.entity.ArticleComment;
import com.alek.influentialpeople.article.comment.persistence.ArticleCommentRepository;
import com.alek.influentialpeople.article.entity.Article;
import com.alek.influentialpeople.article.repository.ArticleRepository;
import com.alek.influentialpeople.hero.category.entity.Category;
import com.alek.influentialpeople.hero.category.persistence.CategoryCrudRepository;
import com.alek.influentialpeople.hero.entity.Hero;
import com.alek.influentialpeople.hero.persistence.HeroRepository;
import com.alek.influentialpeople.hero.rate.persistence.HeroRateRepository;
import com.alek.influentialpeople.quote.entity.Quote;
import com.alek.influentialpeople.quote.persistence.QuoteRepository;
import com.alek.influentialpeople.user.entity.User;
import com.alek.influentialpeople.user.persistence.UserRepository;
import com.alek.influentialpeople.user.role.entity.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.HashSet;

@Service
@Profile(value = {"dev"})
public class DevInitializer {

    @Autowired
    public DevInitializer(HeroRateRepository heroRateRepository, ArticleCommentRepository articleCommentRepository, QuoteRepository quoteRepository, CategoryCrudRepository categoryCrudRepository, ArticleRepository articleRepository, HeroRepository heroRepository, UserRepository userRepository, PasswordEncoder passwordEncoder) {

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
        User user2 = User.builder().username("user2")
                .password(passwordEncoder.encode("user2"))
                .email("email@email.com")
                .enabled(true)
                .roles(new HashSet(Arrays.asList(new Role(Role.Roles.ROLE_USER))))
                .build();
        Category scientists = new Category("scientists", "Science is organized knowledge. Wisdom is organized life.");
        Category inventors = new Category("inventors", "Either write something worth reading or do something worth writing.");
        Category leaders = new Category("leaders", "Do not follow where the path may lead. Go instead where there is no path and leave a trail.");
        Category religiousFounders = new Category("religious founders", "Just as a candle cannot burn without fire, men cannot live without a spiritual life.");
        Category explorers = new Category("explorers", "Life is an experiment in which you may fail or succeed. Explore more, expect least.");
        Category activists = new Category("activists", "We must always take sides. Neutrality helps the oppressor, never the victim. Silence encourages the tormentor, never the tormented.");
        Category artistsWriters = new Category("artists & writers", "Every child is an artist. The problem is how to remain an artist once he grows up.");
        Category philosophers = new Category("philosophers", "If you only read the books that everyone else is reading, you can only think what everyone else is thinking.author-Haruki Murakami");

        categoryCrudRepository.saveAll(Arrays.asList(scientists, philosophers, leaders, religiousFounders, activists, artistsWriters, explorers));

        Hero edison = Hero.builder().heroCategories(new HashSet<>(Arrays.asList(scientists, philosophers))).name("Thomas Alva Edison").avatarImagePath("storage/images/heroes/Thomas_Alva_Edison/avatar/Thomas_Alva_Edison.jpg").build();
        Hero julius = Hero.builder().heroCategories(new HashSet<>(Arrays.asList(leaders))).name("Gaius Julius Caesar").build();
        Hero christ = Hero.builder().heroCategories(new HashSet<>(Arrays.asList(philosophers))).name("Jesus Christ").build();
        Hero aristotle = Hero.builder().heroCategories(new HashSet<>(Arrays.asList(philosophers, scientists))).name("Aristotle").build();

        Hero plato = Hero.builder().heroCategories(new HashSet<>(Arrays.asList(scientists, philosophers))).name("Plato").build();
        Hero socrates = Hero.builder().heroCategories(new HashSet<>(Arrays.asList(scientists, philosophers))).name("Socrates").build();
        Hero newton = Hero.builder().heroCategories(new HashSet<>(Arrays.asList(scientists, philosophers))).name("Isaac Newton").build();
        Hero stalin = Hero.builder().heroCategories(new HashSet<>(Arrays.asList(leaders))).name("Joseph Stalin").avatarImagePath("storage/images/heroes/Joseph_Stalin/avatar/Joseph_Stalin.jpg").build();
        Hero galileo = Hero.builder().heroCategories(new HashSet<>(Arrays.asList(scientists, philosophers))).name("Galileo Galilei").avatarImagePath("storage/images/heroes/Galileo_Galilei/avatar/Galileo Galilei.jpg").build();

        Article edisonArticle = Article.builder().title("Edison the wise man").text("Edison whas not only genius in science field but ... ").hero(edison).build();
        Article platoArticle = Article.builder().title("Plato the wise man").text("Plato was not only genius in science field but ... ").hero(edison).build();

        Article edisonArticle2 = Article.builder().title("Edison and his filosophy").text("Edison whas not only genius in science field but ... ").hero(edison).build();
        Article stalinArticle = Article.builder().title("Stalin the tyrant").text("Stalin whas not only genius in science field but ... ").hero(stalin).build();
        Quote galileo1 = Quote.builder().content("I have never met a man so ignorant that I couldn't learn something from him.").hero(galileo).build();
        Quote edison1 = Quote.builder().content("I have not failed. I've just found 10,000 ways that won't work.").hero(edison).build();
        Quote galileo2 = Quote.builder().content("I do not feel obliged to believe that the same God who has endowed us with sense, reason, and intellect has intended us to forgo their use.").hero(galileo).build();
        Quote galileo3 = Quote.builder().content("You cannot teach a man anything, you can only help him find it within himself.").hero(galileo).build();
        heroRepository.saveAll(Arrays.asList(edison, stalin, galileo, julius, christ, socrates, plato, aristotle, newton));

        quoteRepository.saveAll(Arrays.asList(galileo1, galileo2, galileo3, edison1));
        articleRepository.saveAll(Arrays.asList(edisonArticle, edisonArticle2, stalinArticle, platoArticle));

        userRepository.save(admin);
        userRepository.save(user);
        userRepository.save(user2);
        ArticleComment articleComment1 = ArticleComment.builder().content("Yea that's true").article(edisonArticle).user(admin).build();
        ArticleComment articleComment2 = ArticleComment.builder().content("No that's false").article(edisonArticle).user(admin).build();
        ArticleComment articleComment3 = ArticleComment.builder().content("this is comment").article(edisonArticle).user(admin).build();
        ArticleComment articleComment4 = ArticleComment.builder().content("this is also a comment").article(stalinArticle).user(admin).build();
        articleCommentRepository.save(articleComment1);
        articleCommentRepository.save(articleComment2);
        articleCommentRepository.save(articleComment3);
        articleCommentRepository.save(articleComment4);
        heroRateRepository.rate(user.getUsername(), stalin.getName(), 5);
        heroRateRepository.rate(admin.getUsername(), edison.getName(), 7);
        heroRateRepository.rate(user.getUsername(), edison.getName(), 8);
        heroRateRepository.rate(admin.getUsername(), galileo.getName(), 7);
        heroRateRepository.rate(user.getUsername(), galileo.getName(), 7);
        heroRateRepository.rate(admin.getUsername(), stalin.getName(), 6);

        heroRepository.updateScore(stalin.getName());
        heroRepository.updateScore(edison.getName());
        heroRepository.updateScore(galileo.getName());
        heroRepository.addToFavourites(aristotle.getName(),admin.getUsername());
        heroRepository.addToFavourites(galileo.getName(),admin.getUsername());
        heroRepository.addToFavourites(plato.getName(),admin.getUsername());
        articleRepository.addToFavourites(1,admin.getUsername());
        articleRepository.addToFavourites(2,admin.getUsername());
        articleRepository.addToFavourites(3,admin.getUsername());
        quoteRepository.addToFavourites(1, admin.getUsername());
        quoteRepository.addToFavourites(2, admin.getUsername());
        quoteRepository.addToFavourites(3, admin.getUsername());
        quoteRepository.addToFavourites(1, user.getUsername());
    }
}