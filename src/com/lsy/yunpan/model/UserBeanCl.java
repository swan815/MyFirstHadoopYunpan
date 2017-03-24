package com.lsy.yunpan.model;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * 验证登陆
 * @author apple
 *
 */
public class UserBeanCl {
	
	private Statement sm = null;
	
	private Connection conn = null;
	
	private ResultSet rs = null;
	
	public void close(){
		try{
			
			if(sm != null){
				sm.close();
				sm=null;
			}
			
			if(conn != null){
				conn.close();
				sm=null;
			}
			
			if(rs != null){
				rs.close();
				sm=null;
			}
		}
		catch(SQLException e){
			e.printStackTrace();
		}
	}
	
	public boolean checkUser(String user, String password){
		boolean b = false;
		
		try {
			//获得链接
			conn = new DBUtils().getConnection();
			//创建statement
			sm = conn.createStatement();
			
			rs = sm.executeQuery("select * from user where username=\"" + user + "\"");
			 
			if(rs.next()){
				//说明该用户存在
				String pwd = rs.getString(4);
				//System.out.println(pwd);
				if(password.equals(pwd)){
					//说明密码正确
					b=true;
				}else{
					b=false;
				}
			}else{
				b=false;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			this.close();
		}
		
		return b;
		
	}
	
	
	
	

}
