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
import Service.ReplyService;
import Service.UserService;
import board.Board;
import board.CommonRespDto;
import board.UpdateReqDto;
import reply.Reply;
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
			String cmd = request.getParameter("cmd");
			HttpSession session = request.getSession();
			
			System.out.println("cmd 값은 : " +cmd);
			
			BoardService boardService = new BoardService();
			UserService userService = new UserService();
			ReplyService replyService = new ReplyService();
			
			
			
			if(cmd.equals("boardList")) {
				//response.sendRedirect("/lectureEvaluation/board/boardForm.jsp");
				 List<Board> boards = boardService.글목록보기();
				 request.setAttribute("boards", boards);
				
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
					out.println("location.href ='/lectureEvaluation/boardServlet?cmd=boardList'");
					out.println("</script>");
					
					
				}else {
					Script.back(response, "글등록 실패");
				}
			}else if(cmd.equals("boardForm")) {
				RequestDispatcher dis =
						request.getRequestDispatcher("board/boardForm.jsp");
				
				dis.forward(request, response);
			}else if(cmd.equals("detail")) {
				int bno = Integer.parseInt(request.getParameter("bno"));
				
				/*
				 * 사실 아래와 같이 세션 값을 체크 할 필요가 없다. 그냥 boardDetail.jsp 에서 sessionScope.principal 값을
				 * 바로 체크하면 되는데, 내가 세션 값을 설정할 때 user 객체로 지정하지않고 userId로 지정을 해서 아래와 같이 user 객체를
				 * 넘겨주었다.
				 */
				String userId = null;
				if(session.getAttribute("principal") != null) {
					userId = (String) session.getAttribute("principal");
					User user = new User();
					user = userService.유저정보가져오기(userId);
					System.out.println("유저 세션의 no : " + user.getUserNo());
					request.setAttribute("userObject", user);
				}
				
				
				
				System.out.println("bno :" +bno);
				Board detail = new Board();
				detail = boardService.글상세보기(bno);
				List<Reply> replys = replyService.댓글목록보기(bno);
				// 글 상세보기 화면에 댓글 목록도 나와야한다.
				if(detail == null) {
					Script.back(response, "글 상세보기 실패");
				}else {
				request.setAttribute("detail", detail);
				request.setAttribute("replys", replys);
				// 댓글에 대한 데이터도 글 상세보기 페이지에 필요하다.
				RequestDispatcher dis =
						request.getRequestDispatcher("board/boardDetaill.jsp");
				
				dis.forward(request, response);
				}
			}else if(cmd.equals("image")) {
				String uploadPath = "C:\\summernote\\image";
				
				String fileName = "";
				
				try {
				
				}catch(Exception e){
					e.printStackTrace();
					
				}
				
			}else if(cmd.equals("delete")) {
				int bno = Integer.parseInt(request.getParameter("bno"));
				
				Board board = boardService.글상세보기(bno);
				String userId = board.getUserId();
				
				System.out.println("userId : " + userId);
				
				 session = request.getSession();
				String sessionValue = null;
				if(session.getAttribute("principal") != null) {
					sessionValue = (String) session.getAttribute("principal");
					
				}
				System.out.println("session :" + sessionValue);
				if( sessionValue == null){
	
					Script.back(response, "권한이 없습니다.");
				}else if(!userId.equals(sessionValue)) {
					
					
					Script.back(response, "글 작성자만 삭제할 수있습니다.");
				}else{
					int result = boardService.글삭제하기(bno);
					if(result ==1) {
						PrintWriter out = response.getWriter();
						out.println("<script>");
						out.println("alert('글 삭제가 완료되었습니다.')");
						out.println("location.href ='/lectureEvaluation/boardServlet?cmd=boardList'");
						out.println("</script>");
						
					}else {
						Script.back(response, "글 삭제 실패!!");
					}
				}
			}else if(cmd.equals("delete1")) {
				int bno = Integer.parseInt(request.getParameter("bno"));
				
				Board board = boardService.글상세보기(bno);
				String userId = board.getUserId();
				
				 session = request.getSession();
				String sessionValue = null;
				if(session.getAttribute("principal") != null) {
					sessionValue = (String) session.getAttribute("principal");	
				}
				
				int result = boardService.글삭제하기2(bno, sessionValue , userId);
				
				//응답할 json 데이터 생성
				CommonRespDto<String> RespDto = new CommonRespDto<>();
				RespDto.setStatusCode(result);
				RespDto.setData("성공");
				
				Gson gson = new Gson();
				// 응답해줄 ajax에서 dataType : "json"으로 설정했기 때문에 
				//결과값을 json으로 변환해줘야함
				String resultData = gson.toJson(RespDto);
				System.out.println("resultData" +resultData);
				PrintWriter out = response.getWriter();
				out.print(resultData);
				out.flush();
		
				
			}else if(cmd.equals("update")) {
				int bno = Integer.parseInt(request.getParameter("bno"));
				
				
				String title = request.getParameter("title");
				String content = request.getParameter("content");
				UpdateReqDto dto = new UpdateReqDto();
				dto.setBno(bno);
				dto.setTitle(title);
				dto.setContent(content);
				System.out.println("title :" +title);
				System.out.println("Dto : "+ dto);
				int result = boardService.글수정하기(dto);
				System.out.println("result : " +result);
				if(result == 1) {
				PrintWriter out = response.getWriter();
				out.println("<script>");
				out.println("alert('글 수정이 완료되었습니다.')");
				out.print("location.href='/lectureEvaluation/boardServlet?cmd=detail&bno="+bno+"'");
				out.println("</script>");
					
				}else {
					Script.back(response, "글수정 실패");
				}
			}else if(cmd.equals("updateForm")) {
				int bno = Integer.parseInt(request.getParameter("bno"));
				Board board = boardService.글상세보기(bno);
				String userId = board.getUserId();
				 session = request.getSession();
				String sessionValue = null;
				
				request.setAttribute("board", board);
				if(session.getAttribute("principal") != null) {
					sessionValue = (String) session.getAttribute("principal");	
				}
				
				if(sessionValue == null) {
					Script.back(response, "로그인이 필요합니다.");
				}else if(!sessionValue.equals(userId)) {
					Script.back(response, "권한이 없습니다.");
				}else {
					
					RequestDispatcher dis =
							request.getRequestDispatcher("board/boardUpdate.jsp");
					
					dis.forward(request, response);
				}
				
				
			}
			
	}
	
	

}
