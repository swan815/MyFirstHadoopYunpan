package com.lsy.yunpan.model;

import java.sql.Connection;
import java.sql.DriverManager;

/**
 * 数据库链接工具类
 * 
 * @author apple
 *
 */
public class DBUtils {
	
	private Connection connection;
	
	public Connection getConnection(){
		
		try {
			Class.forName("com.mysql.jdbc.Driver");
			
			connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/hadoop?user=root&password=30940660");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return connection;
	}

}
