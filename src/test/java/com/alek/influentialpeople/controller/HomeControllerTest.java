package com.alek.influentialpeople.controller;

import com.alek.influentialpeople.article.domain.Article;
import com.alek.influentialpeople.article.service.ArticleService;
import com.alek.influentialpeople.service.TheHeroService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.ArrayList;
import java.util.Arrays;

@RunWith(MockitoJUnitRunner.class)
public class HomeControllerTest {

    private MockMvc mockMvc;
    @Mock
    private TheHeroService theHeroService;
    @Mock
    private ArticleService articleService;

//    @JsonView(View.Public.class)
//    @RequestMapping(path = "/home/article", method = RequestMethod.GET)
//    public List<Article> getNewestArticles(HttpServletRequest request) {
//
//        List<Article> articles = theArticleService.getNewestArticles(4);
//        for (int i = 0; i < articles.size(); i++) {
//
//            String url = urlBuilder.requestRoot(request).slash().append(EndpointConstants.ARTICLE).slash()
//                    .append(String.valueOf(articles.get(i).getRealId())).build();
//            Link link = linkFactory.getLink(url, EndpointConstants.SELF);
//            articles.get(i).add(link);
//        }
//        return articles;
//    }


    @Before
    public void setup() {
        mockMvc = MockMvcBuilders.standaloneSetup(new HomeController(theHeroService, articleService)).build();

    }

    @Test
    public void getNewestArticles_properRequest_returnsNewestArticles() throws Exception {
        MockHttpServletRequestBuilder request = MockMvcRequestBuilders.get("/home/article");
        Mockito.when(articleService.getNewestArticles(4)).thenReturn(new ArrayList<>(Arrays.asList(
                new Article(),
                new Article(),
                new Article(),
                new Article())));
        mockMvc.perform(request).andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk());


    }


}