package com.alek.influentialpeople.article.controller;

import com.alek.influentialpeople.article.entity.Article;
import com.alek.influentialpeople.article.model.ArticleHeader;
import com.alek.influentialpeople.article.model.ArticleRequest;
import com.alek.influentialpeople.article.model.ArticleResponse;
import com.alek.influentialpeople.article.service.ArticleHeaderConverter;
import com.alek.influentialpeople.article.service.ArticleRequestConverter;
import com.alek.influentialpeople.article.service.ArticleResponseConverter;
import com.alek.influentialpeople.common.TwoWayConverter;
import com.alek.influentialpeople.common.abstraction.FavouriteService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/article")
public class ArticleFavouriteController {

    private TwoWayConverter<ArticleRequest, Article> articleRequestConverter = new ArticleRequestConverter();
    private TwoWayConverter<Article, ArticleHeader> articleHeaderConverter = new ArticleHeaderConverter();
    private TwoWayConverter<Article, ArticleResponse> articleResponseConverter = new ArticleResponseConverter();

    private final FavouriteService<Article, Long> articleService;

    public ArticleFavouriteController(FavouriteService<Article, Long> articleService) {
        this.articleService = articleService;
    }

    @RequestMapping(path = "/{id}/favourite", method = RequestMethod.POST)
    public ResponseEntity add(@PathVariable(name = "id") long articleId) {
        articleService.add(articleId);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @RequestMapping(path = "/{id}/favourite", method = RequestMethod.DELETE)
    public ResponseEntity delete(@PathVariable(name = "id") long articleId) {
        articleService.delete(articleId);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @RequestMapping(path = "/favourite", method = RequestMethod.GET)
    public ResponseEntity<Page<ArticleHeader>> findAll(Pageable pageable) {
        return ResponseEntity.status(HttpStatus.OK).body(articleService.findAll(pageable).map(article -> articleHeaderConverter.convert(article)));
    }

    @RequestMapping(path = "/{id}/favourite", method = RequestMethod.GET)
    public ResponseEntity<ArticleHeader> findOne(@PathVariable(name = "id") long articleId) {
        return ResponseEntity.status(HttpStatus.OK).body(articleHeaderConverter.convert(articleService.find(articleId)));
    }
}
