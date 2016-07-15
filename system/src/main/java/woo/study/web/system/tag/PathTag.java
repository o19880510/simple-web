package woo.study.web.system.tag;

import java.io.IOException;

import java.lang.reflect.Method;

import javax.servlet.ServletContext;
import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.servlet.jsp.JspTagException;
import javax.servlet.jsp.PageContext;
import javax.servlet.jsp.tagext.BodyTagSupport;

import woo.study.web.common.util.AssertHelper;
import woo.study.web.system.datacenter.SystemDataManagementHelper;

/**
 * 
 * @author tianjp
 *
 */
public class PathTag extends BodyTagSupport {

	private static final long	serialVersionUID	= 1L;

	private static final String	PATH_PROJECT	= "path.project";
	private String	path;

	public int doStartTag() throws JspTagException {
		
		String pathProject = getValueFromScope(PATH_PROJECT);
		if (AssertHelper.isEmpty(pathProject)) {
			pathProject = getProjectContextPath();
		}
		
		if (AssertHelper.isEmpty(path) || PATH_PROJECT.equals(path.trim())) {
			write(pathProject);
			return SKIP_BODY;
		}
		
		String value = getValueFromScope(path);
		
		if (AssertHelper.isEmpty(value)) {
			value = (String) SystemDataManagementHelper.getInstance().getConfigProperties().get(path);
			if (AssertHelper.notEmpty(value)) {
				value = pathProject + value;
			}
		}

		write(value);

		return SKIP_BODY;
	}

	private String getProjectUrl() {
		HttpServletRequest request = (HttpServletRequest) this.pageContext.getRequest();
		return "http://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath();
	}
	
	private String getProjectContextPath() {
		HttpServletRequest request = (HttpServletRequest) this.pageContext.getRequest();
		return request.getContextPath();
	}

	private String getValueFromScope(String key) {
		Object returnValue = getFromScope(key);

		return returnValue == null ? "" : returnValue.toString();
	}

	private Object getFromScope(String param) {
		try {
			PageContext pageContext = this.pageContext;
			HttpServletRequest request = (HttpServletRequest) this.pageContext.getRequest();
			HttpSession session = request.getSession();
			ServletContext servletContext = this.pageContext.getServletContext();
			
			Object returnValue = pageContext.getAttribute(param);
			if(returnValue != null){
				return returnValue;
			}
			
			returnValue = request.getAttribute(param);
			if(returnValue != null){
				return returnValue;
			}
			
			returnValue = session.getAttribute(param);
			if(returnValue != null){
				return returnValue;
			}
			
			returnValue = servletContext.getAttribute(param);
			if(returnValue != null){
				return returnValue;
			}
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	private void write(String cons) {
		try {
			this.pageContext.getOut().write(cons);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}
	
	public static void main(String[] args) throws SecurityException, NoSuchMethodException {
		Method getAttribute = ServletRequest.class.getMethod("getAttribute", String.class);
		System.out.println(getAttribute);
		
	}
}
