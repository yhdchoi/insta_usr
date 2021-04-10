package com.yhdc.untact.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.yhdc.untact.dto.Article;
import com.yhdc.untact.dto.Board;

@Mapper
public interface ArticleDao {


	Board getBoardById(@Param("boardId") int boardI);

	int getLastInsertId();
	
	int getArticleCount(@Param("id") int id);
	
	public int writeArticle(@Param("boardId") int boardId, 
			@Param("memberId") int memberId, 
			@Param("title") String title, 
			@Param("body") String body);

	public boolean modifyArticle(@Param("id") Integer id, 
			@Param("title") String title, 
			@Param("body") String body);

	public void deleteArticleById(@Param("id") int id);

	public Article getArticleById(@Param("id") int id);
	
}
