package board;

import lombok.Data;

@Data
public class CommonRespDto<T> {
			// 제너럴 타입

	private int statusCode ; //1 , -1
	private T data;
}
