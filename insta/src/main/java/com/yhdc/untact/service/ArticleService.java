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
		int id = articleDao.getLastInsertId();
		int boardId = 3;
		int memberId = 3;
		articleDao.writeArticle(boardId, memberId, title, body);

		return new ResultData("S-1", id + "번 글이 작성되었습니다.", "id", id);
	}

	public ResultData modifyArticle(int id, String title, String body) {

		Article article = getArticleById(id);

		if (isEmpty(article)) {
			return new ResultData("F-1", id + "번 글은 존재하지 않습니다.", "id", id);
		}

		return new ResultData("S-1", id + "번 글이 수정되었습다.", "id", id);
	}


	public ResultData deleteArticleById(int id) {
		Article article = getArticleById(id);

		if (isEmpty(article)) {
			return new ResultData("F-1", id + "번 글은 존재하지 않습니다.", "id", id);
		}
		
		//delStatus needed

		articleDao.deleteArticleById(id);

		return new ResultData("S-1", id + "번 글이 삭제되었습다.", "id", id);
	}

	public Article getArticleById(int id) {

		return articleDao.getArticleById(id);
	}
	
	private boolean isEmpty(Article article) {
		
		if (article == null) {
			return true;
		} else if (article.isDelStatus()) {
			return true;
		}
		return false;
	}
}
