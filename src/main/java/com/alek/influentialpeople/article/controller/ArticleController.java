package com.alek.influentialpeople.article.controller;

import com.alek.influentialpeople.article.entity.Article;
import com.alek.influentialpeople.article.model.ArticleHeader;
import com.alek.influentialpeople.article.model.ArticleRequest;
import com.alek.influentialpeople.article.service.ArticleService;
import com.alek.influentialpeople.common.TwoWayConverter;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static com.alek.influentialpeople.common.ConvertersFactory.ConverterType.ARTICLE_REQUEST_TO_ARTICLE;
import static com.alek.influentialpeople.common.ConvertersFactory.ConverterType.ARTICLE_TO_ARTICLE_HEADER;
import static com.alek.influentialpeople.common.ConvertersFactory.getConverter;

@RestController
@RequestMapping("/article")
public class ArticleController {

    private ArticleService articleService;

    private TwoWayConverter<ArticleRequest, Article> articleRequestConverter = getConverter(ARTICLE_REQUEST_TO_ARTICLE);
    private TwoWayConverter<Article, ArticleHeader> articleHeaderConverter = getConverter(ARTICLE_TO_ARTICLE_HEADER);

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
    public ResponseEntity<ArticleHeader> findArticle(@PathVariable(name = "id") Long id) {

        return ResponseEntity.status(HttpStatus.OK).body(articleHeaderConverter.convert(articleService.findArticle(id)));
    }

    @RequestMapping( method = RequestMethod.POST)
    public ResponseEntity<ArticleHeader> createHeroArticle(@RequestBody ArticleRequest articleRequest) {

        Article article = articleService.createHeroArticle(articleRequestConverter.convert(articleRequest));
        return ResponseEntity.status(HttpStatus.CREATED).body(articleHeaderConverter.convert(article));
    }
}
