<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style type="text/css">
	.comment_inbox_text{
	
		min-height: 17px;
		display: block;
		border : 0;
		font-size: 13px;
		box-sizing : border-box;
	}
	.commentBox .comment_list .comment_area{
		position: relative;
		padding: 12px 23px 10px 0;
	}
	.comment_nick_box .comment_nick_info {
    display: inline-block;
    position: relative;
    font-size: 13px;
    vertical-align: top;
}

ul{
	list-style: none;
}
</style>
</head>
<body>
<%@ include file="../components/header.jsp" %>
<h1 style="text-align: center">글상세보기</h1>
<div class="container">
		<div class="row">
			<table class="table table-striped" style="text-align :center; border: 1px solid #dddddd">
				<thead>
					<tr>
						<th colspan="4" style="background-color: #eeeeee; text-align: center;">게시판 글보기</th>
					</tr>
				</thead>
				<tbody>
				 
				 <!-- DB에서 게시글 번호를 기준으로 가져온것임 --> 
					<tr>
						<td style="width:20%">제목</td>
						<td>${detail.title }</td>
						<td >작성일</td>
						<td>${detail.createDate }</td>
					</tr>
					
					<tr>
						<td >작성자</td>
						<td>${detail.userId }</td>
						<td >조회수</td>
						<td>${detail.readCount }</td>
					</tr>
					
					<tr>
						
					</tr>
					<tr>
						<td >내용</td>
						<td colspan="3" style="height: 200px; text-align: left;">${detail.content }</td>
					</tr>
					
				
				</tbody>
			</table>
			<a  style="margin: 0px 5px" href="/lectureEvaluation/boardServlet?cmd=boardList" class="btn btn-primary">목록</a>
			<a  style="margin: 0px 5px" href="/lectureEvaluation/boardServlet?cmd=updateForm&bno=${detail.bno }" class="btn btn-warning">수정</a>
			
	<%-- <a  style="margin: 0px 5px" onclick="return confirm('정말 삭제하시겠습니까?')" href="/lectureEvaluation/boardServlet?cmd=delete&bno=${detail.bno }" class="btn btn-danger">삭제</a> --%>
			 <button style="margin: 0px 5px" class="btn btn-danger" onclick="boardDelete(${detail.bno})">삭제</button> 
	<!-- 		왜 ajax로 삭제를 하려고 할까? 그냥 바로 서블릿으로 요청하면 안되나??
			왜 그럴까? 비동기로 처리하고 싶어서? 단지 그 이유때문에? --> 
	</div>
	<div class="commentBox" style="margin-top: 5px;">
	
	<ul class="comment_list" id="comment__list">
	<li class="commentItem">
	<div class="comment_area" style="border-top: 2px solid #e5e5e5">
		<div class="comment_box">
			<div class="comment_nick_box">
				<div class="comment_nick_info" id="comment_username">장정우니당</div>
			</div>
			<div class="comment_text_box">
				<p class="comment_text_box" id="comment_content">취뽀하자 ㅎㅎ </p>
			</div>
			<div class="comment_info_box">
				<span class="comment_info_date">2021.01.30. 19:05</span>
				<a href="" class="comment_info_button">답글쓰기</a>
			</div>
			<div class="comment_tool"></div>
		</div> <!-- comment_box -->
	</div>  <!-- comment_area -->
	</li>
	</ul>
	<c:choose>
	<c:when test="${sessionScope.principal == null }">
		<div class="notComment" style="width: 100%; border: 2px solid #dcdcdc">
		<p>로그인 후 댓글을 남길 수 있습니다.</p>
			
		</div>
	</c:when>
	<c:otherwise>
	<div class="commentWriter" style="width: 10	0%; border: 2px solid #dcdcdc; border-radius: 6px; box-sizing: border-box; margin-top:10px;">
	
	<div class="commentInbox" style="position: relative; margin: 10px 5px; height:80%">
	<em class="comment_inbox_name" style="display:block; font-weight: 700; margin: 10px 0; font-size: 17px;"> ${userObject.userName }</em>
	<textarea style=" max-height: 500px; width:100%;"class="comment_inbox_text" rows="1" placeholder="댓글을 남겨보세요"  id="reply"></textarea>
	</div>
	
	
	<div class="comment_attach" style="display: block; position: relative; clear: both;">
	<div class="register_box">
	
	<input type="hidden" value="${userObject.userName }"  id="userName" />
	<button style="float: right"  class="btn btn-register" onclick="commentRegister(${detail.bno})">등록</button>
	</div>
	</div>
	
	</div>  <!-- commetWriter  -->
	</c:otherwise>
	</c:choose>
	</div><!--  commentBox  -->
	
	</div>

	<script src="/lectureEvaluation/js/boardDetail.js"></script>
</body>
</html>