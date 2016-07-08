package com.lutongnet.base.functions.actionlog2.config;

import java.util.Arrays;

import javax.servlet.http.Cookie;

import org.junit.Test;
import org.springframework.mock.web.MockHttpServletRequest;

import static org.junit.Assert.*;

public class UalStayTimeHelperTest {
	private static final String		HEADER_NAME	= "X-UAL-Log";
	
	private static final String		HTTP_STATUS	= "x-hs";
	private static final String		LOG_ID		= "x-li";
	private static final String		USER_ID		= "x-ui";
	private static final String		ACTION_CODE	= "x-ac";
	private static final String		START_TIME	= "x-st";
	
	public static void main(String[] args) {
	}
	
	
	@Test
	public void testHeader02(){
		MockHttpServletRequest request = new MockHttpServletRequest();
		request.addHeader(HEADER_NAME, "");
		UalStayTimeHelper ualStayTimeHelper = new UalStayTimeHelper(request);
		
		assertNull(ualStayTimeHelper.getLogId());
	}
	
	@Test
	public void testHeader03(){
		MockHttpServletRequest request = new MockHttpServletRequest();
		request.addHeader(HEADER_NAME, "x-hs=200; x-li=UAL011504030000033; x-ac=XX01S01; x-st=1428032374205;");
		UalStayTimeHelper ualStayTimeHelper = new UalStayTimeHelper(request);
		
		assertEquals(ualStayTimeHelper.getLogId(), "UAL011504030000033");
	}
	
	@Test
	public void testHeader04(){
		MockHttpServletRequest request = new MockHttpServletRequest();
		request.addHeader(HEADER_NAME, "x-hs=200; x-li=UAL011504030000033; x-ac=XX01S01; x-st=1428032374205");
		UalStayTimeHelper ualStayTimeHelper = new UalStayTimeHelper(request);
		assertEquals(ualStayTimeHelper.getLogId(), "UAL011504030000033");
	}
	
	@Test
	public void testHeaderCookies(){
		MockHttpServletRequest request = new MockHttpServletRequest();
		request.addHeader(HEADER_NAME, "x-hs=200; x-li=UAL011504030000033; x-ac=XX01S01; x-st=1428032374205");
		
		request.setCookies(new Cookie(HTTP_STATUS, "HTTP_STATUS"),
				new Cookie(LOG_ID, "LOG_ID"),
				new Cookie(USER_ID, "USER_ID"),
				new Cookie(ACTION_CODE, "ACTION_CODE"),
				new Cookie(START_TIME, "START_TIME")
		);
	
		
		UalStayTimeHelper ualStayTimeHelper = new UalStayTimeHelper(request);
		System.out.println("testHeaderCookies"+ualStayTimeHelper.toString());
		
		assertEquals(ualStayTimeHelper.getLogId(), "LOG_ID");
	}
	
	@Test
	public void testCookies(){
		MockHttpServletRequest request = new MockHttpServletRequest();
		
		request.setCookies(new Cookie(HTTP_STATUS, "HTTP_STATUS"),
					new Cookie(LOG_ID, "LOG_ID"),
					new Cookie(USER_ID, "USER_ID"),
					new Cookie(ACTION_CODE, "ACTION_CODE"),
					new Cookie(START_TIME, "START_TIME")
		);
		
		UalStayTimeHelper ualStayTimeHelper = new UalStayTimeHelper(request);
		System.out.println("testCookies"+ualStayTimeHelper.toString());
		
		assertEquals(ualStayTimeHelper.getLogId(), "LOG_ID");
	}
}
