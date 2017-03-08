package com.lsy.yunpan.controller;

import java.io.File;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.hadoop.mapred.JobConf;

import com.lsy.yunpan.model.HDFSDao;

/**
 * Servlet implementation class UploadServlet
 */
@WebServlet("/UploadServlet")
public final class UploadServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private final Log log = LogFactory.getLog(UploadServlet.class);

	private final int MAX_FILE_SIZE = 50 * 1024 * 1024; // 50M
	private final int MAX_MEM_SIZE = 50 * 1024 * 1024; // 50M
	private String fileUploadPath;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public UploadServlet() {
		super();
	}

	@Override
	public void init() throws ServletException {
		super.init();
		log.debug("init UploadServlet");
		ServletContext context = getServletContext();
		this.fileUploadPath = context.getInitParameter("file.upload.path");
		log.debug("source file path:" + fileUploadPath + "");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException,
			IOException {
		request.setCharacterEncoding("UTF-8");
		File file;
		// 验证上传内容了类型
		String contentType = request.getContentType();
		if ((contentType.indexOf("multipart/form-data") >= 0)) {
			DiskFileItemFactory factory = new DiskFileItemFactory();
			// 设置内存中存储文件的最大值
			factory.setSizeThreshold(MAX_MEM_SIZE);
			// 本地存储的数据大于 maxMemSize.
			factory.setRepository(new File("/tmp"));

			// 创建一个新的文件上传处理程序
			ServletFileUpload upload = new ServletFileUpload(factory);
			// 设置最大上传的文件大小
			upload.setSizeMax(MAX_FILE_SIZE);
			try {
				// 解析获取的文件
				List<FileItem> fileList = upload.parseRequest(request);
				// 处理上传的文件
				Iterator<FileItem> iterator = fileList.iterator();
				log.debug("begin to upload file to tomcat server</p>");
				while (iterator.hasNext()) {
					FileItem item = iterator.next();
					if (!item.isFormField()) {
						// 获取上传文件的参数
						String fileName = item.getName();
						String fn = fileName.substring(fileName.lastIndexOf("\\") + 1);
						log.debug("<br>" + fn + "<br>");
						// boolean isInMemory = item.isInMemory();
						// long sizeInBytes = item.getSize();
						// 写入文件
						if (fileName.lastIndexOf("\\") >= 0) {
							file = new File(fileUploadPath, fileName.substring(fileName.lastIndexOf("\\")));
						} else {
							file = new File(fileUploadPath, fileName.substring(fileName.lastIndexOf("\\") + 1));
						}
						item.write(file);
						JobConf conf = HDFSDao.getConfig();
						HDFSDao hdfs = new HDFSDao(conf);
                        hdfs.copyFile(fileUploadPath+File.separator+fn, "/swan/"+fn);
					}
				}
				
				log.debug("upload file to tomcat server success!");
				request.setAttribute("flag", "success");
				request.setAttribute("msg", "upload file to tomcat server success!<br/>upload file to hadoop hdfs success!s");
				request.getRequestDispatcher("upload.jsp").forward(request, response);

			} catch (Exception ex) {
				log.error(ex.getMessage());
				request.setAttribute("flag", "danger");
				request.setAttribute("msg", ex.getMessage());
				request.getRequestDispatcher("upload.jsp").forward(request, response);
			}
		} else {
			log.warn("<p>No file uploaded</p>");
			request.setAttribute("flag", "warning");
			request.setAttribute("msg", "<p>No file uploaded</p>");
			request.getRequestDispatcher("upload.jsp").forward(request, response);
		}
	}
}
