package Service;

import reply.ReplyDao;
import reply.SaveReqDto;

public class ReplyService {
	
	private ReplyDao dao;
	
	public ReplyService() {
		dao = new ReplyDao();
	}
	
	public int 댓글쓰기(SaveReqDto dto) {
		
		return dao.register(dto);
	}
	
	public int 댓글삭제 () {
		
		return -1;
	}

}
