package woo.study.web.common.wrapper;

import java.io.BufferedReader;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;

import woo.study.web.common.util.HttpRequestUtils;
import woo.study.web.common.util.IOSystem;

public class InfoLogServletRequest extends HttpServletRequestWrapper {

	private byte[] requestBody;
	private boolean isJsonContent;
	private boolean isMultipartContent;
	private HttpServletRequest request;
	
	public InfoLogServletRequest(HttpServletRequest request) {
		super(request);
		this.request = request;
		
		isJsonContent = HttpRequestUtils.isJsonContent(request);
		isMultipartContent = HttpRequestUtils.isMultipart(request);
		if(isJsonContent || isMultipartContent){
			try {
				requestBody = IOSystem.readToBytes(request.getInputStream());
			} catch (IOException e) {
				throw new RuntimeException(e);
			}
		}
	}
	
	
	@Override
	public BufferedReader getReader() throws IOException {
		if(isJsonContent || isMultipartContent){
			return new BufferedReader(new InputStreamReader(new ByteArrayInputStream(requestBody)));
		}else{
			return request.getReader();
		}
	}
	
	@Override
	public ServletInputStream getInputStream() throws IOException {
		if(isJsonContent || isMultipartContent){
			return new MyServletInputStream(requestBody);
		}else{
			return request.getInputStream();
		}
	}
	
	
	private class MyServletInputStream extends ServletInputStream{
		private byte[] requestBody;
		private int position;
		
		public MyServletInputStream(byte[] requestBody){
			this.requestBody = requestBody;
			position = 0;
		}
		
		@Override
		public int read() throws IOException {
			
			if(position >= requestBody.length){
				return -1;
			} 
			return requestBody[position++] & 0xFF;
		}
		
	}

}
