package board;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import utill.DB;
import utill.DatabaseUtill;

public class BoardDao {
	

	public int write(String userId , String title, String content) {
		Connection conn;
		PreparedStatement pstmt;
		ResultSet rs;
		
		String sql = "insert into board(userId, title, content, readCount, createDate) values(?,?,?,0, now() )";
		
		conn = DatabaseUtill.dbPool();
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, userId);
			pstmt.setString(2, title);
			pstmt.setString(3, content);
				System.out.println("글쓰기 완료?");
			return pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return -1;
	}
}
