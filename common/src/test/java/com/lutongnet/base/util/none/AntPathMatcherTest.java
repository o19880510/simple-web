package com.lutongnet.base.util.none;

import java.util.Map;

import org.junit.Test;
import org.springframework.util.AntPathMatcher;
import org.springframework.web.util.UrlPathHelper;

public class AntPathMatcherTest {

	AntPathMatcher	pathMatcher		= new AntPathMatcher();
	UrlPathHelper	urlPathHelper	= new UrlPathHelper();

	@Test
	public void testIsPattern() {
		System.out.println(pathMatcher.isPattern("/sys/113"));
		System.out.println(pathMatcher.isPattern("/sys/${var}"));
		System.out.println(pathMatcher.isPattern("/sys/{var}"));
	}

	@Test
	public void testMatchStart() {

		System.out.println(pathMatcher.isPattern("/sys/{var}/{var2}.do"));
		System.out.println(pathMatcher.matchStart("/sys/{var}/{var2}.do", "/sys/asd/123.do"));
		System.out.println(pathMatcher.match("/sys/{var}/{var2}.do", "/sys/asd/123.do"));
		System.out.println(pathMatcher.extractUriTemplateVariables("/sys/{var}/{var2}.do", "/sys/asd/123.do"));
		System.out.println(pathMatcher.extractUriTemplateVariables("/sys/z.do", "/sys/asd/123.do"));

	}

	@Test
	public void testMatchStart02() {
		System.out.println("----------------------------testMatchStart02----------------------------");

		System.out.println(pathMatcher.isPattern("/sys/*.do"));
		System.out.println(pathMatcher.matchStart("/sys/*.do", "/sys/asd.do"));
		System.out.println("\"*\", \"POST\"="+pathMatcher.matchStart("*", "POST"));
		System.out.println("\"GET\", \"POST\"="+pathMatcher.matchStart("GET", "POST"));
		System.out.println("\"POST\", \"POST\"="+pathMatcher.matchStart("POST", "POST"));
		System.out.println("\"POST\", \"post\"="+pathMatcher.matchStart("POST", "post"));
		System.out.println(pathMatcher.matchStart("/sys/*", "/sys/asd.do"));
		System.out.println(pathMatcher.matchStart("/sys/*.do", "/sys/asd/123.do"));
		System.out.println(pathMatcher.matchStart("/sys/**", "/sys/asd/123.do"));
		System.out.println(pathMatcher.matchStart("/sys/*sd/*.do", "/sys/asd/123.do"));
		System.out.println(pathMatcher.matchStart("/sys/*sd/**", "/sys/asd/123.do"));
		System.out.println(pathMatcher.extractUriTemplateVariables("/sys/*.do", "/sys/123.do"));

		System.out.println("----------------------------testMatchStart02----------------------------");
	}
	@Test
	public void testMatchStart03() {
		System.out.println("----------------------------testMatchStart03----------------------------");
		
		System.out.println(pathMatcher.matchStart("*/sys/*", "GET/sys/asd.do"));
		System.out.println(pathMatcher.matchStart("GET/sys/*", "GET/sys/asd.do"));
		System.out.println(pathMatcher.matchStart("POST/sys/*", "GET/sys/asd.do"));
		System.out.println(pathMatcher.match("*/sys/*", "GET/sys/asd.do"));
		System.out.println(pathMatcher.match("GET/sys/*", "GET/sys/asd.do"));
		System.out.println(pathMatcher.match("POST/sys/*", "GET/sys/asd.do"));
		
		System.out.println(pathMatcher.extractUriTemplateVariables("*/sys/{var}/{var2}.do", "GET/sys/asd/123.do"));
		System.out.println(pathMatcher.extractUriTemplateVariables("*/sys/{var}/{var2}.do", "POST/sys/asd/123.do"));
		System.out.println(pathMatcher.extractUriTemplateVariables("POST/sys/{var}/{var2}.do", "POST/sys/asd/123.do"));
		System.out.println(pathMatcher.extractUriTemplateVariables("GET/sys/{var}/{var2}.do", "POST/sys/asd/123.do"));
		
		System.out.println("----------------------------testMatchStart03----------------------------");
	}
	
	@Test
	public void testMatchStart04() {
		System.out.println("----------------------------testMatchStart04----------------------------");
		
		System.out.println(pathMatcher.matchStart("*/test/actionlog2.0/{name}.jsp", "GET/"));
		System.out.println(pathMatcher.matchStart("{method}/test/actionlog2.0", "GET/"));
		System.out.println(pathMatcher.match("*/test/actionlog2.0/{name}.jsp", "GET/"));
		System.out.println(pathMatcher.match("{method}/test/actionlog2.0", "GET/"));
		System.out.println(pathMatcher.matchStart("?", "11"));
		
		
		System.out.println("----------------------------testMatchStart04----------------------------");
	}

	@Test
	public void testSummary() {
		String pattern = "/sys/{1:\\d*A}";
		String path = "/sys/1230B";
		if (pathMatcher.match(pattern, path)) {
			Map result = pathMatcher.extractUriTemplateVariables(pattern, path);
			System.out.println("result=" + result);
		} else {
			System.out.println("NotMatch!");
		}
	}

}
