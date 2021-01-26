package test;

import static org.junit.Assert.assertNotNull;

import java.sql.Connection;

import org.junit.jupiter.api.Test;

import utill.DB;

public class DBTest {
	
	
	@Test
	public void 디비연결() {
		Connection conn =  DB.getConnection();
		assertNotNull(conn);
				
	}

}
