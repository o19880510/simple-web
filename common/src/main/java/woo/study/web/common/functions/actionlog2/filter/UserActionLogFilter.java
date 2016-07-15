package woo.study.web.common.functions.actionlog2.filter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
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

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import woo.study.web.common.functions.actionlog2.service.FilterService;
import woo.study.web.common.thread.ThreadLocalMaps;
import woo.study.web.common.util.AssertHelper;
import woo.study.web.common.util.HttpResponseUtils;
import woo.study.web.common.wrapper.InfoLogServletRequest;
import woo.study.web.common.wrapper.InfoLogServletResponse;

public class UserActionLogFilter implements Filter {

	private static Logger			log	= LoggerFactory.getLogger(UserActionLogFilter.class);

	private WebApplicationContext	webSpringApp;
	private ServletContext			servletContext;
	
	private List<FilterService> filterServiceList ; 

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		filterServiceList = new ArrayList<FilterService>();
		servletContext = filterConfig.getServletContext();
		webSpringApp = WebApplicationContextUtils.getWebApplicationContext(servletContext);

		FilterService ualBaseFilterService = webSpringApp.getBean("ualBaseFilterService", FilterService.class);
		FilterService ualStayTimeFilterService = webSpringApp.getBean("ualStayTimeFilterService", FilterService.class);
		
		filterServiceList.add(ualBaseFilterService);
		filterServiceList.add(ualStayTimeFilterService);

	}

	@Override
	public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain)
			throws IOException, ServletException {
		
		ThreadLocalMaps.init();
		
		HttpServletRequest httpRequest = (HttpServletRequest) servletRequest;
		InfoLogServletRequest request = new InfoLogServletRequest(httpRequest);
		
		HttpServletResponse httpResponse = (HttpServletResponse) servletResponse;
		InfoLogServletResponse response = new InfoLogServletResponse(httpResponse);
		
		for(FilterService filterService : filterServiceList){
			boolean success = filterService.beforeCheck(request, response);
			if(success){
				filterService.beforeDoFilter(request, response);
			}
		}
		
		filterChain.doFilter(request, response);
		
		for(FilterService filterService : filterServiceList){
			boolean success = filterService.afterCheck(request, response);
			if(success){
				filterService.afterDoFilter(request, response);
			}
		}
		
		
		{ // 处理response返回数据
			
			String redirectLocation = response.getRedirectLocation();
			if( AssertHelper.isEmpty(redirectLocation) ){
				byte[] datas = response.getResponseData();
				httpResponse.setHeader("Content-Length", new Integer(datas.length).toString());
				httpResponse.setHeader("Content-Type", response.getContentType());
				HttpResponseUtils.write(httpResponse, datas);
			}
		}
		
		
		ThreadLocalMaps.clean();
	}

	@Override
	public void destroy() {

	}
	
	
	protected Map<String, String> setRequestParams(HttpServletRequest request){
		Map<String, String[]> map = request.getParameterMap();
		Map<String, String> requestMap = new HashMap<String, String>();
		for(Map.Entry<String, String[]> entry : map.entrySet()){
			String[] values = entry.getValue();
			StringBuilder value = new StringBuilder();
			if(values != null){
				for(String temp : values){
					value.append(temp);
				}
			}
			
			requestMap.put(entry.getKey(), value.toString());
		}
		
		return requestMap;
	}

}
