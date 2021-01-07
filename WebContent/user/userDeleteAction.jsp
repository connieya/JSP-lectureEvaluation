<%@page import="java.io.PrintWriter"%>
<%@page import="user.UserDAO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<%
	String userId = request.getParameter("userId");
	

	;
	UserDAO dao = new UserDAO();
	int result =dao.회원탈퇴(userId);
	
	if(result ==1 ){
		PrintWriter script = response.getWriter();
		script.println("<script>");
		script.println("alert('회원탈퇴가 완료되었습니다. 그동안 이용해주셔서 감사합니다')");
		script.println("location.href='logout.jsp'");
		script.println("</script>");
	}else{
		PrintWriter script = response.getWriter();
		script.println("<script>");
		script.println("alert('오류가 발생했습니다.')");
		script.println("history.back()");
		script.println("</script>");
	}
%>

</body>
</html>