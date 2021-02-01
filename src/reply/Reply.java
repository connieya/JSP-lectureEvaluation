package reply;

import java.sql.Timestamp;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Reply {
	private int rno;
	private int userNo;
	private String userName;
	private int bno;
	private String content;
	private Timestamp createDate;
}
