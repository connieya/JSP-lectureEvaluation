<% request.setCharacterEncoding("UTF-8"); %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="user.UserDAO"%>
<%@page import="java.io.PrintWriter"%>
<jsp:useBean id="user" class="user.User" scope="page"/>
<jsp:setProperty property="*" name="user" />
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<%
		
		UserDAO userdao = new UserDAO();
		int result = userdao.login(user);
		
		if(result == 1){
			PrintWriter script = response.getWriter();
			script.println("<script>");
			script.println("alert('로그인 성공')");
			script.println("location.href='../index.jsp'");
			script.println("</script>");
			session.setAttribute("principal", user.getUserId());
		
		}else{
			
			PrintWriter script = response.getWriter();
			script.println("<script>");
			script.println("alert('로그인 실패')");
			script.println("history.back()");
			script.println("</script>");
		}
	%>
	
</body>
</html>