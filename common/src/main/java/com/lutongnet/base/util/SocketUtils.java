package com.lutongnet.base.util;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

/**
 * 
 * socket 通讯工具
 * 
 * 用于http通信、soap通信。
 * 主要面向与对http和soap协议有深刻了解的用户
 * 
 * @author tianjp
 *
 */
public class SocketUtils {

	/**
	 * 发送HTTP POST 请求 
	 * @param ip 服务器IP
	 * @param port 服务器端口
	 * @param uri  调用的URI
	 * @param body 请求体
	 * @return  服务器返回的报文，包括应答头和应答体
	 * @throws Exception
	 */
	public static String post(String ip, int port, String uri, String body) throws Exception{
		StringBuilder post = new StringBuilder();
		post.append("POST ").append(uri).append(" HTTP/1.1").append("\r\n");
		post.append("Host: ").append(ip).append(":").append(port).append("\r\n");
		post.append("Connection: Close").append("\r\n");
		post.append("Content-Type: ; charset=utf-8").append("\r\n");
		post.append("Content-Length: ").append(body.length()).append("\r\n");
		post.append("\r\n");
		post.append(body);
		
		return request(ip, port, post.toString());
	}
	
	/**
	 * 发送HTTP GET 请求 
	 * @param ip 服务器IP
	 * @param port 服务器端口
	 * @param uri 调用的URI
	 * @return 服务器返回的报文，包括应答头和应答体
	 * @throws Exception
	 */
	public static String get(String ip, int port, String uri) throws Exception{
		StringBuilder get = new StringBuilder();
		get.append("GET ").append(uri).append(" HTTP/1.1").append("\r\n");
		get.append("Host: ").append(ip).append(":").append(port).append("\r\n");
		
		get.append("Connection: Close").append("\r\n");
		get.append("\r\n");
		
		return request(ip, port, get.toString());
	}
	
	
	/**
	 * 发送soap请求
	 * @param ip 服务器IP
	 * @param port 服务器端口
	 * @param methodNS 调用的方法命名空间
	 * @param methodName 调用的方法名
	 * @param paramsXml 传入的参数，XML格式
	 * @param requestUri 调用的URI
	 * @return 服务器返回的报文，包括应答头和应答体
	 * @throws Exception
	 */
	public static String soapWithTemplate(String ip, int port, String methodNS, String methodName, String paramsXml, String requestUri) throws Exception{
		
		StringBuilder xml = new StringBuilder();
		xml.append("<?xml version=\"1.0\" encoding=\"UTF-8\"?>").append("\r\n");
		xml.append("<soap:Envelope xmlns:soap=\"http://schemas.xmlsoap.org/soap/envelope/\" xmlns:xsd=\"http://www.w3.org/2001/XMLSchema\" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" >").append("\r\n");
		xml.append("<soap:Body xmlns:m=\"").append(methodNS).append("\">").append("\r\n");
		xml.append("<m:").append(methodName).append(">\r\n");
		xml.append(paramsXml).append("\r\n");
		xml.append("</m:").append(methodName).append(">\r\n");
		xml.append("</soap:Body>");
		xml.append("</soap:Envelope>");
	    
		return soapWithBody(ip, port, requestUri, xml.toString());
	}
	
	/**
	 * 发送soap请求。http请求头已经封装。只需要传入请求体即可
	 * @param ip 服务器IP
	 * @param port 服务器端口
	 * @param requestUri 调用的URI
	 * @param soapBody 请求体
	 * @return 服务器返回的报文，包括应答头和应答体
	 * @throws Exception
	 */
	public static String soapWithBody(String ip, int port, String requestUri, String soapBody) throws Exception{
		
		StringBuilder soap = new StringBuilder();
		soap.append("POST ").append(requestUri).append(" HTTP/1.1").append("\r\n");
		soap.append("Content-Type: text/xml; charset=utf-8").append("\r\n");
		soap.append("Content-Length: ").append(soapBody.length()).append("\r\n");
		soap.append("SOAPAction: ").append("\r\n");
		soap.append("Host: ").append(ip).append(":").append(port).append("\r\n");
		soap.append("Connection: Close").append("\r\n");
		soap.append("\r\n");
		soap.append(soapBody);
		
		System.out.println(soap);
		System.out.println();
		
		return request(ip, port, soap.toString());
	}
	
	
	/**
	 * 模拟HTTP请求
	 * @param ip 目标IP地址
	 * @param port	目标端口
	 * @param message 要请求的报文
	 * @return 返回HTTP响应的信息，包括请求头和请求体
	 * @throws Exception
	 */
	public static String request(String ip, int port, String message) throws Exception{
			Socket socket = new Socket(ip, port);
			
			PrintWriter pw = new PrintWriter(socket.getOutputStream());
			pw.println(message);
			pw.flush();
			
			InputStream input = socket.getInputStream();
			BufferedReader reader = new BufferedReader(new InputStreamReader(input));
			StringBuilder httpResponse = new StringBuilder();
			while(true){
				if(reader.ready()){
					String line = null;
					while( (line = reader.readLine()) != null){
						System.out.println(line); 
						httpResponse.append(line).append("\r\n");
					}
					break;
				}
				Thread.currentThread().sleep(50);
			}
			
			return httpResponse.toString();
	}
}
