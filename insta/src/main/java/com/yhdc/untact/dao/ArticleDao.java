package com.yhdc.untact.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.yhdc.untact.dto.Article;
import com.yhdc.untact.dto.Board;

@Mapper
public interface ArticleDao {

	Board getBoardById(@Param("id") int id);

	int getLastInsertId();

	public Article getArticleById(@Param("id") int id);

	int getArticleCount(@Param("id") int id, @Param("searchType") String searchType, @Param("keyword") String keyword);

	List<Article> getPrintArticles(@Param("boardId") int boardId, @Param("searchType") String searchType,
			@Param("keyword") String keyword, @Param("limitFrom") int limitFrom, @Param("limitTake") int limitTake);

	public int writeArticle(@Param("boardId") int boardId, @Param("memberId") int memberId,
			@Param("title") String title, @Param("content") String content);

	public boolean modifyArticle(@Param("id") Integer id, @Param("title") String title,
			@Param("content") String content);

	public void deleteArticleById(@Param("id") int id);

}
