package com.alek.influentialpeople.article.controller;

import com.alek.influentialpeople.article.entity.Article;
import com.alek.influentialpeople.article.model.ArticleResponse;
import com.alek.influentialpeople.article.model.ArticleSearchFilter;
import com.alek.influentialpeople.common.SearchFilterService;
import com.alek.influentialpeople.common.TwoWayConverter;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import static com.alek.influentialpeople.common.ConvertersFactory.ConverterType.ARTICLE_TO_ARTICLE_RESPONSE;
import static com.alek.influentialpeople.common.ConvertersFactory.getConverter;

@RestController
@RequestMapping("/article/search-filter")
@PreAuthorize("hasAnyRole('USER','ADMIN')")
public class SearchFilterArticleController {

    private TwoWayConverter<Article, ArticleResponse> articleResponseConverter = getConverter(ARTICLE_TO_ARTICLE_RESPONSE);
    private SearchFilterService<Article, ArticleSearchFilter> searchFilterService;

    public SearchFilterArticleController(SearchFilterService<Article, ArticleSearchFilter> searchFilterService) {
        this.searchFilterService = searchFilterService;
    }

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<Page<ArticleResponse>> getAllPaged(@RequestParam(value = "title", required = false) String title,
                                                             @RequestParam(value = "sort", required = false) String sorting,
                                                             Pageable pageRequest) {

        ArticleSearchFilter articleSearchFilter = ArticleSearchFilter.builder().title(title).sorting(sorting).build();
        return ResponseEntity.status(HttpStatus.OK).body(searchFilterService.findPaged(articleSearchFilter).map(article -> articleResponseConverter.convert(article)));
    }

//    @RequestMapping(method = RequestMethod.GET)
//    public ResponseEntity<List<HeroResponse>> getAllList(@RequestParam(value = "name", required = false) String name,
//                                                     @RequestParam(value = "score", required = false) Integer score,
//                                                     Pageable pageRequest) {
//
//        HeroSearchFilter heroSearchFilter = new HeroSearchFilter(name, score, null, pageRequest);
//        return ResponseEntity.status(HttpStatus.OK).body(searchFilterService.findList(heroSearchFilter).stream().map(hero -> heroResponseConverter.convert(hero)).collect(Collectors.toList()));
//    }
}