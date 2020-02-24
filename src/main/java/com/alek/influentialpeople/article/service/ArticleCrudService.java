package com.alek.influentialpeople.article.service;

import com.alek.influentialpeople.article.entity.Article;
import com.alek.influentialpeople.article.persistence.ArticleCrudRepository;
import com.alek.influentialpeople.common.abstraction.CrudService;
import com.alek.influentialpeople.exception.ExceptionMessages;
import com.alek.influentialpeople.exception.exceptions.EntityExistsException;
import com.alek.influentialpeople.user.entity.User;
import com.alek.influentialpeople.user.service.UserDataHolder;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.Optional;

@Service
public class ArticleCrudService implements CrudService<Article, Long> {

    private final ArticleCrudRepository articleRepository;
    private final UserDataHolder<User> userHolder;

    public ArticleCrudService(ArticleCrudRepository articleRepository, UserDataHolder<User> userHolder) {
        this.articleRepository = articleRepository;
        this.userHolder = userHolder;
    }

    public Page<Article> findByHero(String name, Pageable pageable) {
        return articleRepository.findByHeroName(name, pageable);
    }

    @Override
    public Article findOne(Long id) {
        Optional<Article> optionalArticle = articleRepository.findById(id);
        if (optionalArticle.isPresent()) {
            return optionalArticle.get();
        }
        throw new EntityNotFoundException(ExceptionMessages.NOT_FOUND_ARTICLE_MESSAGE);
    }

    @Override
    public Page<Article> findAll(Pageable pageable) {
        return articleRepository.findAll(pageable);
    }

    @Override
    public Article create(Article article) {
        if (articleRepository.existsById(article.getId())) {
            throw new EntityExistsException(ExceptionMessages.ARTICLE_EXISTS_MESSAGE);
        }
        article.setUser(new User(userHolder.getUsername()));
        return articleRepository.save(article);
    }

    @Override
    public Article update(Long id, Article changes) {
        Optional<Article> optionalArticle = articleRepository.findById(id);
        if (optionalArticle.isPresent()) {
            Article article = optionalArticle.get();
            article = setChanges(article, changes);
            return articleRepository.save(article);
        }
        throw new EntityNotFoundException(ExceptionMessages.NOT_FOUND_ARTICLE_MESSAGE);
    }

    private Article setChanges(Article article, Article changes) {
        article.setTitle(changes.getTitle());
        article.setText(changes.getText());
        article.setHero(changes.getHero());
        return article;
    }

    @Override
    public void delete(Long id) {
        if (articleRepository.existsById(id)) {
            articleRepository.deleteById(id);
            return;
        }
        throw new EntityNotFoundException(ExceptionMessages.NOT_FOUND_ARTICLE_MESSAGE);
    }
}
