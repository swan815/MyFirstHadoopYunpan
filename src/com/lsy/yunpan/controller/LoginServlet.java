package com.lsy.yunpan.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.hadoop.fs.FileStatus;
import org.apache.hadoop.mapred.JobConf;

import com.lsy.yunpan.model.HDFSDao;
import com.lsy.yunpan.model.UserBeanCl;

/**
 * Servlet implementation class LoginServlet
 */
@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		
		//System.out.println(username + " " + password);
		
		UserBeanCl ubc = new UserBeanCl();
		
		if(ubc.checkUser(username, password)){
			//用户合法，跳转页面
			HttpSession session = request.getSession();
			session.setAttribute("username", username);
			
			JobConf conf = HDFSDao.getConfig();
			HDFSDao hdfs = new HDFSDao(conf);
			
			FileStatus[] list = hdfs.ls("/"+username);
			
			request.setAttribute("list", list);
			
			request.getRequestDispatcher("index.jsp").forward(request, response);
			
		}else{
			//用户不合法，调回登录界面，并提示错误信息
			request.getRequestDispatcher("login.jsp").forward(request, response);
		}
		
	}

}
