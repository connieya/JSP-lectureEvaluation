package reply;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import utill.DB;
import utill.DatabaseUtill;

public class ReplyDao {
	
	Connection conn;
	PreparedStatement pstmt;
	ResultSet rs;

	public int register(SaveReqDto dto) {
		
		
		String sql = "insert into reply(userName, bno, content, createDate) values(?,?,?, now() )";
		
		conn = DatabaseUtill.dbPool();
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, dto.getUserName());
			pstmt.setInt(2, dto.getBno());
			pstmt.setString(3, dto.getContent());
				System.out.println("댓글 쓰기 완료?");
			return pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			DB.close(conn, pstmt);
		}
		
		return -1;
	}

}
