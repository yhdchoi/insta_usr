package com.yhdc.untact.dao;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.yhdc.untact.dto.Article;
import com.yhdc.untact.util.Util;

@Component
public class ArticleDao {
	private int articleLastId;
	private List<Article> articles;

	public ArticleDao() {
		articleLastId = 0;
		articles = new ArrayList<>();
		makeTestData();
	}

	public void makeTestData() {
		for (int i = 0; i < 5; i++) {
			writeArticle("Title", "Body");
		}
	}

	public int writeArticle(String title, String body) {
		int id = articleLastId + 1;
		String regDate = Util.getNowDateStr();
		String updateDate = Util.getNowDateStr();

		Article article = new Article(id, regDate, updateDate, title, body);
		articles.add(article);

		articleLastId = id;
		return id;
	}

	public boolean modifyArticle(Integer id, String title, String body) {
		Article article = getArticleById(id);

		if (article == null) {
			return false;
		}

		article.setTitle(title);
		article.setBody(body);
		article.setUpdateDate(Util.getNowDateStr());

		return true;
	}

	public void deleteArticleById(int id) {

		Article article = getArticleById(id);

		articles.remove(article);
	}

	public Article getArticleById(int id) {
		for (Article article : articles) {
			if (article.getId() == id) {
				return article;
			}
		}
		return null;
	}

}
