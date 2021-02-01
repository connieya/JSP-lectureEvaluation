package reply;

import lombok.Data;

@Data
public class SaveReqDto {

	private int bno;
	private int userNo;
	private String userName;
	private String content;
	
}
