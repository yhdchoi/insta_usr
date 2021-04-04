package com.yhdc.untact.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.yhdc.untact.dto.Article;

@Mapper
public interface ArticleDao {


	public int writeArticle(@Param("boardId") int boardId, 
			@Param("memberId") int memberId, 
			@Param("title") String title, 
			@Param("body") String body);

	public boolean modifyArticle(@Param("id") Integer id, 
			@Param("title") String title, 
			@Param("body") String body);

	public void deleteArticleById(@Param("id") int id);

	public Article getArticleById(@Param("id") int id);
	
	int getLastInsertId();
}
