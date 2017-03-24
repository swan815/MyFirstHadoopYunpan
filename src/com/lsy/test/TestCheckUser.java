package com.lsy.test;

import java.sql.Connection;

import org.junit.Test;

import com.lsy.yunpan.model.UserBean;
import com.lsy.yunpan.model.UserBeanCl;

public class TestCheckUser {
	
	@Test
	public void testCheckUser(){
		UserBean user = new UserBean("10001", "lsy", "30940660@qq.com", "30940660");
		
		UserBeanCl userBeanCl = new UserBeanCl();
		//System.out.println("验证成功");
		
		if(userBeanCl.checkUser(user.getUsername(), user.getPassword())){
			
			System.out.println("验证成功");
		}
		
		
	}

}
