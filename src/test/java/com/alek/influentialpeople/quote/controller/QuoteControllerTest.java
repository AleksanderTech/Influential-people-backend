package com.alek.influentialpeople.quote.controller;

import com.alek.influentialpeople.common.TwoWayConverter;
import com.alek.influentialpeople.exception.controller.ExceptionController;
import com.alek.influentialpeople.hero.entity.Hero;
import com.alek.influentialpeople.quote.entity.Quote;
import com.alek.influentialpeople.quote.model.QuoteRequest;
import com.alek.influentialpeople.quote.model.QuoteResponse;
import com.alek.influentialpeople.quote.service.QuoteCrudService;
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

import static com.alek.influentialpeople.common.ConvertersFactory.ConverterType.QUOTE_REQUEST_TO_QUOTE;
import static com.alek.influentialpeople.common.ConvertersFactory.ConverterType.QUOTE_TO_QUOTE_RESPONSE;
import static com.alek.influentialpeople.common.ConvertersFactory.getConverter;

@RunWith(MockitoJUnitRunner.class)
public class QuoteControllerTest {

    @Mock
    private QuoteCrudService quoteService;
    @InjectMocks
    private QuoteCrudController quoteController;

    private TwoWayConverter<Quote, QuoteResponse> quoteResponseConverter = getConverter(QUOTE_TO_QUOTE_RESPONSE);
    private TwoWayConverter<QuoteRequest, Quote> quoteRequestConverter = getConverter(QUOTE_REQUEST_TO_QUOTE);

    private Hero aristotle;
    private Quote quote1;
    private Quote quote2;
    private MockMvc mockMvc;

    @Before
    public void setUp() {
        aristotle = Hero.builder().name("Aristotle").build();
        quote1 = Quote.builder().content("I have never met a man so ignorant that I couldn't learn something from him.").hero(aristotle).build();
        quote2 = Quote.builder().content("I have not failed. I've just found 10,000 ways that won't work.").hero(aristotle).build();
        mockMvc = MockMvcBuilders.standaloneSetup(quoteController).setControllerAdvice(new ExceptionController()).setCustomArgumentResolvers(new PageableHandlerMethodArgumentResolver()).build();
    }

    @Test
    public void findQuotes_quotesExist_returnsQuotesAndStatus200() throws Exception {

        Mockito.when(quoteService.findAll(Mockito.any(Pageable.class))).thenReturn(new PageImpl<>(Lists.list(quote1, quote2)));
        mockMvc.perform(MockMvcRequestBuilders.get("/quote")).andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    public void findHeroQuotes_quotesExist_returnsQuotesAndStatus200() throws Exception {

        Mockito.when(quoteService.findByHero(Mockito.eq("Aristotle"), Mockito.any(Pageable.class))).thenReturn(new PageImpl<>(Lists.list(quote1, quote2)));
        mockMvc.perform(MockMvcRequestBuilders.get("/quote/hero/"+aristotle.getName())).andExpect(MockMvcResultMatchers.status().isOk());
    }
}