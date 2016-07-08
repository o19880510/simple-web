package test.tools;

import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Enumeration;
import java.util.EventListener;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import javax.servlet.Filter;
import javax.servlet.FilterRegistration;
import javax.servlet.FilterRegistration.Dynamic;
import javax.servlet.RequestDispatcher;
import javax.servlet.Servlet;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;
import javax.servlet.SessionCookieConfig;
import javax.servlet.SessionTrackingMode;
import javax.servlet.descriptor.JspConfigDescriptor;

public class TestServletContext implements ServletContext{
	
	public Map<String,Object> attriMap = new HashMap<String,Object> ();
	
	public Object getAttribute(String paramString) {
		return attriMap.get(paramString);
	}

	public void setAttribute(String paramString, Object paramObject) {
		attriMap.put(paramString, paramObject);
		
	}

	public void removeAttribute(String paramString) {
		attriMap.remove(paramString);
		
	}

	public String getServletContextName() {
		return null;
	}
	
	
	public Enumeration getAttributeNames() {
		// TODO Auto-generated method stub
		return null;
	}
	
	public ServletContext getContext(String paramString) {
		// TODO Auto-generated method stub
		return null;
	}

	public String getContextPath() {
		// TODO Auto-generated method stub
		return null;
	}

	public int getMajorVersion() {
		// TODO Auto-generated method stub
		return 0;
	}

	public int getMinorVersion() {
		// TODO Auto-generated method stub
		return 0;
	}

	public String getMimeType(String paramString) {
		// TODO Auto-generated method stub
		return null;
	}

	public Set getResourcePaths(String paramString) {
		// TODO Auto-generated method stub
		return null;
	}

	public URL getResource(String paramString) throws MalformedURLException {
		// TODO Auto-generated method stub
		return null;
	}

	public InputStream getResourceAsStream(String paramString) {
		// TODO Auto-generated method stub
		return null;
	}

	public RequestDispatcher getRequestDispatcher(String paramString) {
		// TODO Auto-generated method stub
		return null;
	}

	public RequestDispatcher getNamedDispatcher(String paramString) {
		// TODO Auto-generated method stub
		return null;
	}

	public Servlet getServlet(String paramString) throws ServletException {
		// TODO Auto-generated method stub
		return null;
	}

	public Enumeration getServlets() {
		// TODO Auto-generated method stub
		return null;
	}

	public Enumeration getServletNames() {
		// TODO Auto-generated method stub
		return null;
	}

	public void log(String paramString) {
		// TODO Auto-generated method stub
		
	}

	public void log(Exception paramException, String paramString) {
		// TODO Auto-generated method stub
		
	}

	public void log(String paramString, Throwable paramThrowable) {
		// TODO Auto-generated method stub
		
	}

	public String getRealPath(String paramString) {
		// TODO Auto-generated method stub
		return null;
	}

	public String getServerInfo() {
		// TODO Auto-generated method stub
		return null;
	}

	public String getInitParameter(String paramString) {
		// TODO Auto-generated method stub
		return null;
	}

	public Enumeration getInitParameterNames() {
		// TODO Auto-generated method stub
		return null;
	}

	
	public Dynamic addFilter(String arg0, String arg1) {
		// TODO Auto-generated method stub
		return null;
	}

	
	public Dynamic addFilter(String arg0, Filter arg1) {
		// TODO Auto-generated method stub
		return null;
	}

	
	public Dynamic addFilter(String arg0, Class<? extends Filter> arg1) {
		// TODO Auto-generated method stub
		return null;
	}

	
	public void addListener(String arg0) {
		// TODO Auto-generated method stub
		
	}

	
	public <T extends EventListener> void addListener(T arg0) {
		// TODO Auto-generated method stub
		
	}

	
	public void addListener(Class<? extends EventListener> arg0) {
		// TODO Auto-generated method stub
		
	}

	
	public javax.servlet.ServletRegistration.Dynamic addServlet(String arg0, String arg1) {
		// TODO Auto-generated method stub
		return null;
	}

	
	public javax.servlet.ServletRegistration.Dynamic addServlet(String arg0, Servlet arg1) {
		// TODO Auto-generated method stub
		return null;
	}

	
	public javax.servlet.ServletRegistration.Dynamic addServlet(String arg0, Class<? extends Servlet> arg1) {
		// TODO Auto-generated method stub
		return null;
	}

	
	public <T extends Filter> T createFilter(Class<T> arg0) throws ServletException {
		// TODO Auto-generated method stub
		return null;
	}

	
	public <T extends EventListener> T createListener(Class<T> arg0) throws ServletException {
		// TODO Auto-generated method stub
		return null;
	}

	
	public <T extends Servlet> T createServlet(Class<T> arg0) throws ServletException {
		// TODO Auto-generated method stub
		return null;
	}

	
	public void declareRoles(String... arg0) {
		// TODO Auto-generated method stub
		
	}

	
	public ClassLoader getClassLoader() {
		// TODO Auto-generated method stub
		return null;
	}

	
	public Set<SessionTrackingMode> getDefaultSessionTrackingModes() {
		// TODO Auto-generated method stub
		return null;
	}

	
	public int getEffectiveMajorVersion() {
		// TODO Auto-generated method stub
		return 0;
	}

	
	public int getEffectiveMinorVersion() {
		// TODO Auto-generated method stub
		return 0;
	}

	
	public Set<SessionTrackingMode> getEffectiveSessionTrackingModes() {
		// TODO Auto-generated method stub
		return null;
	}

	
	public FilterRegistration getFilterRegistration(String arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	
	public Map<String, ? extends FilterRegistration> getFilterRegistrations() {
		// TODO Auto-generated method stub
		return null;
	}

	
	public JspConfigDescriptor getJspConfigDescriptor() {
		// TODO Auto-generated method stub
		return null;
	}

	
	public ServletRegistration getServletRegistration(String arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	
	public Map<String, ? extends ServletRegistration> getServletRegistrations() {
		// TODO Auto-generated method stub
		return null;
	}

	
	public SessionCookieConfig getSessionCookieConfig() {
		// TODO Auto-generated method stub
		return null;
	}

	
	public boolean setInitParameter(String arg0, String arg1) {
		// TODO Auto-generated method stub
		return false;
	}

	
	public void setSessionTrackingModes(Set<SessionTrackingMode> arg0) {
		// TODO Auto-generated method stub
		
	}


}
