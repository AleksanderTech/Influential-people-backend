package com.alek.influentialpeople.article.controller;

import com.alek.influentialpeople.article.entity.Article;
import com.alek.influentialpeople.article.model.ArticleHeader;
import com.alek.influentialpeople.article.model.ArticleRequest;
import com.alek.influentialpeople.article.model.ArticleResponse;
import com.alek.influentialpeople.article.service.ArticleCrudService;
import com.alek.influentialpeople.common.TwoWayConverter;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import static com.alek.influentialpeople.common.ConvertersFactory.ConverterType.*;
import static com.alek.influentialpeople.common.ConvertersFactory.getConverter;

@RestController
@RequestMapping("/article")
public class ArticleCrudController {

    private final ArticleCrudService articleService;

    private TwoWayConverter<ArticleRequest, Article> articleRequestConverter = getConverter(ARTICLE_REQUEST_TO_ARTICLE);
    private TwoWayConverter<Article, ArticleHeader> articleHeaderConverter = getConverter(ARTICLE_TO_ARTICLE_HEADER);
    private TwoWayConverter<Article, ArticleResponse> articleResponseConverter = getConverter(ARTICLE_TO_ARTICLE_RESPONSE);

    public ArticleCrudController(ArticleCrudService articleService) {
        this.articleService = articleService;
    }

    @RequestMapping(path = "/hero/{name}", method = RequestMethod.GET)
    public ResponseEntity<Page<ArticleHeader>> findByHero(@PathVariable(name = "name") String name, Pageable pageable) {
        return ResponseEntity.status(HttpStatus.OK).body(articleService.findByHero(name, pageable).map(article -> articleHeaderConverter.convert(article)));
    }

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<Page<ArticleHeader>> findAll(Pageable pageable) {
        return ResponseEntity.status(HttpStatus.OK).body(articleService.findAll(pageable).map(article -> articleHeaderConverter.convert(article)));
    }

    @RequestMapping(path = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<ArticleResponse> findOne(@PathVariable(name = "id") Long id) {
        return ResponseEntity.status(HttpStatus.OK).body(articleResponseConverter.convert(articleService.findOne(id)));
    }

    @PreAuthorize("hasRole('ADMIN')")
    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<ArticleHeader> create(@RequestBody ArticleRequest articleRequest) {
        Article article = articleService.create(articleRequestConverter.convert(articleRequest));
        return ResponseEntity.status(HttpStatus.CREATED).body(articleHeaderConverter.convert(article));
    }

    @PreAuthorize("hasRole('ADMIN')")
    @RequestMapping(method = RequestMethod.PATCH)
    public ResponseEntity<ArticleResponse> update(@PathVariable(name = "id") Long id, @RequestBody ArticleRequest articleRequest) {
        Article article = articleService.update(id, articleRequestConverter.convert(articleRequest));
        return new ResponseEntity<>(articleResponseConverter.convert(article), HttpStatus.OK);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @RequestMapping(path = "/{id}", method = RequestMethod.DELETE)
    public ResponseEntity delete(@PathVariable(name = "id") Long id) {
        articleService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
