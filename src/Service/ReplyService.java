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
		
		return dao.register(dto);
	}
	
	public int 댓글삭제 () {
		
		return -1;
	}

}
