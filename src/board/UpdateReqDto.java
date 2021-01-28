package board;

import lombok.Data;


// 글수정 할 때 필요한 DTO ( Data Transfer Object)
@Data
public class UpdateReqDto {
	
	private int bno;
	private String title;
	private String content;

}
