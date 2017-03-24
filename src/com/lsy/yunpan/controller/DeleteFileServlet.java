package com.lsy.yunpan.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.hadoop.fs.FileStatus;
import org.apache.hadoop.mapred.JobConf;

import com.lsy.yunpan.model.HDFSDao;

/**
 * Servlet implementation class DeleteFileServlet
 */
@WebServlet("/DeleteFileServlet")
public class DeleteFileServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		//得到文件路径
		String filePath = new String(request.getParameter("filePath").getBytes("ISO-8859-1"),"GB2312");
		
		JobConf conf = HDFSDao.getConfig();
		HDFSDao hdfs = new HDFSDao(conf);
		
		hdfs.rmr(filePath);
		
		System.out.println("===="+ filePath + "====");
		FileStatus[] list = hdfs.ls("/swan");
		request.setAttribute("list", list);
		request.getRequestDispatcher("index.jsp").forward(request, response);
		
		
		
	}

}
