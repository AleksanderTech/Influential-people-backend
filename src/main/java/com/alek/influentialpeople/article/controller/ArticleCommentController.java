package com.alek.influentialpeople.article.controller;

import java.util.List;

import com.alek.influentialpeople.article.service.ArticleCommentService;
import com.alek.influentialpeople.article.domain.ArticleComment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ArticleCommentController {

	@Autowired
	ArticleCommentService articleCommentService;


	@RequestMapping(path = "/article/{id}/comment", method = RequestMethod.GET)
	public List<ArticleComment> getCommentsOfArticle(@PathVariable String id) {

		return articleCommentService.getAllArticleComments(Long.valueOf(id));
	}



	@RequestMapping(path = "/article/{id}/comment", method = RequestMethod.POST)
	public void addUser(@RequestBody ArticleComment articleComment) { // deserializacja
		articleCommentService.addArticleComment(articleComment);
	}

}
