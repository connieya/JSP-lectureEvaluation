# 강의평가 사이트

-------------------------------

## header.jsp

페이지를 전환하는데 오류가 발생하였는데,
<br/>
상대경로 -> 절대경로 수정 후 <br/>
디버깅 성공!! <br/>


`상대 경로`

```
<ul class="navbar-nav">
    <li class="nav-item active">
      <a class="nav-link" href="../index.jsp">Home</a>
    </li>
    <li class="nav-item">
      <a class="nav-link" href="user/join.jsp">회원가입</a>
    </li>
    <li class="nav-item">
      <a class="nav-link" href="../user/login.jsp">로그인</a>
    </li>
    <li class="nav-item">
      <a class="nav-link disabled" href="#">Disabled</a>
    </li>
  </ul>
```
`절대 경로`
```
<ul class="navbar-nav">
    <li class="nav-item active">
      <a class="nav-link" href="/lectureEvaluation/index.jsp">Home</a>
    </li>
    <li class="nav-item">
      <a class="nav-link" href="/lectureEvaluation/user/join.jsp">회원가입</a>
    </li>
    <li class="nav-item">
      <a class="nav-link" href="/lectureEvaluation/user/login.jsp">로그인</a>
    </li>
    <li class="nav-item">
      <a class="nav-link disabled" href="#">Disabled</a>
    </li>
  </ul>

```


-----------------------------------
## DB 연동 ( DataBase Pool 사용)

`기존의 DB연동`

```
public UserDAO() {
		try {
			String url = "jdbc:mysql://localhost:3306/pgh";
			String id = "root";
			String pw = "1234";
			
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection(url,id,pw);
			System.out.println("db 연동 성공");
		}catch(Exception e) {
			System.out.println("db 연동 실패");
			e.printStackTrace();
		}
	
	}
    
    
    String sql = "select password from user where id = ?";
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, userID);
			rs = pstmt.executeQuery();

```


```
Connection connection;
	PreparedStatement pstmt;
	ResultSet rs;
	
	String driver = "com.mysql.jdbc.Driver";
	String url = "jdbc:mysql://localhost:3306/cony";
	String user = "root";
	String pw = "1234";
	
	public BoardDao() {
	
	try {
		//Context context = new InitialContext();
		//datasource = (DataSource) context.lookup("javax.sql.DataSource");
		Class.forName(driver);
		 connection = DriverManager.getConnection(url,user,pw);
		
	}catch(Exception e){
		e.printStackTrace();
	}
```


`DB Pool 사용`

```
package utill;

import java.sql.Connection;
import java.sql.DriverManager;

public class DatabaseUtill {
	
	public static Connection dbPool() {
		
	try {
		Connection conn;
		String url ="jdbc:mysql://localhost:3306/lecture";
		String id = "root";
		String pw = "1234";
		Class.forName("com.mysql.jdbc.Driver");
		conn = DriverManager.getConnection(url,id,pw);
		System.out.println("DB연동 성공");
		return conn;
	}catch(Exception e) {
		e.printStackTrace();
		System.out.println("DB연동 실패");
	}
	
	return null;

}
}


----------------------------
UserDAO.java

public int join(User user) {
		String sql = "insert into user(userId,userName,userPassword,userEmail) values(?,?,?,?)";
		try {
			Connection conn = DatabaseUtill.dbPool();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, user.getUserId());
			pstmt.setString(2,user.getUserName());
			pstmt.setString(3,user.getUserPassword());
			pstmt.setString(4,user.getUserEmail());
```
-----------------------------------

## 회원가입

null vs ""

```
if(userId == "" || userPassword =="" || userEmail=="" || userName =="") {
		PrintWriter script = response.getWriter();
		script.println("<script>");
		script.println("alert('입력이 안 된 사항이 있습니다.')");
		script.println("history.back();");
		script.println("</script>");
		script.close();
		return;
```

null = no vaule <br/>
"" = empty ( 0 length string)


----------------------------------------
## XSS 공격 방어

![image](https://user-images.githubusercontent.com/66653324/104120842-49da6900-537d-11eb-8733-0082787fa113.png)

강의 등록 할 때 <script> 태그를 넣어 사이트를 공격할 수 있다.
<br/> XSS 공격에 방어하기 위해 <script> 태그를 다른 문자로 치환해서 방어해보자 <br/>

```
			pstmt = conn.prepareStatement(sql);
			pstmt.setNString(1, userId);
			pstmt.setNString(2, lecture.getLectureName().replaceAll("<", "&alt;").replaceAll(">", "&gt;").replaceAll("\r\n","<br>"));
			pstmt.setNString(3, lecture.getProfessorName().replaceAll("<", "&alt;").replaceAll(">", "&gt;").replaceAll("\r\n","<br>"));
			pstmt.setInt(4, lecture.getLectureYear());
			pstmt.setNString(5, lecture.getSemesterDivide());
			pstmt.setNString(6, lecture.getLectureDivide());
			pstmt.setNString(7, lecture.getEvaluationTitle().replaceAll("<", "&alt;").replaceAll(">", "&gt;").replaceAll("\r\n","<br>"));
			pstmt.setNString(8, lecture.getEvaluationContent().replaceAll("<", "&alt;").replaceAll(">", "&gt;").replaceAll("\r\n","<br>"));
			pstmt.setNString(9, lecture.getTotalscore());
			pstmt.setNString(10, lecture.getLecturescore());
			pstmt.setNString(11, lecture.getJoyscore());
			
```
