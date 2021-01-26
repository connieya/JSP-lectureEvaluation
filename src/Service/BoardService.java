package Service;

import board.BoardDao;

public class BoardService {
	
	
	public int 글쓰기(String userId , String title , String content) {
		System.out.println("글쓰기 로직 dao 호출");
		BoardDao dao = new BoardDao();
		int result = dao.write(userId, title,content);
		
		return result;
		
	}
	
	public void 글상세보기() {
		
		
	}
	
	public void 글삭제하기() {
		
		
	}
	
	public void 글수정하기() {
		
	}


}
