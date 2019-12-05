package com.alek.influentialpeople.article.controller;

import com.alek.influentialpeople.article.entity.Article;
import com.alek.influentialpeople.article.model.ArticleHeader;
import com.alek.influentialpeople.article.model.ArticleRequest;
import com.alek.influentialpeople.article.model.ArticleResponse;
import com.alek.influentialpeople.article.service.ArticleService;
import com.alek.influentialpeople.common.TwoWayConverter;
import com.alek.influentialpeople.exception.controller.ExceptionController;
import com.alek.influentialpeople.hero.entity.Hero;
import org.assertj.core.util.Lists;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableHandlerMethodArgumentResolver;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Date;

import static com.alek.influentialpeople.common.ConvertersFactory.ConverterType.*;
import static com.alek.influentialpeople.common.ConvertersFactory.getConverter;

@RunWith(MockitoJUnitRunner.class)
public class ArticleControllerTest {

    @Mock
    private ArticleService articleService;
    @InjectMocks
    private ArticleController articleController;

    private TwoWayConverter<ArticleRequest, Article> articleRequestConverter = getConverter(ARTICLE_REQUEST_TO_ARTICLE);
    private TwoWayConverter<Article, ArticleHeader> articleHeaderConverter = getConverter(ARTICLE_TO_ARTICLE_HEADER);
    private TwoWayConverter<Article, ArticleResponse> articleResponseConverter = getConverter(ARTICLE_TO_ARTICLE_RESPONSE);

    private Article article1;
    private Article article2;
    private MockMvc mockMvc;

    @Before
    public void setUp() {

        article1 = Article.builder().id(1L).created_at(new Date().getTime()).title("title1").text("text1").hero(new Hero("hero1")).build();
        article2 = Article.builder().id(2L).created_at(new Date().getTime()).title("title2").text("text2").hero(new Hero("hero2")).build();
        mockMvc = MockMvcBuilders.standaloneSetup(articleController).setControllerAdvice(new ExceptionController()).setCustomArgumentResolvers(new PageableHandlerMethodArgumentResolver()).build();
    }

    @Test
    public void findArticles_articlesExist_returnsArticlesAndStatus200() throws Exception {

        Mockito.when(articleService.findArticles(Mockito.any(Pageable.class))).thenReturn(new PageImpl<>(Lists.list(article1, article2)));
        mockMvc.perform(MockMvcRequestBuilders.get("/article")).andExpect(MockMvcResultMatchers.status().isOk());
    }

}