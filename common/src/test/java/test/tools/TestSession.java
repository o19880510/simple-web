package test.tools;

import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionContext;

public class TestSession implements HttpSession{
	
	private ServletContext servletContext;
	public TestSession(){
		
	}
	public TestSession(ServletContext servletContext){
		this.servletContext = servletContext;
	}
	
	public ServletContext getServletContext() {
		return servletContext;
	}
	
	
	
	
	private Map<String, Object> attributeMap = new HashMap<String, Object>(8);
	
	public Object getAttribute(String paramString) {
		return attributeMap.get(paramString);
	}
	
	public void setAttribute(String paramString, Object paramObject) {
		attributeMap.put(paramString, paramObject);
	}
	
	
	public long getCreationTime() {
		// TODO Auto-generated method stub
		return 0;
	}

	public String getId() {
		// TODO Auto-generated method stub
		return null;
	}

	public long getLastAccessedTime() {
		// TODO Auto-generated method stub
		return 0;
	}

	

	public void setMaxInactiveInterval(int paramInt) {
		// TODO Auto-generated method stub
		
	}

	public int getMaxInactiveInterval() {
		// TODO Auto-generated method stub
		return 0;
	}

	public HttpSessionContext getSessionContext() {
		// TODO Auto-generated method stub
		return null;
	}

	

	public Object getValue(String paramString) {
		// TODO Auto-generated method stub
		return null;
	}

	public Enumeration getAttributeNames() {
		// TODO Auto-generated method stub
		return null;
	}

	public String[] getValueNames() {
		// TODO Auto-generated method stub
		return null;
	}

	public void putValue(String paramString, Object paramObject) {
		// TODO Auto-generated method stub
		
	}

	public void removeAttribute(String paramString) {
		// TODO Auto-generated method stub
		
	}

	public void removeValue(String paramString) {
		// TODO Auto-generated method stub
		
	}

	public void invalidate() {
		// TODO Auto-generated method stub
		
	}

	public boolean isNew() {
		// TODO Auto-generated method stub
		return false;
	}

}
