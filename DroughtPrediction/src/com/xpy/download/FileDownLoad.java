package com.xpy.download;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URLEncoder;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@SuppressWarnings("serial")
public class FileDownLoad extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		String fileName=request.getParameter("fileName");
		//解决参数乱码
 		fileName=new String(fileName.getBytes("iso8859-1"),"UTF-8");
 		
		fileName=this.getServletContext().getRealPath("/WEB-INF/download/"+fileName);
// 		System.out.println(fileName+"-------->"+File.separator);
		/*
		 * 检查文件是否存在、、略
		 */
		//设置响应头，控制浏览器下载该文件
		response.setHeader("content-disposition", "attachment;filename="+URLEncoder.encode(fileName.substring(fileName.lastIndexOf(File.separator)+1),"UTF-8"));
		
		//文件下载
		FileInputStream inputStream=new FileInputStream(fileName);
		OutputStream outputStream=response.getOutputStream();
		
		byte []buffer=new byte[1024];
		int length=0;
		
		while((length=inputStream.read(buffer))!=-1){
			outputStream.write(buffer, 0, length);
		}
		inputStream.close();
		outputStream.close();
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doGet(request, response);
	}

}
