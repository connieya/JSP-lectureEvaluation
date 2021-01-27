package board;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import utill.DB;
import utill.DatabaseUtill;

public class BoardDao {
	
	Connection conn;
	PreparedStatement pstmt;
	ResultSet rs;

	public int write(String userId , String title, String content) {
		
		
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
		}finally {
			DB.close(conn, pstmt, rs);
		}
		
		return -1;
	}
	
	public List<Board> list() {
		System.out.println("boardlist !!");
		
		
		String sql = "select * from board order by bno desc";
		
		conn = DatabaseUtill.dbPool();
		ArrayList<Board> boardlist = new ArrayList<Board>();
		try {
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				Board board = new Board();
				board.setBno(rs.getInt(1));
				board.setUserId(rs.getString(2));
				board.setTitle(rs.getString(3));
				board.setContent(rs.getString(4));
				board.setReadCount(rs.getInt(5));
				board.setCreateDate(rs.getTimestamp(6));
				
				boardlist.add(board);	
			}
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			DB.close(conn, pstmt, rs);
		}
		
		return boardlist;
	}
	
	public Board detail(int bno) {
		
		
		String sql = "select * from board where bno =?";
		Connection conn;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		conn = DatabaseUtill.dbPool();
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, bno);
			rs = pstmt.executeQuery();
			Board board = new Board();
			if(rs.next()) {
				board.setBno(rs.getInt("bno"));
				board.setUserId(rs.getString("userId"));
				board.setTitle(rs.getString("title"));
				board.setContent(rs.getString("content"));
				board.setReadCount(rs.getInt("readCount"));
				board.setCreateDate(rs.getTimestamp("createDate"));
				
				return board;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			DB.close(conn, pstmt, rs);
		}
		
		return null;
		
	}
	
	public int delete(int bno) {
		
		Connection conn;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		String sql = "delete from board where bno = ?";
		
		conn = DatabaseUtill.dbPool();
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, bno);
		
			
			return pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			DB.close(conn, pstmt, rs);
		}
		
		return -1;
	
		
	}
	// 조회수 증가 함수 
	public int readCount(int bno) {
		
		String sql = "update board set readCount = readCount+1 where bno =?";
		
		conn = DatabaseUtill.dbPool();
		
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, bno);
			
			
				return pstmt.executeUpdate();
			
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			DB.close(conn, pstmt);
		}
		return -1;
	}
}
