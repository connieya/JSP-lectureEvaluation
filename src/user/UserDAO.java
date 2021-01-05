package user;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import utill.DatabaseUtill;

public class UserDAO {

	private PreparedStatement pstmt ;
	private ResultSet rs;
	private Connection conn;
	
	//회원가입
	public int join(User user) {
		String sql = "insert into user values(?,?,?,?,?,false)";
		try {
			Connection conn = DatabaseUtill.dbPool();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, user.getUserId());
			pstmt.setString(2,user.getUserName());
			pstmt.setString(3,user.getUserPassword());
			pstmt.setString(4,user.getUserEmail());
			pstmt.setString(5, user.getUserEmailHash());
			
			System.out.println("회원가입  성공");
			return pstmt.executeUpdate();
		}catch(Exception e){
			System.out.println("회원가입 실패");
			e.printStackTrace();
			
		}
		
		return -1;
		
	}
	//로그인
	public int login(User user) {
		String sql = "select userPassword from user where userId =?";
		try {
			 conn = DatabaseUtill.dbPool();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, user.getUserId());
			
			rs = pstmt.executeQuery(); //select 할 때
			if(rs.next()) {
				if(rs.getString(1).equals(user.getUserPassword())) {
					System.out.println("로그인 성공");
					return 1;
				}else {
					System.out.println("비밀번호가 틀렸습니다.");
					return 0;
				}
				
			}else {
				System.out.println("아이디 없음");
				return -1;
			}
			
		}catch(Exception e){
			System.out.println("로그인 실패");
			e.printStackTrace();
			
		}finally {
			try {
				if(rs != null) rs.close();
				if(pstmt != null) pstmt.close();
				if(conn != null) conn.close();
		}catch(Exception e1) {
			e1.printStackTrace();
		}
		}
		
		return -2; // 데이터 베이스 오류
		
	}
	
	//이메일 인증 (인증이 완료된 사람만 강의평가 할 수 있음)
	//이메일 인증이 되었는지 확인
	public boolean getUserEmailChecked(String userId) {
		String sql ="select userEmailChecked from user where userId=?";
		
		try {
			conn = DatabaseUtill.dbPool();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, userId);
			
			rs = pstmt.executeQuery();
			if(rs.next()) {
				return rs.getBoolean(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			try {
				if(rs != null) rs.close();
				if(pstmt != null) pstmt.close();
				if(conn != null) conn.close();
		}catch(Exception e1) {
			e1.printStackTrace();
		}
		}
		
		return false;
		
	}
	// 이메일 인증 시켜줌
	// 이메일 인증 수행
	public boolean setUserEmailChecked(String userId) {
		String sql ="update user set userEmailChecked =  true where userId=?";
		
		try {
			conn = DatabaseUtill.dbPool();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, userId);	
			pstmt.executeUpdate();
			
			return true;
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			try {
				if(rs != null) rs.close();
				if(pstmt != null) pstmt.close();
				if(conn != null) conn.close();
		}catch(Exception e1) {
			e1.printStackTrace();
		}
	}
		
		return false;
		
	}
	
	public String getUserEmail(String userId) {
		String sql ="select userEmail from user where userId=?";
		
		try {
			conn = DatabaseUtill.dbPool();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, userId);	
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				System.out.println("이메일 호출");
				return rs.getString(1);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			try {
				if(rs != null) rs.close();
				if(pstmt != null) pstmt.close();
				if(conn != null) conn.close();
		}catch(Exception e1) {
			e1.printStackTrace();
		}
	}
		
		return null;
	}
}
