package Service;

import java.util.List;

import board.Board;
import board.BoardDao;

public class BoardService {
	
	
	public int 글쓰기(String userId , String title , String content) {
		System.out.println("글쓰기 로직 dao 호출");
		BoardDao dao = new BoardDao();
		int result = dao.write(userId, title,content);
		
		return result;
		
	}
	public List<Board> 글목록보기() {
		
		BoardDao dao = new BoardDao();
		List<Board> board = dao.list();
		System.out.println("board 객체 : " + board);
		return board;
		
	}
	
	public void 글상세보기() {
		
		
	}
	
	public void 글삭제하기() {
		
		
	}
	
	public void 글수정하기() {
		
	}


}
