package woo.study.web.common.wrapper;

import java.io.IOException;

import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface ResponseInfo extends ServletResponse, HttpServletResponse{
	
	public int getStatus();
	public String getMsg();
	public String getResponseDatas();
	public byte[] getResponseData() throws IOException;

}
