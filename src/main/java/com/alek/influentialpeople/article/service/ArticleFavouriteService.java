package com.alek.influentialpeople.article.service;

import com.alek.influentialpeople.article.entity.Article;
import com.alek.influentialpeople.article.persistence.ArticleFavouriteRepository;
import com.alek.influentialpeople.common.abstraction.FavouriteService;
import com.alek.influentialpeople.exception.ExceptionMessages;
import com.alek.influentialpeople.user.entity.User;
import com.alek.influentialpeople.user.service.UserDataHolder;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;

@Service
public class ArticleFavouriteService implements FavouriteService<Article, Long> {

    private final ArticleFavouriteRepository articleRepository;
    private final UserDataHolder<User> userHolder;

    public ArticleFavouriteService(ArticleFavouriteRepository articleRepository, UserDataHolder<User> userHolder) {
        this.articleRepository = articleRepository;
        this.userHolder = userHolder;
    }

    @Override
    public Article find(Long id) {
        Article article = articleRepository.find(id, userHolder.getUsername());
        if (article == null) {
            throw new EntityNotFoundException(ExceptionMessages.NOT_FOUND_ARTICLE_FAVOURITE_MESSAGE);
        }
        return articleRepository.find(id, userHolder.getUsername());
    }

    @Override
    public Page<Article> findAll(Pageable pageable) {
        return articleRepository.findAll(pageable, userHolder.getUsername());
    }

    @Override
    public void add(Long id) {
        articleRepository.add(id, userHolder.getUsername());
    }

    @Override
    public void delete(Long id) {
        articleRepository.delete(userHolder.getUsername(), id);
    }
}
