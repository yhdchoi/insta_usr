package com.yhdc.untact.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yhdc.untact.dto.Article;
import com.yhdc.untact.dto.Board;
import com.yhdc.untact.dto.ResultData;
import com.yhdc.untact.service.ArticleService;
import com.yhdc.untact.util.Util;

@Controller
public class MpaUsrArticleController {

	@Autowired
	private ArticleService articleService;
	
	// MSG
	private String msgBack(HttpServletRequest req, String msg) {
		req.setAttribute("msg", msg);
		req.setAttribute("historyBack", true);
		return "common/redirect";
	}
	
	private String msgReplace(HttpServletRequest req, String msg, String replaceUrl) {
		req.setAttribute("msg", msg);
		req.setAttribute("replaceUrl", replaceUrl);
		return "common/redirect";
	}
	
		
	// LIST
	@RequestMapping("/usr/article/doGetList")
	public String doGetList(HttpServletRequest req, int boardId, @RequestParam(defaultValue = "1") int page) {
		Board board = articleService.getBoardById(boardId);
		
		if (board == null) {
			return msgBack(req, boardId + "번 게시판은 존제하지 않습니다.");
		}
		req.setAttribute("board", board);
		
		int totalArticleCount = articleService.getArticleCount(boardId);
		
		req.setAttribute("totalArticleCount", totalArticleCount);
		
		// MAX NUMBER OF POSTS IN A PAGE
		int itemsInPage = 20;
		// TOTAL NUMBER OF PAGE
		int totalPage = (int) Math.ceil(totalArticleCount / (double) itemsInPage);

		// CURRENT PAGE (TEMP)
		req.setAttribute("page", page);
		req.setAttribute("totalPage", totalPage);

		List<Article> articles = articleService.getPrintArticles(boardId, itemsInPage, page);

		System.out.println("articles : " + articles);

		req.setAttribute("articles", articles);
		
		return "/usr/article/doGetlist";
	}

	// GET
	@RequestMapping("/mpaUsr/article/doGet")
	@ResponseBody
	public ResultData doGet(Integer id) {

		if (Util.isEmpty(id)) {
			return new ResultData("F-1", "번호를 입력해 주세요.");
		}

		Article article = articleService.getArticleById(id);

		if (article == null) {
			return new ResultData("F-1", id + "번 글은 존재하지 않습니다.", "id", id);
		}

		return new ResultData("S-1", article.getId() + "번 글 입니다.", "article", article);
	}
	
	// WRITE
	@RequestMapping("/mpaUsr/article/doPost")
	@ResponseBody
	public ResultData doPost(String title, String body) {

		if (Util.isEmpty(title)) {
			return new ResultData("F-1", "제목을 작성해 주세요.");
		}

		if (Util.isEmpty(body)) {
			return new ResultData("F-2", "내용을 작성해 주세요.");
		}

		return articleService.writeArticle(title, body);
	}

	// EDIT
	@RequestMapping("/mpaUsr/article/doPut")
	@ResponseBody
	public ResultData doPut(Integer id, String title, String body) {

		if (Util.isEmpty(id)) {
			return new ResultData("F-1", "id을 작성해 주세요.");
		}

		if (Util.isEmpty(title)) {
			return new ResultData("F-1", "제목을 작성해 주세요.");
		}

		if (Util.isEmpty(body)) {
			return new ResultData("F-1", "내용을 작성해 주세요.");
		}

		Article article = articleService.getArticleById(id);

		if (article == null) {
			return new ResultData("F-1", id + "번 글은 존재하지 않습니다.", "id", id);
		}
		return articleService.editArticle(id, title, body);
	}

	// DELETE
	@RequestMapping("/mpaUsr/article/doDelete")
	public String doDelete(HttpServletRequest req, Integer id) {

		if (Util.isEmpty(id)) {
			return msgBack(req, "번호를 입력해 주세요.");
		}
		
		ResultData rd = articleService.deleteArticleById(id);
		
		if (rd.isFail()) {
			return msgBack(req, rd.getMsg());
		}
		
		String redirectUrl = "../aricle/doGetList?boardId=" + rd.getBody().get("boardId");

		return msgReplace(req, rd.getMsg(), redirectUrl);
	}



}
