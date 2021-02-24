package web;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.catalina.Session;

import com.google.gson.Gson;

import Service.BoardService;
import Service.LectureService;
import Service.UserService;
import board.Board;
import board.CommonRespDto;
import board.UpdateReqDto;
import lecture.Lecture;
import user.User;
import utill.GetIp;
import utill.SHA256;
import utill.Script;

/**
 * Servlet implementation class UserController
 */

// 요청 경로는 : /lectureEvaluation/board
// 맵핑 경로 : /board
@WebServlet("/lectureServlet")
public class LectureController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LectureController() {
        super();
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doProcess(request,response);
	
		
		
	}
	

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doProcess(request,response);
		/*
		 * sendRedirect는 필터를 거친다. 그래서 RequestDispatcher를 사용해야 한다.
		 */
		
		
		/*
		 * 왜냐하면 RequestDispathcher는 톰켓이 생성하는 request와 response를 재사용한다. 다시 접근하는게 아니라
		 * 내부적으로 움직인다.
		 * 
		 * sendRedirect는 다시 나갔다 들어와서 필터를 타야한다.
		 */
		}
	
	protected void doProcess(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			request.setCharacterEncoding("utf-8");
			response.setContentType("text/html; charset=utf-8");
			String cmd = request.getParameter("cmd");
			System.out.println("cmd 값은 : " +cmd);
			
			HttpSession session = request.getSession();
			LectureService lectureService = new LectureService();
			if(cmd.equals("register")) {
//				여기서 다시 세션값이 있는지 없는지 확인할 필요가 없다.
//				왜냐하면 이미 클릭 버튼 이벤트에서 세션값을 체크했고
//				만약 url을 통해 직접 요청한다고 해도 필터를 설정해서 괜찮다.
				String userId = null;	
				
				if( session.getAttribute("principal") != null ){
					 userId = (String) session.getAttribute("principal");
				}
				System.out.println("userId : " + userId);
				
				String lectureName = request.getParameter("lectureName");
				String professorName = request.getParameter("professorName");
				int lectureYear = Integer.parseInt(request.getParameter("lectureYear"));
				String semesterDivide = request.getParameter("semesterDivide");
				String lectureDivide = request.getParameter("lectureDivide");
				String evaluationTitle = request.getParameter("evaluationTitle");
				String evaluationContent = request.getParameter("evaluationContent");
				String totalscore = request.getParameter("totalscore");
				String joyscore = request.getParameter("joyscore");
				String lecturescore = request.getParameter("lecturescore");
				
				Lecture lecture = new Lecture();
				
				lecture.setLectureName(lectureName);
				lecture.setProfessorName(professorName);
				lecture.setLectureYear(lectureYear);
				lecture.setSemesterDivide(semesterDivide);
				lecture.setLectureDivide(lectureDivide);
				lecture.setEvaluationTitle(evaluationTitle);
				lecture.setEvaluationContent(evaluationContent);
				lecture.setTotalscore(totalscore);
				lecture.setJoyscore(joyscore);
				lecture.setLecturescore(lecturescore);
				
				
				
				int result = lectureService.강의평가등록(userId, lecture);
				if(result ==1) {
					PrintWriter script = response.getWriter();
					script.println("<script>");
					script.println("alert('강의평가가 등록 되었습니다.')");
					script.println("location.href ='/lectureEvaluation/index.jsp'");
					script.println("</script>");
					script.close();
				}else {
					Script.back(response, "강의 등록 실패");
				}
				
			}else if(cmd.equals("reportAction")) {
				String reportTitle = request.getParameter("reportTitle");
				String reportContent = request.getParameter("reportContent");
			}else if(cmd.equals("emailSend")) {
				RequestDispatcher dis = 
						request.getRequestDispatcher("emailSendAction.jsp");
				dis.forward(request, response);
			}else if(cmd.equals("like")) {
				String evaluationNo = request.getParameter("evaluationNo");
				String userId = null;
				if(session.getAttribute("principal")!=null) {
					userId = (String) session.getAttribute("principal");
				}
					GetIp ip = new GetIp();
				 String userIp = ip.getClientIp(request);
				 System.out.println("서블릿 호출 : " +evaluationNo+userId);
				 int result = lectureService.추천누르기(userId, evaluationNo, userIp);
				 if(result ==1) {
					 	PrintWriter script = response.getWriter();
						script.println("<script>");
						script.println("alert('추천이 완료되었습니다')");
						script.println("location.href='/lectureEvaluation/index.jsp'");
						script.println("</script>");
				 }else if(result ==-2) {
					 	PrintWriter script = response.getWriter();
						script.println("<script>");
						script.println("alert('이미 추천을 누른 글입니다.')");
						script.println("history.back()");
						script.println("</script>");
				 }else {
					 PrintWriter script = response.getWriter();
						script.println("<script>");
						script.println("alert('데이터 베이스 오류가 발생했습니다')");
						script.println("history.back()");
						script.println("</script>");
				 }
//				
			}else if(cmd.equals("delete")) {
				String evaluationNo = request.getParameter("evaluationNo");
				String userId = null;
				if(session.getAttribute("principal")!=null) {
					userId = (String) session.getAttribute("principal");
				}
				int result = lectureService.강의삭제하기(userId, evaluationNo);
				if(result ==1) {
					PrintWriter script = response.getWriter();
					script.println("<script>");
					script.println("alert('삭제가 완료되었습니다')");
					script.println("location.href='/lectureEvaluation/index.jsp'");
					script.println("</script>");
					script.close();
				}else if(result == -2) {
					PrintWriter script = response.getWriter();
					script.println("<script>");
					script.println("alert('권한이 없습니다.')");
					script.println("history.back()");
					script.println("</script>");
				}else {
					PrintWriter script = response.getWriter();
					script.println("<script>");
					script.println("alert('데이터 베이스 오류가 발생했습니다')");
					script.println("history.back()");
					script.println("</script>");
					script.close();
				}
				
			}
			
			
	}
	
	

}
