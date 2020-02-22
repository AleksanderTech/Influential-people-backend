package com.alek.influentialpeople.hero.controller;

import com.alek.influentialpeople.article.service.ArticleCrudService;
import com.alek.influentialpeople.common.abstraction.CrudService;
import com.alek.influentialpeople.exception.controller.ExceptionController;
import com.alek.influentialpeople.hero.category.entity.Category;
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

import java.util.Arrays;
import java.util.HashSet;

@RunWith(MockitoJUnitRunner.class)
public class HeroCrudControllerTest {

    @Mock
    private CrudService<Hero,String> heroService;

    @Mock
    private ArticleCrudService articleService;

    @InjectMocks
    private HeroCrudController heroCrudController;

    private MockMvc mockMvc;
    private Hero hero1;
    private Hero hero2;
    private Category philosopher;
    private Category tyrant;

    @Before
    public void setUp() {

        philosopher = new Category("philosopher","");
        tyrant = new Category("tyrant","");
        this.hero1 = Hero.builder().name("hero1").rate(1).avatarImagePath("/hero1/path").heroCategories(new HashSet<>(Arrays.asList(tyrant, philosopher))).build();
        this.hero2 = Hero.builder().name("hero2").rate(2).avatarImagePath("/hero2/path").heroCategories(new HashSet<>(Arrays.asList(tyrant, philosopher))).build();
        mockMvc = MockMvcBuilders.standaloneSetup(heroCrudController).setControllerAdvice(new ExceptionController()).setCustomArgumentResolvers(new PageableHandlerMethodArgumentResolver()).build();
    }

    @Test
    public void findAllHeroes_heroesFound_shouldReturnHeroes() throws Exception {

        Mockito.when(heroService.findAll(Mockito.any(Pageable.class))).thenReturn(new PageImpl<>(Lists.newArrayList(hero1, hero2)));
        mockMvc.perform(MockMvcRequestBuilders.get("/hero")).andExpect(MockMvcResultMatchers.status().isOk());
    }

}