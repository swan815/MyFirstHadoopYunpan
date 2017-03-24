package com.lsy.test;

import java.sql.Connection;
import java.sql.SQLException;

import org.junit.Test;

import com.lsy.yunpan.model.DBUtils;

public class TestConn {
	
	@Test
	public void testGetConnection() throws SQLException {
		Connection connection = new DBUtils().getConnection();
		System.out.println(connection);
		
	}

}
