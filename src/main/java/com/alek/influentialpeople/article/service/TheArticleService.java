package com.alek.influentialpeople.article.service;

import com.alek.influentialpeople.article.entity.Article;
import com.alek.influentialpeople.article.repository.ArticleRepository;
import com.alek.influentialpeople.user.entity.User;
import com.alek.influentialpeople.user.service.UserDataHolder;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

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
    public Page<Article> findFavourites(Pageable pageable) {
        return articleRepository.findFavourites(pageable, userHolder.getUsername());
    }
}
