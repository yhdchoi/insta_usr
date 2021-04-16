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
				<input name="boardId" type="hidden" value="${board.id}" />
				
				<div class="form-control">
					<label class="label">
						<span class="label-text">Option</span>
					</label>
					<select name="searchType" class="select select-bordered">
						<option value="titleAndBody">Title+Content</option>
						<option value="title">Title</option>
						<option value="Content">Content</option>
					</select>
					<script>
						const param_searchType = '${param.searchType}';
						if ( param_searchType.length > 0 ) {
							$('.search-form-box from [name="searchType"]')
							.val('${param.searchType}');
						}
					</script>
				</div>
				
				<div class="form-control">
					<input type="text" class="input input-bodered" name="keyword" value="${param.keyword}" placeholder="Enter keyword" maxlength="50"/>
				</div>
				
				<div class="form-control">
					<input type="submit" class="btn btn-sm btn-primary" value="search"/>
				</div>
			</form>
		
		</div>
		
		<div class="articles mt-2">
			<c:if test="${articles==null || articles.size()==0}">
				There are no results.
			</c:if>
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