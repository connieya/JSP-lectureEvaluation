package like;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import utill.DatabaseUtill;

public class LikeDAO {
	
	Connection conn;
	PreparedStatement pstmt;
	ResultSet rs;
	
	public int like(String userId, String evaluationNo, String userIP) {
		String sql = "insert into likey values(?,?,?)";
		try {
			 conn = DatabaseUtill.dbPool();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, userId);
			pstmt.setString(2,evaluationNo);
			pstmt.setString(3,userIP);
			
			
			return  pstmt.executeUpdate();
		}catch(Exception e){
			System.out.println("오류 발생 추천 실패");
			e.printStackTrace();
			
		}finally {
			try {
			
				if(pstmt != null) pstmt.close();
				if(conn != null) conn.close();
		}catch(Exception e1) {
			e1.printStackTrace();
		}
		}
		
		return -1; //추천 중복 오류
	}

}
