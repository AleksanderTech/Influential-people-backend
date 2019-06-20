//package com.alek.influentialpeople.controller;
//
//import java.util.List;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.web.bind.annotation.PathVariable;
//import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestMethod;
//import org.springframework.web.bind.annotation.RestController;
//import com.alek.influentialpeople.persistence.entity.ArticleComment;
//import com.alek.influentialpeople.service.ArticleCommentService;
//
//@RestController
//public class ArticleCommentController {
//
//	@Autowired
//	ArticleCommentService articleCommentService;
//	
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
//	public void addUser(@RequestBody ArticleComment articleComment) { // deserializacja
//		articleCommentService.addArticleComment(articleComment);
//	}
//
//}
