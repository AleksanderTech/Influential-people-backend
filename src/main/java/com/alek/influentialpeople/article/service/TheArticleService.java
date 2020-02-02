package com.alek.influentialpeople.article.service;

import com.alek.influentialpeople.article.entity.Article;
import com.alek.influentialpeople.article.repository.ArticleRepository;
import com.alek.influentialpeople.exception.ExceptionMessages;
import com.alek.influentialpeople.user.entity.User;
import com.alek.influentialpeople.user.service.UserDataHolder;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;

@Service
public class TheArticleService implements ArticleService {

    private final ArticleRepository articleRepository;
    private final UserDataHolder<User> userHolder;

    public TheArticleService(final ArticleRepository articleRepository, final UserDataHolder<User> userHolder) {
        this.articleRepository = articleRepository;
        this.userHolder = userHolder;
    }

    @Override
    public Page<Article> findHeroArticles(String fullName, Pageable pageable) {
        return articleRepository.findByHeroName(fullName, pageable);
    }

    @Override
    public Article findArticle(Long id) {
        return articleRepository.findById(id).get();
    }

    @Override
    public Article createHeroArticle(Article article) {
        return articleRepository.save(article);
    }

    @Override
    public Page<Article> findArticles(Pageable pageable) {
        return articleRepository.findAllArticles(pageable);
    }

    @Override
    public void addToFavourites(long quoteId) {
        articleRepository.addToFavourites(quoteId, userHolder.getUsername());
    }


    @Override
    public void deleteFromFavourites(long articleId) {
        articleRepository.deleteFromFavourites(userHolder.getUsername(), articleId);
    }

    @Override
    public Article findFavourite(long articleId) {
        Article article = articleRepository.findFavourite(articleId, userHolder.getUsername());
        if (article == null) {
            throw new EntityNotFoundException(ExceptionMessages.NOT_FOUND_ARTICLE_FAVOURITE_MESSAGE);
        }
        return articleRepository.findFavourite(articleId, userHolder.getUsername());
    }

    @Override
    public Page<Article> findFavourites(Pageable pageable) {
        return articleRepository.findFavourites(pageable, userHolder.getUsername());
    }
}
