package Service;

import java.util.List;

import board.Board;
import board.BoardDao;
import board.UpdateReqDto;

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
//		글 상세보기 할 때 필요한 것이 board에 대한 정보와
//		유저 아이디와 이름 , 그리고 댓글 목록이기 때문에
//		여기서 모든 객체를 다 받아줘야한다.
//		그리고 하나의 dto에 담아서 리턴해줘야한다.
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
	// json 데이터로 보낼 때 
	public int 글삭제하기2(int bno, String sessionValue, String userId) {
		System.out.println("글삭제하기2");
		if(sessionValue == null) {
			return -1;
		}else if(!sessionValue.equals(userId)) {
			return 0;
		}else {
			
			return dao.delete(bno);
		}
		
		
	}
	
	
	public int 글수정하기(UpdateReqDto dto) {
		System.out.println("글 수정하기");
		return dao.update(dto);
	}


}
