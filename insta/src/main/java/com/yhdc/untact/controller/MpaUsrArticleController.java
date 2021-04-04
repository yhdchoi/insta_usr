package com.yhdc.untact.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yhdc.untact.dto.Article;
import com.yhdc.untact.dto.ResultData;
import com.yhdc.untact.service.ArticleService;
import com.yhdc.untact.util.Util;

@Controller
public class MpaUsrArticleController {

	@Autowired
	private ArticleService articleService;

	@RequestMapping("/mpaUsr/article/doWrite")
	@ResponseBody
	public ResultData doWrite(String title, String body) {

		if (Util.isEmpty(title)) {
			return new ResultData("F-1", "제목을 작성해 주세요.");
		}

		if (Util.isEmpty(body)) {
			return new ResultData("F-2", "내용을 작성해 주세요.");
		}

		return articleService.writeArticle(title, body);
	}

	@RequestMapping("/mpaUsr/article/doModify")
	@ResponseBody
	public ResultData doModify(Integer id, String title, String body) {

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
		return articleService.modifyArticle(id, title, body);
	}

	@RequestMapping("/mpaUsr/article/doDelete")
	@ResponseBody
	public ResultData doDelete(Integer id) {

		if (Util.isEmpty(id)) {
			return new ResultData("F-1", "번호를 입력해 주세요.");
		}

		return articleService.deleteArticleById(id);
	}

	@RequestMapping("/mpaUsr/article/getArticle")
	@ResponseBody
	public ResultData getArticle(Integer id) {

		if (Util.isEmpty(id)) {
			return new ResultData("F-1", "번호를 입력해 주세요.");
		}

		Article article = articleService.getArticleById(id);

		if (article == null) {
			return new ResultData("F-1", id + "번 글은 존재하지 않습니다.", "id", id);
		}

		return new ResultData("S-1", article.getId() + "번 글 입니다.", "article", article);
	}

}
