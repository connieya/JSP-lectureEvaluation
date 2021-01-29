<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<!-- 	신고하기 모달 -->
	<div class="modal fade" id="reportModal" tabindex="-1" role="dialog" aria-labelledby="modal" aria-hidden="true">
		<div class="modal-dialog" >
			<div class="modal-content">
				<div class="modal-header">
					<h5 class="modal-title" id="modal">신고하기</h5>
					<button class="close" type="button" data-dismiss="modal" aria-label="Close">
					<span aria-hidden="true">&times;</span>
					</button>
				</div>
				<div class="modal-body">
					<form action="/lectureEvaluation/evaluation/reportAction.jsp" method="post">
	
				<div class="form-group">
					<label for="">신고 제목</label>
					<input type="text" name="reportTitle" class="form-control" maxlength="40" />
				</div>
				<div class="form-group">
					<label for="">신고 내용</label>
					<textarea  name="reportContent" class="form-control" maxlength="2048" ></textarea>
				</div>		
				<div class="modal-footer">
					<button type="button" class="btn btn-secondary" data-dismiss="modal">취소</button>
					<button type="submit" class="btn btn-danger">신고하기</button>
				
				</div>
					</form>
				</div><!--  div modal body -->
				
			
				
				
			</div> <!-- div modal content -->
			
		</div> <!-- div modal dialog -->
	</div> <!-- div modal fade -->
</body>
</html>