<% request.setCharacterEncoding("UTF-8"); %>
<%@page import="javax.mail.Transport"%>
<%@page import="javax.mail.Message"%>
<%@page import="javax.mail.internet.InternetAddress"%>
<%@page import="javax.mail.Address"%>
<%@page import="utill.Gmail"%>
<%@page import="javax.mail.internet.MimeMessage"%>
<%@page import="javax.mail.Session"%>
<%@page import="javax.mail.Authenticator"%>
<%@page import="java.util.Properties"%>
<%@page import="utill.SHA256"%>
<%@page import="user.User"%>
<%@page import="user.UserDAO"%>
<%@page import="java.io.PrintWriter"%>
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
		

		UserDAO dao = new UserDAO();
		/* String code1 = null;
		if(request.getParameter("code") != null){
			code1 = request.getParameter("code");
		}
		
		PrintWriter ss = response.getWriter();
		ss.println("<script>");
		ss.println("alert('"+code1+"')");
		ss.println("</script>"); */

		String userId = null;	
		if(session.getAttribute("principal") !=null){
			userId = (String) session.getAttribute("principal");
			// session 값은 기본적으로 object 이기 때문에
			// String 으로 형변환을 해줘야한다.
		}
		
		if(userId == null) {
			PrintWriter script = response.getWriter();
			script.println("<script>");
			script.println("alert('로그인이 필요합니다.')");
			script.println("location.href ='login.jsp'");
			script.println("</script>");
			script.close();
			return;
		}
		
		boolean emailChecked = dao.getUserEmailChecked(userId);
		//아마 회원 수정란에서 이메일 인증 눌렸을 때 
		if(emailChecked == true){
				PrintWriter script = response.getWriter();
				script.println("<script>");
				script.println("alert('이미 인증 된 회원입니다.')");
				script.println("location.href='index.jsp'");
				script.println("</script>");
				script.close();
				return;
		}
		String host = "http://localhost:8090/lectureEvaluation/";
		String from = "gunny6026@gmail.com";
		String to = dao.getUserEmail(userId);
		PrintWriter out1 = response.getWriter();
		out1.println("<script>");
		out1.println("console.log('to : '"+to+")");
		out1.println("</script>");
		String code = SHA256.getSHA256(to);
		String subject = "강의 평가를 위한 이메일 인증 메일입니다.";
		String content ="다음 링크에 접속하여 이메일 인증을 진행하세요" +	
		"<a href= '"+host+"emailCheckAction.jsp?code="+ code+"'>이메일 인증하기</a>";
		
		// 네이버 메일함에 오는 제목과 내용이다.
		Properties p = new Properties();
		p.put("mail.smtp.user", from);
		p.put("mail.smtp.host","smtp.googlemail.com");
		p.put("mail.smtp.port","456");
		p.put("mail.smtp.starttls.enable","true");
		p.put("mail.smtp.debug","true");
		p.put("mail.smtp.auth","true");
		p.put("mail.smtp.socketFactory.port","465");
		p.put("mail.smtp.socketFactory.class","javax.net.ssl.SSLSocketFactory");
		p.put("mail.smtp.socketFactory.fallback","false");
		
		try{
			Authenticator auth = new Gmail();
			Session ses = Session.getInstance(p,auth);
			ses.setDebug(true);
			MimeMessage msg = new MimeMessage(ses);
			msg.setSubject(subject);
			Address fromAddr = new InternetAddress(from);
			msg.setFrom(fromAddr);
			Address toAddr = new InternetAddress(to);
			msg.addRecipient(Message.RecipientType.TO, toAddr);
			msg.setContent(content,"text/html;charset=UTF-8");
			Transport.send(msg);
			
		}catch(Exception  e){
				 PrintWriter script = response.getWriter();
				script.println("<script>");
			/* 	script.println("alert('오류가 발생했습니다.')");
				script.println("history.back();"); */
				script.println("console.log("+e+")");
				script.println("</script>");
				script.close(); 
				return;
		}
	
		
%>
<%@ include file="/components/header.jsp" %>
<section class="container mt-5" style="max-width: 560px;">
	<div class="alert alert-success mt-4" role="alert">
		이메일 주소 인증 메일이 전송되었습니다. 회원가입시 입력했던 이메일에 들어가셔서 인증해주세요.
	</div>

	</section>


<footer class="bg-dark mt-4 p-5 text-center" style="color:#ffffff;">
		CopyRight &copy; 2020 박건희 All Rights Reserved.
	</footer>
</body>
</html>