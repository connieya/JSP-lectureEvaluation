package test;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

//localhost:8090/lectureEvaluation/test(GET, POST)
@WebServlet("/test")
public class ApiServletTest extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public ApiServletTest() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html; charset=utf-8");
	
		PrintWriter out = response.getWriter();
		String reqUrl = request.getRequestURI();
		String mappingUrl = request.getServletPath();
		
		out.println("<html>");
		out.println("<head>");
		out.println("<title>HelloServlet</title>");
		out.println("</head>");
		out.println("<body>");
		out.println("<h1>HelloServlet</h1>");
		out.println("<h3>요청 경로는 : "+reqUrl+"</h3>");
		out.println("<h3>맵핑 경로는 : "+mappingUrl+"</h3>");
		out.println("</body>");
		out.println("</html>");
		out.close();
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("dopPost 메서드 호출");
		String food = request.getParameter("food");
		System.out.println(food);
		
		// DB에 insert 하고 끝
//		int result = 1;
//		PrintWriter out = response.getWriter();
//		out.println(result);
//		out.flush(); 
		
		int result = 1;
		PrintWriter out = response.getWriter();
		if(result ==1) {
			out.println("{\"food\" :\" "+food+"\" }");
		}else {
			out.println("{\"error\" :\"fail\"}");
		}
		
		out.flush();
		
	}
	// text/plain
	// 응답 결과 1 : 정상 2: 실패
	
//	응답데이터
//	application/json
//	응답결과 : DB에 insert된 레코드
//	(food)
}


// 	요청주소
// 	localhost:8080/lectureEvaluation/test
// 	
// 	요청 메서드
// 	POST   ( 음식레시피를 저장) ,GET (음식 메뉴 응답)
// 	
// 	요청데이터
// 	키 food
// 	값 soup , noodle
//  키 method
//  값 recipe
//	요청 데이터의 Content-type  : x-www-form-urlencoded
//	(이것이 필요 없으면 query string으로 보낸다는 말이다.) 
// 응답 데이터
//	text/html  or application/json or text/plain 
