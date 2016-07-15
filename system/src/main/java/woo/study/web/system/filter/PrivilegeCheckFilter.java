package woo.study.web.system.filter;

import java.io.IOException;

import java.util.Set;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import woo.study.web.common.util.HttpRequestUtils;
import woo.study.web.common.util.HttpResponseUtils;
import woo.study.web.system.constant.AppConstant;
import woo.study.web.system.datacenter.SystemDataManagementHelper;
import woo.study.web.system.model.vo.UserInfo;

public class PrivilegeCheckFilter implements Filter{
	
	public void destroy() {
		
	}

	public void doFilter(ServletRequest inRequest, ServletResponse inResponse, FilterChain chain) throws IOException,
			ServletException {
		
		HttpServletRequest request = (HttpServletRequest)inRequest;
		HttpServletResponse response = (HttpServletResponse)inResponse;
		HttpSession session = request.getSession();
		
		
		UserInfo user = (UserInfo) session.getAttribute(AppConstant.USER_INFO);
		if(user == null){
			chain.doFilter(request, response);
			return;
		}
		
		// 用户已登录
		Set<String> privilegeUriSet = SystemDataManagementHelper.getInstance().getPrivilegeUris();
		Set<String> allowUriSet = user.getAllowUriSet();
		String requestUri = HttpRequestUtils.getRequestUri(request);
		if (privilegeUriSet.contains(requestUri)) {
			// 访问受权限保护的资源，判断有没有权限
			if(!allowUriSet.contains(requestUri)){
				// 没有权限
				String info = "权限不足";
				
				String deniedPage = HttpRequestUtils.getContextPath(request) + "/system/info/denied.do";
				
				session.setAttribute("info", info);
				HttpResponseUtils.sendResponse(request, response, info, deniedPage);
				return;
			}
		}
		
		chain.doFilter(request, response);
	}

	public void init(FilterConfig filterConfig) throws ServletException {
	}

}
