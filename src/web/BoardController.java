package web;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.catalina.Session;

import Service.BoardService;
import Service.UserService;
import user.User;
import utill.SHA256;
import utill.Script;

/**
 * Servlet implementation class UserController
 */

// 요청 경로는 : /lectureEvaluation/board
// 맵핑 경로 : /board
@WebServlet("/boardServlet")
public class BoardController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public BoardController() {
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
	
	//http://localhost:8080/lectureEvaluaiton/board?cmd=''''
	protected void doProcess(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			request.setCharacterEncoding("utf-8");
			response.setContentType("text/html; charset=utf-8");
			System.out.println("doProcess 호출");
			String cmd = request.getParameter("cmd");
			
			
			System.out.println("cmd 값은 : " +cmd);
			
			BoardService boardService = new BoardService();
			
			if(cmd.equals("boardList")) {
				//response.sendRedirect("/lectureEvaluation/board/boardForm.jsp");

				RequestDispatcher dis =
						request.getRequestDispatcher("board/boardList.jsp");
				
				dis.forward(request, response);
			}else if(cmd.equals("boardRegister")) {
				String userId =  request.getParameter("userId");
				String title = request.getParameter("title");
				String content = request.getParameter("content");
				
				int result = boardService.글쓰기(userId,title, content);
				if(result == 1) {
					System.out.println("글쓰기 진짜 성공");
					PrintWriter out = response.getWriter();
					out.println("<script>");
					out.println("alert('글쓰기가 완료되었습니다.')");
					out.println("location.href ='/lectureEvaluation/board/boardList.jsp'");
					out.println("</script>");
					
					
				}else {
					Script.back(response, "글등록 실패");
				}
			}
			
	}
	
	

}
