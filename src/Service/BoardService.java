package Service;

import java.util.List;

import board.Board;
import board.BoardDao;

public class BoardService {
	private BoardDao dao;
	
	public BoardService() {
	 dao = new BoardDao();
	}
	
	public int 글쓰기(String userId , String title , String content) {
		
		int result = dao.write(userId, title,content);
		
		return result;
		
	}
	public List<Board> 글목록보기() {
		
		
		List<Board> board = dao.list();
		return board;
		
	}
	// 하나의 서비스 안에 여러가지 DB관련 로직이 섞여있기때문에 
	// service를 사용한다.
	public Board 글상세보기(int bno) {
		
		// 조회수 증가 & 상세보기 값 두개 다 오류가 발생하지 않아야함
		int result  = dao.readCount(bno);
		if(result ==1) {
			return dao.detail(bno);
			
		}else {
			return null;
		}
		
		
		
		
	}
	
	public int 글삭제하기(int bno) {
		System.out.println("글삭제하기 ");
		
		return dao.delete(bno);
		
	}
	
	public void 글수정하기() {
		
	}


}
