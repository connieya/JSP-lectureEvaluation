package reply;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import utill.DB;
import utill.DatabaseUtill;

public class ReplyDao {
	
	Connection conn;
	PreparedStatement pstmt;
	ResultSet rs;

	public int register(SaveReqDto dto) {
		
		
		String sql = "insert into reply(userName, bno, content, createDate ,userNo) values(?,?,?, now() ,?)";
		int generateKey;
		conn = DatabaseUtill.dbPool();
		try {
								// executeUpdate를 했는데도 rs로 결과를 받을 수 있다.
			pstmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			pstmt.setString(1, dto.getUserName());
			pstmt.setInt(2, dto.getBno());
			pstmt.setString(3, dto.getContent());
			pstmt.setInt(4, dto.getUserNo());
			int result = pstmt.executeUpdate();
			rs = pstmt.getGeneratedKeys(); //primary key를 받는다.
			if(rs.next()) {
				generateKey = rs.getInt(1);
				System.out.println("생성된 키(댓글번호) : " + generateKey);
				if(result==1) {
					return generateKey;
					// AutoIncrement로 설정된 rno 값을 리턴하는거다.
				}
			}
					
			
			
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
				reply.setUserNo(rs.getInt("userNo"));
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
		}finally {
			DB.close(conn, pstmt ,rs);
		}
		
		return null;
	}
	
	
	public Reply findById(int rno) {
		String sql = "select * from reply where rno =?";
		conn = DatabaseUtill.dbPool();
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, rno);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				Reply reply = new Reply();
				reply.setBno(rs.getInt("bno"));
				reply.setRno(rs.getInt("rno"));
				reply.setContent(rs.getString("content"));
				reply.setUserName(rs.getString("userName"));
				reply.setCreateDate(rs.getTimestamp("createDate"));
				
				
				
				return reply;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			DB.close(conn, pstmt, rs);
		}
		
		return null;
	}
	
	public int delete(int rno) {
		String sql = "delete from reply where rno = ?";
	
		conn = DatabaseUtill.dbPool();
		try {
								// executeUpdate를 했는데도 rs로 결과를 받을 수 있다.
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, rno);
			return pstmt.executeUpdate();
				
		}catch (SQLException e) {
			e.printStackTrace();
		}finally {
			DB.close(conn, pstmt);
		}
		
		return -1;
	}

}
