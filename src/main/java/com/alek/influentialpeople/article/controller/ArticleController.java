package com.alek.influentialpeople.article.controller;

import com.alek.influentialpeople.article.entity.Article;
import com.alek.influentialpeople.article.model.ArticleHeader;
import com.alek.influentialpeople.article.model.ArticleRequest;
import com.alek.influentialpeople.article.model.ArticleResponse;
import com.alek.influentialpeople.article.service.ArticleService;
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
public class ArticleController {

    private ArticleService articleService;

    private TwoWayConverter<ArticleRequest, Article> articleRequestConverter = getConverter(ARTICLE_REQUEST_TO_ARTICLE);
    private TwoWayConverter<Article, ArticleHeader> articleHeaderConverter = getConverter(ARTICLE_TO_ARTICLE_HEADER);
    private TwoWayConverter<Article, ArticleResponse> articleResponseConverter = getConverter(ARTICLE_TO_ARTICLE_RESPONSE);

    public ArticleController(ArticleService articleService) {
        this.articleService = articleService;
    }

    @RequestMapping(path = "/hero/{name}", method = RequestMethod.GET)
    public ResponseEntity<Page<ArticleHeader>> findHeroArticles(@PathVariable(name = "name") String name, Pageable pageable) {

        return ResponseEntity.status(HttpStatus.OK).body(articleService.findHeroArticles(name, pageable).map(article -> articleHeaderConverter.convert(article)));
    }

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<Page<ArticleHeader>> findArticles(Pageable pageable) {

        return ResponseEntity.status(HttpStatus.OK).body(articleService.findArticles(pageable).map(article -> articleHeaderConverter.convert(article)));
    }

    @RequestMapping(path = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<ArticleResponse> findArticle(@PathVariable(name = "id") Long id) {

        return ResponseEntity.status(HttpStatus.OK).body(articleResponseConverter.convert(articleService.findArticle(id)));
    }

    @PreAuthorize("hasRole('ADMIN')")
    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<ArticleHeader> createHeroArticle(@RequestBody ArticleRequest articleRequest) {
        Article article = articleService.createHeroArticle(articleRequestConverter.convert(articleRequest));
        return ResponseEntity.status(HttpStatus.CREATED).body(articleHeaderConverter.convert(article));
    }


    @RequestMapping(path = "/{id}/favourite", method = RequestMethod.POST)
    public ResponseEntity addToFavourites(@PathVariable(name = "id") long articleId) {
        articleService.addToFavourites(articleId);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @RequestMapping(path = "/{id}/favourite", method = RequestMethod.DELETE)
    public ResponseEntity deleteFromFavourites(@PathVariable(name = "id") long articleId) {
        articleService.deleteFromFavourites(articleId);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @RequestMapping(path = "/favourite", method = RequestMethod.GET)
    public ResponseEntity<Page<ArticleHeader>> findFavourites(Pageable pageable) {
        return ResponseEntity.status(HttpStatus.OK).body(articleService.findFavourites(pageable).map(article -> articleHeaderConverter.convert(article)));
    }

    @RequestMapping(path = "/{id}/favourite", method = RequestMethod.GET)
    public ResponseEntity<ArticleHeader> findFavourite(@PathVariable(name = "id") long articleId) {
        return ResponseEntity.status(HttpStatus.OK).body(articleHeaderConverter.convert(articleService.findFavourite(articleId)));
    }
}
