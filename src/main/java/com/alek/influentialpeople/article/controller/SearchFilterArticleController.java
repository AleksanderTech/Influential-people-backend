package com.alek.influentialpeople.article.controller;

import com.alek.influentialpeople.article.entity.Article;
import com.alek.influentialpeople.article.model.ArticleResponse;
import com.alek.influentialpeople.article.model.ArticleSearchFilter;
import com.alek.influentialpeople.common.SearchFilterService;
import com.alek.influentialpeople.common.TwoWayConverter;
import com.alek.influentialpeople.hero.entity.Hero;
import com.alek.influentialpeople.hero.persistence.HeroRepository;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

import static com.alek.influentialpeople.common.ConvertersFactory.ConverterType.ARTICLE_TO_ARTICLE_RESPONSE;
import static com.alek.influentialpeople.common.ConvertersFactory.getConverter;

@RestController
@RequestMapping("/article/search-filter")
@PreAuthorize("hasAnyRole('USER','ADMIN')")
public class SearchFilterArticleController {

    private TwoWayConverter<Article, ArticleResponse> articleResponseConverter = getConverter(ARTICLE_TO_ARTICLE_RESPONSE);
    private SearchFilterService<Article, ArticleSearchFilter> searchFilterService;
    private HeroRepository heroRepository;

    public SearchFilterArticleController(SearchFilterService<Article, ArticleSearchFilter> searchFilterService, HeroRepository heroRepository) {
        this.searchFilterService = searchFilterService;
        this.heroRepository = heroRepository;
    }

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<Iterable<ArticleResponse>> getAllPaged(@RequestParam(value = "paging", defaultValue = "true") Boolean paging,
                                                                 @RequestParam(value = "title", required = false) String title,
                                                                 @RequestParam(value = "hero", required = false) List<String> heroes,
                                                                 @RequestParam(value = "sort", required = false) String sorting,
                                                                 Pageable pageRequest) {

        List<Hero> heroesDb = null;
        if (heroes != null) {
            heroesDb = heroRepository.findByNameIn(heroes);
        }
        ArticleSearchFilter articleSearchFilter = ArticleSearchFilter.builder().heroes(heroesDb).pageRequest(pageRequest).title(title).sorting(sorting).build();
        if (paging) {
            return ResponseEntity.status(HttpStatus.OK).body(searchFilterService.findPaged(articleSearchFilter).map(article -> articleResponseConverter.convert(article)));
        } else {
            return ResponseEntity.status(HttpStatus.OK).body(searchFilterService.findList(articleSearchFilter).stream().map(article -> articleResponseConverter.convert(article)).collect(Collectors.toList()));
        }
    }
}