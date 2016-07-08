package com.lutongnet.base.util;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * javax.servlet.http.HttpServletResponse 帮助类
 * 
 * @author tianjp
 *
 */
public class HttpResponseUtils {
	
	public static void sendResponse(HttpServletRequest request, HttpServletResponse response, String info, String page) throws IOException{
		
		if (HttpRequestUtils.isAjaxRequest(request)) {
			writeStatus(response, info);
		} else {
			response.sendRedirect(page);
		}
	}
	
	public static void writeStatus(HttpServletResponse response, String info) throws IOException{
		writeStatus(response, info, HttpServletResponse.SC_BAD_REQUEST);
	}
	
	public static void writeStatus(HttpServletResponse response, String info, int status) throws IOException{
		response.setStatus(status);
		write(response, info);
	}
	
	/**
	 * 设置excel文件下载
	 * 
	 * @param response
	 * @param fileName 文件名称
	 * @param excelDatas excel文件二进制数据
	 */
	public static void writeExcel(HttpServletResponse response, String fileName, byte[] excelDatas){
		try {
			// 中文编码。否则客户端文件名中包含乱码
			fileName = URLEncoder.encode(fileName, "utf-8");
		} catch (UnsupportedEncodingException e) {}
		
		response.reset();
		response.setContentType("application/vnd.ms-excel");
		response.setHeader("Content-Disposition", "attachment;filename=\"" + fileName + "\"");
		
		write(response, excelDatas);
	}
	
	/**
	 * 输出附加文件
	 * @param fileName
	 * @param datas
	 * @param response
	 */
	public static void writeAsAttachment(String fileName , byte[] datas , HttpServletResponse response ){
		response.setContentType("application/" + StringUtils.getFileSuffix(fileName));
		response.setHeader("content-disposition", "attachment;filename=" + fileName);

		write(response, datas);
	}
	
	
	/**
	 * 通过response输出字节数据
	 * @param response
	 * @param datas
	 */
	public static void write(HttpServletResponse response, byte[] datas){
		try {
			response.setContentLength(datas.length);
			ServletOutputStream os = response.getOutputStream();
			os.write(datas);
			os.flush();
			os.close();
		} catch (IOException e) {
			throw new RuntimeException("write error:" + e);
		}
	}
	
	/**
	 * 通过response输出字符串
	 * @param response
	 * @param datas 需要输入的字符串
	 */
	public static void write(HttpServletResponse response, String datas){
		
		try {
			response.setContentLength(datas.length());
			PrintWriter writer = response.getWriter();
			writer.write(datas);
			writer.flush();
			writer.close();
		} catch (IOException e) {
			throw new RuntimeException("write error:" + e);
		}
	}
	
	/**
	 * 判断response正文格式，正对JSON检查
	 * @param response
	 * @return
	 */
	public static boolean isJsonContent(HttpServletResponse response){
		String contentType = response.getContentType();
		if( contentType == null || contentType.indexOf("application/json") == -1 ){
			return false;
		}
		
		return true;
	}
}
