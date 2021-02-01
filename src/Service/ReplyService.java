package Service;

import java.util.List;

import reply.Reply;
import reply.ReplyDao;
import reply.SaveReqDto;

public class ReplyService {
	
	private ReplyDao dao;
	
	public ReplyService() {
		dao = new ReplyDao();
	}
	public List<Reply> 댓글목록보기(int bno){
		
		return dao.findAll(bno);
		
	}
	
	public int 댓글쓰기(SaveReqDto dto) {
		
		return dao.register(dto); // 결과가 1일 때 댓글번호(rno) 값
	}
	
	public int 댓글삭제 (int rno) {
		
		
		return dao.delete(rno);
	}
	
	public Reply 댓글찾기(int rno) {
		
		return dao.findById(rno);
		
	}

}
