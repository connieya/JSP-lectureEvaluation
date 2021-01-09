<%@page import="javax.mail.internet.InternetAddress"%>
<%@page import="javax.mail.Message"%>
<%@page import="javax.mail.Address"%>
<%@page import="javax.mail.Transport"%>
<%@page import="javax.mail.internet.MimeMessage"%>
<%@page import="javax.mail.Session"%>
<%@page import="utill.Gmail"%>
<%@page import="javax.mail.Authenticator"%>
<%@page import="java.util.Properties"%>
<% request.setCharacterEncoding("UTF-8"); %>
<%@page import="lecture.LectureDAO"%>
<%@page import="java.io.PrintWriter"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../components/header.jsp" %>
	<% 
		String userId = null;
		if(session.getAttribute("principal") != null){
			userId = (String) session.getAttribute("principal");
		}
		
		if(userId == null){
			PrintWriter script = response.getWriter();
			script.println("<script>");
			script.println("alert('로그인이 필요한 서비스입니다.')");
			script.println("location.href='/lectureEvaluation/user/login.jsp'");
			script.println("</script>");
		}
		
		String reportTitle = null;
		String reportContent = null;
		
		if(request.getParameter("reportTitle") !=null){
			
			reportTitle = request.getParameter("reportTitle");
				
		}
		

		if(request.getParameter("reportContent") !=null){
		reportContent = request.getParameter("reportContent");
		}
		
		if(reportTitle == null || reportContent == null){
			PrintWriter script = response.getWriter();
			script.println("<script>");
			script.println("alert('입력이 안된 부분이 있습니다.')");
			script.println("history.back();");
			script.println("</script>");
		}
		
		
		String host = "http://localhost:8090/lectureEvaluation/";
		String from = "gunny6026@gmail.com";
		String to = "gunny6026@naver.com";
		String subject = "강의평가 사이트에서 접수된 신고가 있습니다.";
		String content ="신고자 : "+userId +	
						"<br/> 제목 : "+reportTitle+
						"<br/> 내용 : "+reportContent; 
		
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
				script.println("alert('오류가 발생했습니다.')");
				script.println("history.back();");
				script.println("</script>");
				script.close();
				return;
		}
	
		PrintWriter script = response.getWriter();
		script.println("<script>");
		script.println("alert('정상적으로 신고 되었습니다.')");
		script.println("location.href='../index.jsp'");
		script.println("</script>");
		script.close();
		return;
	%>