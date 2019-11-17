package com.alek.influentialpeople.article.comment;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ArticleCommentController {

	@Autowired
	ArticleCommentService articleCommentService;

//
//	@RequestMapping(path = "/article/{id}/comment", method = RequestMethod.GET)
//	public List<ArticleComment> getCommentsOfArticle(@PathVariable String id) {
//
//		return articleCommentService.getAllArticleComments(Long.valueOf(id));
//	}
//
//
//
//	@RequestMapping(path = "/article/{id}/comment", method = RequestMethod.POST)
//	public void addUser(@RequestBody ArticleComment comment) { // deserializacja
//		articleCommentService.addArticleComment(comment);
//	}

}
