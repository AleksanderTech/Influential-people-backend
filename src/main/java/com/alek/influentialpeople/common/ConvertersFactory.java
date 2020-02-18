package com.alek.influentialpeople.common;

import com.alek.influentialpeople.article.comment.service.ArtCommentRequestConverter;
import com.alek.influentialpeople.article.comment.service.ArtCommentResponseConverter;
import com.alek.influentialpeople.article.service.ArticleHeaderConverter;
import com.alek.influentialpeople.article.service.ArticleRequestConverter;
import com.alek.influentialpeople.article.service.ArticleResponseConverter;
import com.alek.influentialpeople.hero.category.service.CategoryChangesConverter;
import com.alek.influentialpeople.hero.category.service.CategoryResponseConverter;
import com.alek.influentialpeople.hero.service.HeroDetailConverter;
import com.alek.influentialpeople.hero.service.HeroRequestConverter;
import com.alek.influentialpeople.hero.service.HeroResponseConverter;
import com.alek.influentialpeople.quote.service.QuoteRequestConverter;
import com.alek.influentialpeople.quote.service.QuoteResponseConverter;
import com.alek.influentialpeople.security.service.UserRegistrationConverter;
import com.alek.influentialpeople.user.service.UserAccountConverter;
import com.alek.influentialpeople.user.service.UserResponseConverter;

import java.util.ArrayList;
import java.util.List;


public class ConvertersFactory {

    public static <E, M> TwoWayConverter<E, M> getConverter(ConverterType converterType) {
        TwoWayConverter converter = null;
        switch (converterType) {
            case USER_ACCOUNT_TO_USER:
                converter = new <E, M>UserAccountConverter();
                break;
            case USER_TO_USER_RESPONSE:
                converter = new <E, M>UserResponseConverter();
                break;
            case USER_REGISTRATION_TO_USER:
                converter = new <E, M>UserRegistrationConverter();
                break;
            case HERO_REQUEST_TO_HERO:
                converter = new <E, M>HeroRequestConverter();
                break;
            case HERO_TO_HERO_RESPONSE:
                converter = new <E, M>HeroResponseConverter();
                break;
            case HERO_TO_HERO_DETAIL:
                converter = new <E, M>HeroDetailConverter();
                break;
            case ARTICLE_REQUEST_TO_ARTICLE:
                converter = new <E, M>ArticleRequestConverter();
                break;
            case ARTICLE_TO_ARTICLE_HEADER:
                converter = new <E, M>ArticleHeaderConverter();
                break;
            case QUOTE_TO_QUOTE_RESPONSE:
                converter = new <E, M>QuoteResponseConverter();
                List list = new <String>ArrayList();
                break;
            case QUOTE_REQUEST_TO_QUOTE:
                converter = new <E, M>QuoteRequestConverter();
                break;
            case ARTICLE_TO_ARTICLE_RESPONSE:
                converter = new <E, M>ArticleResponseConverter();
                break;
            case ART_COMMENT_REQUEST_TO_ART_COMMENT:
                converter = new <E, M>ArtCommentRequestConverter();
                break;
            case ART_COMMENT_TO_ART_COMMENT_RESPONSE:
                converter = new <E, M>ArtCommentResponseConverter();
                break;
            case CATEGORY_TO_CATEGORY_RESPONSE:
                converter = new <E, M>CategoryResponseConverter();
                break;
            case CATEGORY_CHANGES_TO_CATEGORY:
                converter = new <E, M>CategoryChangesConverter();
                break;
        }
        return converter;
    }

    public enum ConverterType {
        USER_TO_USER_RESPONSE,
        USER_REGISTRATION_TO_USER,
        USER_ACCOUNT_TO_USER,
        HERO_REQUEST_TO_HERO,
        HERO_TO_HERO_RESPONSE,
        HERO_TO_HERO_DETAIL,
        ARTICLE_REQUEST_TO_ARTICLE,
        ARTICLE_TO_ARTICLE_HEADER,
        QUOTE_TO_QUOTE_RESPONSE,
        QUOTE_REQUEST_TO_QUOTE,
        ARTICLE_TO_ARTICLE_RESPONSE,
        ART_COMMENT_REQUEST_TO_ART_COMMENT,
        ART_COMMENT_TO_ART_COMMENT_RESPONSE,
        CATEGORY_TO_CATEGORY_RESPONSE,
        CATEGORY_CHANGES_TO_CATEGORY
    }
}
