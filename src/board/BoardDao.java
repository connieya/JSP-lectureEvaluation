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
	

	public int write(String userId , String title, String content) {
		Connection conn;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
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
		Connection conn;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		String sql = "select * from board order by bno desc";
		
		conn = DatabaseUtill.dbPool();
		ArrayList<Board> boardlist = new ArrayList<Board>();
		try {
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			Board board = new Board();
			while(rs.next()) {
				board.setBno(rs.getInt(1));
				board.setUserId(rs.getString(2));
				board.setTitle(rs.getString(3));
				board.setContent(rs.getString(4));
				board.setReadCount(rs.getInt(5));
				board.setCreateDate(rs.getTimestamp(6));
				
				System.out.println("rs.getInt : "+ rs.getInt(1));
				System.out.println("rs : " + rs);
				boardlist.add(board);	
			}
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			DB.close(conn, pstmt, rs);
		}
		
		System.out.println( "boardlist: " +boardlist);
		return boardlist;
	}
}
