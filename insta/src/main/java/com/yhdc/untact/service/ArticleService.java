package com.yhdc.untact.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yhdc.untact.dao.ArticleDao;
import com.yhdc.untact.dto.Article;
import com.yhdc.untact.dto.ResultData;

@Service
public class ArticleService {

	@Autowired
	private ArticleDao articleDao;

	public ResultData writeArticle(String title, String body) {
		int id = articleDao.writeArticle(title, body);

		return new ResultData("S-1", id + "번 글이 작성되었습니다.", "id", id);
	}

	public ResultData modifyArticle(int id, String title, String body) {

		Article article = getArticleById(id);

		if (article.getId() == id) {
			return new ResultData("F-1", id + "번 글은 존재하지 않습니다.", "id", id);
		}

		return new ResultData("S-1", id + "번 글이 수정되었습다.", "id", id);
	}

	public ResultData deleteArticleById(int id) {
		Article article = getArticleById(id);

		if (article == null) {
			return new ResultData("F-1", id + "번 글은 존재하지 않습니다.", "id", id);
		}

		articleDao.deleteArticleById(id);

		return new ResultData("S-1", id + "번 글이 삭제되었습다.", "id", id);
	}

	public Article getArticleById(int id) {

		return articleDao.getArticleById(id);
	}
}
