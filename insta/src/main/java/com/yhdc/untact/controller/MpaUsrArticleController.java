package com.yhdc.untact.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yhdc.untact.dto.Article;
import com.yhdc.untact.util.ResultData;
import com.yhdc.untact.util.Util;

@Controller
public class MpaUsrArticleController {
	private int articleLastId;
	private List<Article> articles;
	
	public MpaUsrArticleController() {
		articleLastId = 0;
		new ArrayList<>();
		makeTestData();
	}
	
	@RequestMapping("/mpaUsr/article/doWrite")
	@ResponseBody
	public ResultData doWrite(String title, String body) {
		int id = writeArticle(title, body);
		Article article = getArticleById(id);
		
		return new ResultData("S-1", id + "번 글이 작성되었습니다.", article);
	}
		
	
	@RequestMapping("/mpaUsr/article/getArticle")
	@ResponseBody
	public ResultData getArticle(int id) {
		Article article = getArticleById(id);

		if (article == null) {
			return new ResultData("F-1", id + "번 글은 존재하지 않습니다.", "id", id);
		}

		return new ResultData("S-1", article.getId() + "번 글 입니다.", "article", article);
	}
	
	
	@RequestMapping("/mpaUsr/article/doDelete")
	@ResponseBody
	public ResultData doDelete(int id) {
		boolean delete = deleteArticleById(id);

		if (delete == false) {
			return new ResultData("F-1", id + "번 글은 존재하지 않습니다.", "id", id);
		}

		return new ResultData("S-1", id + "번 글이 삭제되었습다.", "id", id);
	}
	
	
	
	
	
//	Internal Methods
	
	private void makeTestData() {
		for(int i = 0; i<3; i++) {
			writeArticle("Title1", "Body1");
		}
	}
	
	
	private boolean deleteArticleById(int id) {
		
		for(Article article : articles) {
			if(article.getId() == id) {
				articles.remove(article);
				return true;
			}
		}		
		return false; 
	}
	
	
	private int writeArticle(String title, String body) {
		int id = articleLastId + 1;
		String regDate = Util.getNowDateStr();
		String updateDate = Util.getNowDateStr();
		
		Article article = new Article(id, regDate, updateDate, title, body);
		articles.add(article);

		articleLastId = id;
		return id;
	}
	

	private Article getArticleById(int id) {
		for (Article article : articles) {
			if (article.getId() == id) {
				return article;
			}
		}
		return null;
	}	
		
}

