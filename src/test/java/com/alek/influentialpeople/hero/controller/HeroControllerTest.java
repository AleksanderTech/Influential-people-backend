package com.alek.influentialpeople.hero.controller;

import com.alek.influentialpeople.article.service.ArticleService;
import com.alek.influentialpeople.exception.controller.ExceptionController;
import com.alek.influentialpeople.hero.entity.Hero;
import com.alek.influentialpeople.hero.service.HeroService;
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

@RunWith(MockitoJUnitRunner.class)
public class HeroControllerTest {

    @Mock
    private HeroService heroService;

    @Mock
    private ArticleService articleService;

    @InjectMocks
    private HeroController heroController;

    private MockMvc mockMvc;
    private Hero hero1;
    private Hero hero2;

    @Before
    public void setUp() {
        this.hero1 = Hero.builder().fullName("hero1").score(1).avatarImagePath("/hero1/path").build();
        this.hero2 = Hero.builder().fullName("hero2").score(2).avatarImagePath("/hero2/path").build();
        mockMvc = MockMvcBuilders.standaloneSetup(heroController).setControllerAdvice(new ExceptionController()).setCustomArgumentResolvers(new PageableHandlerMethodArgumentResolver()).build();
    }

    @Test
    public void findAllHeroes_heroesFound_shouldReturnHeroes() throws Exception {

        Mockito.when(heroService.findAllHeroes(Mockito.any(Pageable.class))).thenReturn(new PageImpl<>(Lists.newArrayList(hero1,hero2)));
        mockMvc.perform(MockMvcRequestBuilders.get("/heroes")).andExpect(MockMvcResultMatchers.status().isOk());
    }

}