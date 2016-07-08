package com.lutongnet.system.filter;

import java.io.IOException;

import java.util.Map;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.lutongnet.base.util.AssertHelper;
import com.lutongnet.base.util.HttpRequestUtils;
import com.lutongnet.base.util.HttpResponseUtils;
import com.lutongnet.system.constant.AppConstant;
import com.lutongnet.system.model.vo.UserInfo;

public class SessionFilter implements Filter {
	
	private static ServletContext servletContext ;
	private static String[] skipUriList = {};
	
	public void destroy() {

	}

	public void doFilter(ServletRequest inRequest, ServletResponse inResponse, FilterChain chain) throws IOException, ServletException {
		
		HttpServletRequest request = (HttpServletRequest)inRequest;
		HttpServletResponse response = (HttpServletResponse)inResponse;
		HttpSession session = request.getSession();
		
		
		String mid1 = request.getParameter("mid1");
		String mid2 = request.getParameter("mid2");
		String mid3 = request.getParameter("mid3");
		if (AssertHelper.notEmpty(mid1)) {
			session.setAttribute("mid1", mid1);
		}
		if (AssertHelper.notEmpty(mid2)) {
			session.setAttribute("mid2", mid2);
		}
		if (AssertHelper.notEmpty(mid3)) {
			session.setAttribute("mid3", mid3);
		}
		
		
		String requestUri = HttpRequestUtils.getRequestUri(request);
		
		for (String skipUri : skipUriList) {
			if (requestUri.startsWith(skipUri)) {
				// 访问不受保护的资源
				chain.doFilter(request, response);
				return;
			}
		}
		
		
		UserInfo user = (UserInfo) session.getAttribute(AppConstant.USER_INFO);
		String loginPage = HttpRequestUtils.getContextPath(request) + "/system/login_index.do";
		
		if (user == null) {
			// 用户没登录
			String info = "您还没有登录";
			
			session.setAttribute("info", info);
			HttpResponseUtils.sendResponse(request, response, info, loginPage);
			return;
		}
		
		// 判断是否被挤下
		Map<String, String> sessionidHolder = (Map<String, String>) session.getServletContext().getAttribute(AppConstant.SESSIONID_HOLDER);
		
		String userid = user.getUserid();
		String lastSessionId = sessionidHolder.get(userid);
		String sessionid = session.getId();
		if (!sessionid.equals(lastSessionId)) {
			// 已被挤下
			String info = "账号已经在别处登录";
			
			session.invalidate();
			session = request.getSession(true);
			
			session.setAttribute("info", info);
			HttpResponseUtils.sendResponse(request, response, info, loginPage);
			return;
		}
		
		chain.doFilter(request, response);
	}

	public void init(FilterConfig filterConfig) throws ServletException {
		servletContext = filterConfig.getServletContext();
		
		String uri = filterConfig.getInitParameter("skipUriList");
		
		if(uri != null){
			skipUriList = uri.split("\\s+");
		}
	}

}
