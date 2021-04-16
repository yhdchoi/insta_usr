<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<c:set var="pageTitle"
	value="<span><i class='far fa-clipboard'></i></span> <span>${board.name} BOARD</span>" />

<%@ include file="../common/head.jspf"%>

<div class="section section-article-list">
	<div class="container mx-auto">
		<div class="total-items">
			<span>TOTAL NUMBER OF ARTICLES : </span> 
			<span>${totalArticleCount}</span>			
		</div>
		
		<div class="total-pages">
			<span>TOTAL NUMBER OF PAGES : </span> 
			<span>${totalPage}</span>			
		</div>

		<div class="total-items">
			<span>CURRENT PAGE : </span> 
			<span>${page}</span>			
		</div>
		
		<hr />
		<hr />
		
		<div class="search-for-box mt-2">
			<form action="" class="grid gap-2">
				<input class="input input-bordered" name="searchKeyword" type="text" placeholder="Enter keyword" maxlength="50" />
				<input class="btn btn-sm btn-primary" type="submit" value="search" />
			</form>
		
		</div>
		
		<div class="articles mt-2">
			<c:forEach items="${articles}" var="article">
				<div>
					ID : ${article.id}<br>
					REG DATE : ${article.regDate}<br>
					UPDATE DATE : ${article.updateDate}<br>
					TITLE : ${article.title}<br>
					CONTENT : ${article.content}<br>
				</div>
				<hr />
			</c:forEach>
		</div>
		
	</div>
</div>



<%@ include file="../common/foot.jspf"%>