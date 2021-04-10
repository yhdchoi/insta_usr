package com.yhdc.untact.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yhdc.untact.dao.ArticleDao;
import com.yhdc.untact.dto.Article;
import com.yhdc.untact.dto.Board;
import com.yhdc.untact.dto.ResultData;

@Service
public class ArticleService {

	@Autowired
	private ArticleDao articleDao;
	
	// CHECK
	private boolean isEmpty(Article article) {
		
		if (article == null) {
			return true;
		} else if (article.isDelStatus()) {
			return true;
		}
		return false;
	}

	// LIST
	public Board getBoardById(int boardId) {
		return articleDao.getBoardById(boardId);
	}
	
	// GET
	public Article getArticleById(int id) {
		return articleDao.getArticleById(id);
	}
	
	public int getArticleCount(int boardId) {
		return articleDao.getArticleCount(boardId);
	}
	
		
	// WRITE
	public ResultData writeArticle(String title, String body) {
		int id = articleDao.getLastInsertId();
		int boardId = 3;
		int memberId = 3;
		articleDao.writeArticle(boardId, memberId, title, body);

		return new ResultData("S-1", id + "번 글이 작성되었습니다.", "id", id);
	}

	// EDIT
	public ResultData editArticle(int id, String title, String body) {

		Article article = getArticleById(id);

		if (isEmpty(article)) {
			return new ResultData("F-1", id + "번 글은 존재하지 않습니다.", "id", id);
		}

		return new ResultData("S-1", id + "번 글이 수정되었습다.", "id", id);
	}

	// DELETE
	public ResultData deleteArticleById(int id) {
		Article article = getArticleById(id);

		if (isEmpty(article)) {
			return new ResultData("F-1", id + "번 글은 존재하지 않습니다.", "id", id);
		}
		
		articleDao.deleteArticleById(id);

		return new ResultData("S-1", id + "번 글이 삭제되었습다.", "id", id, "boardId", article.getBoardId());
	}

	public List<Article> getPrintArticles(int boardId, int itemsInPage, int page) {
		int limitFrom = (page-1)*itemsInPage;
		int limitTake = itemsInPage;
		return articleDao.getPrintArticles(boardId, limitFrom, limitTake);
	}

}
