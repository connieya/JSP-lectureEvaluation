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
