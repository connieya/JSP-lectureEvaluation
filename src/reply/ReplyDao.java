package reply;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

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
	
	public List<Reply> findAll(int bno) {
		String sql = "select * from reply where bno =?";
		conn = DatabaseUtill.dbPool();
		
		List<Reply> replys = new ArrayList<>();
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, bno);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				Reply reply = new Reply();
				reply.setBno(rs.getInt("bno"));
				reply.setRno(rs.getInt("rno"));
				reply.setContent(rs.getString("content"));
				reply.setUserName(rs.getString("userName"));
				reply.setCreateDate(rs.getTimestamp("createDate"));
				
				replys.add(reply);
				
			}
			return replys;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return null;
	}

}
