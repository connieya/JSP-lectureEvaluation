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
		String sql = "insert into user(userId,userName,userPassword,userEmail,userEmailHash,userEmailChecked) values(?,?,?,?,?,false)";
		try {
			Connection conn = DatabaseUtill.dbPool();
			PreparedStatement pstmt ;
			ResultSet rs;
			System.out.println("user 객체:"+user);
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, user.getUserId());
			pstmt.setString(2,user.getUserName());
			pstmt.setString(3,user.getUserPassword());
			pstmt.setString(4,user.getUserEmail());
			pstmt.setString(5, user.getUserEmailHash());
			System.out.println("이메일 해쉬값 : " + user.getUserEmailHash());
			System.out.println("회원가입  성공");
			
			
			return  pstmt.executeUpdate();
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
			System.out.println("getUserEmail 메서드 호출");
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
	// 유저 정보 가져오기
	public User getUser(String pr) {
		System.out.println("유저 정보 가져오기");
		String sql = "select * from user where userId = ?";
		System.out.println("Sss");
		System.out.println("pr 값은 :"+ pr);
		User user = new User();
		try {
			conn= DatabaseUtill.dbPool();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1,pr);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				user.setUserName(rs.getString(3));
				user.setUserId(rs.getString(2));
				user.setUserEmail(rs.getString(5));
				user.setUserPassword(rs.getString(4));
				System.out.println("rs next");
				System.out.println("DB 2번 항목 : "+ rs.getString(2));
				System.out.println("DB 4번 항목 : "+rs.getString(4));
				return user;
			}else {
				System.out.println("해당 유저 정보가 없습니다");
				return null;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
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
	
	//회원 탈퇴
	public int 회원탈퇴(String userId) {
		System.out.println("회원탈퇴 메서드 호출");
		System.out.println("userId : " +userId);
		String sql = "delete from user where userId = ?";
		
		conn = DatabaseUtill.dbPool();
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, userId);
			
			return pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
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
		
		return -1;
	}
	
	
	//회원수정
	public int 회원수정(String userId, String userName, String userPassword) {
		System.out.println("회원수정 메서드 호출");
		System.out.println("userId : " +userId);
		String sql = "update user set userName =? , userPassword =?  where userId = ?";
		
		conn = DatabaseUtill.dbPool();
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, userName);
			pstmt.setString(2, userPassword);
			pstmt.setString(3, userId);
			
			return pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
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
		
		return -1;
	}
}
