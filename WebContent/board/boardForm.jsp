<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style>
	
</style>

</head>
<body>
<%@ include file="../components/header.jsp" %>

	<!-- 
원래 여기서 또 세션을 체크해야하는데
필터를 만들어서 해결하자  -->

	<br/>
	<div class="container" style="width: 60%;">
	<div class="">
	<h1 style="text-align: center">게시글 작성</h1>
		<form action="/lectureEvaluation/boardServlet?cmd=boardRegister" method="post">
			<input type="hidden" name="userId" value="${sessionScope.principal}" />
			<div class="form-group">
	    <label for="title">제목</label>
	    <input type="text"  class="form-control" placeholder="제목 입력하세요"  name="title">
	  </div>
	  
	  <div id="editor" class="form-group">
	  <label for="content">내용</label>
	  <textarea class="form-control" rows="35" cols="50" id="summernote" name="content"></textarea>
	</div>
	 <button id="btn-register" class="btn btn-primary">글쓰기</button>
		</form>
		
	</div>
	</div>
<script>

$('#summernote').summernote({
  placeholder: '내용을 입력하세요',
  tabsize: 2,
  height: 400,
  callbacks : {
	  onImageUpload : function(files ,editor, welEditable){
		  sendFile(files[0],this);
	  }
  }
});

function sendFile(file,editor){
	//파일 전송을 위한 폼 생성
	data = new FormData();
	data.append("uploadFile", file);
	$.ajax({
		data : data,
		type : "POST",
		url : "/lectureEvaluation/boardServlet?cmd=image",
		cache : false,
		contentType : false,
		processData : false,
		success : function(data){
			
			$(editor).summernote('editor.insertImage' ,data.url);
		}
	});
}

</script>


</body>
</html>