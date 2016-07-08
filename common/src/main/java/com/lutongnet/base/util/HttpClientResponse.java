package com.lutongnet.base.util;


/**
 * http client 返回数据对象<br>
 * 
 * int status：http返回状态。200成功，其它失败。<br>
 * String body：http返回数据
 * @author tianjp
 *
 */
public class HttpClientResponse {
	private int status;
	private String body;
	
	public HttpClientResponse(int status, String body) {
		this.status = status;
		this.body = body;
	}
	
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public String getBody() {
		return body;
	}
	public void setBody(String body) {
		this.body = body;
	}
	
	@Override
	public String toString() {
		return "HttpResponse [status=" + status + ", body=" + body + "]";
	}
	
	
}

