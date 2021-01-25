package board;

import java.sql.Timestamp;


public class Board {
	private int bno;
	private int userId;
	private String title;
	private String content;
	private int readCount; 
	private Timestamp createDate;
	
	public Board() {
		// TODO Auto-generated constructor stub
	}

	public int getBno() {
		return bno;
	}

	public void setBno(int bno) {
		this.bno = bno;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public int getReadCount() {
		return readCount;
	}

	public void setReadCount(int readCount) {
		this.readCount = readCount;
	}

	public Timestamp getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Timestamp createDate) {
		this.createDate = createDate;
	}

	public Board(int bno, int userId, String title, String content, int readCount, Timestamp createDate) {
		super();
		this.bno = bno;
		this.userId = userId;
		this.title = title;
		this.content = content;
		this.readCount = readCount;
		this.createDate = createDate;
	}
	
	
	
}
