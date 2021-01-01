package user;

import java.sql.Connection;
import java.sql.PreparedStatement;

import utill.DatabaseUtill;

public class UserDAO {

	private PreparedStatement pstmt ;
	
	//회원가입
	public int join(User user) {
		String sql = "insert into user(userId,userName,userPassword,userEmail) values(?,?,?,?)";
		try {
			Connection conn = DatabaseUtill.dbPool();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, user.getUserId());
			pstmt.setString(2,user.getUserName());
			pstmt.setString(3,user.getUserPassword());
			pstmt.setString(4,user.getUserEmail());
			
			System.out.println("회원가입  성공");
			return pstmt.executeUpdate();
		}catch(Exception e){
			System.out.println("회원가입 실패");
			e.printStackTrace();
			
		}
		
		return -1;
		
	}

	public int login(String userId, String userPassword) {
		String sql = "insert into user values(?,?)";
		try {
			Connection conn = DatabaseUtill.dbPool();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, userId);
			pstmt.setString(2,userPassword);
			
			System.out.println("로그인 성공");
			return pstmt.executeUpdate();
		}catch(Exception e){
			System.out.println("로그인 실패");
			e.printStackTrace();
			
		}
		
		return -1;
		
	}
}
