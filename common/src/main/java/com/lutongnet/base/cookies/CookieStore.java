package com.lutongnet.base.cookies;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.lutongnet.base.util.AssertHelper;
import com.lutongnet.base.util.HttpRequestUtils;

public class CookieStore implements Store<String, String> {

	private String				path;
	private HttpServletRequest	request;
	private HttpServletResponse	response;

	public CookieStore(HttpServletRequest request, HttpServletResponse response) {
		this(null, request, response);
	}

	public CookieStore(String path, HttpServletRequest request, HttpServletResponse response) {
		this.path = AssertHelper.isEmpty(path) ? HttpRequestUtils.getContextPath(request) + "/" : path;
		this.request = request;
		this.response = response;
	}

	private Cookie getCookie(String key) {
		Cookie[] cookies = request.getCookies();
		if (cookies != null) {
			for (Cookie cookie : cookies) {
				if (cookie.getName().equals(key)) {
					return cookie;
				}
			}
		}
		return null;
	}

	public void delete(String key) {
		Cookie cookie = new Cookie(key, "");
		cookie.setPath(path);
		cookie.setMaxAge(0);
		response.addCookie(cookie);
	}

	public String get(String key) {
		Cookie cookie = getCookie(key);
		return cookie == null ? null : cookie.getValue();
	}

	public void set(String key, String value, int duration) {
		Cookie cookie = new Cookie(key, value);
		cookie.setPath(path);
		cookie.setMaxAge(duration);
		response.addCookie(cookie);
	}

	public void set(String key, String value, int duration, Time unit) {
		set(key, value, duration * unit.getValue());
	}

	public void set(String key, String value) {
		set(key, value, 1, Time.YEAR);
	}
}
