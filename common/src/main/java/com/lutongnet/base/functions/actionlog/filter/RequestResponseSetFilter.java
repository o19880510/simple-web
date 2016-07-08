package com.lutongnet.base.functions.actionlog.filter;

import java.io.IOException;





import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.lutongnet.base.thread.ThreadLocalMaps;
import com.lutongnet.base.util.AssertHelper;
import com.lutongnet.base.util.HttpResponseUtils;
import com.lutongnet.base.wrapper.InfoLogServletRequest;
import com.lutongnet.base.wrapper.InfoLogServletResponse;

public class RequestResponseSetFilter implements Filter{
	
	private static Logger log	= LoggerFactory.getLogger(RequestResponseSetFilter.class);
	
	public void init(FilterConfig paramFilterConfig) throws ServletException {
		
	}
	
	public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain chain) throws IOException, ServletException {
		
		ThreadLocalMaps.init();
		
		HttpServletRequest httpRequest = (HttpServletRequest) servletRequest;
		InfoLogServletRequest request = new InfoLogServletRequest(httpRequest);
		HttpServletResponse httpResponse = (HttpServletResponse)servletResponse;
		InfoLogServletResponse response = new InfoLogServletResponse(httpResponse);
		
		chain.doFilter(request, response);
		
		String redirectLocation = response.getRedirectLocation();
		if( AssertHelper.isEmpty(redirectLocation) ){
			byte[] datas = response.getResponseData();
			httpResponse.setHeader("Content-Length", new Integer(datas.length).toString());
			httpResponse.setHeader("Content-Type", response.getContentType());
			HttpResponseUtils.write(httpResponse, datas);
		}
		
		
		ThreadLocalMaps.clean();
	}

	public void destroy() {
		
	}

}
