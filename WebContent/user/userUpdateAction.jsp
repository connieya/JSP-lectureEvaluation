<% request.setCharacterEncoding("UTF-8"); %>
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
	String userName = request.getParameter("userName");
	String userPassword = request.getParameter("userPassword");
	String userAddr = request.getParameter("userAddr");
	//String userId = request.getParameter("pr");
	
	String userId = null;
	
	if( (session.getAttribute("principal") !=null) ){
		
		 userId = (String) session.getAttribute("principal");
	}
	
	//PrintWriter ss = response.getWriter();
	// ss.println("<script>");
	//ss.println("alert('"+userId+"')");
	//ss.println("</script>");
	
	/* 회원 수정을 할 때 세션값으로 지정한 (userId) 데이터를 통해
	//로직을 수행해야하는지?
	//primary key인 userNo 해도 될거같은데?? */

	
	UserDAO dao = new UserDAO();
	int result =dao.회원수정(userId,userName,userPassword,userAddr);
	
	if(result ==1 ){
		PrintWriter script = response.getWriter();
		script.println("<script>");
		script.println("alert('회원 수정완료')");
		script.println("location.href='../index.jsp'");
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