# DB 연동

`기존의 DB연동`

```java
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


```java
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

```java
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