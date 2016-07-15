package woo.study.web.common.util;

import woo.study.web.common.util.StringUtils;

public class StringUtilsTest {
	
	public static void main(String[] args) {
		System.out.println(StringUtils.encodeHTML("<classpathentry kind=\"src\" path=\"src/unittest\"/>"));
		System.out.println(StringUtils.encodeHTML("<classpathentry kind=\"src\" path=\"src/system\"/>"));
		System.out.println(StringUtils.encodeHTML("<classpathentry kind=\"src\" path=\"src/functions/actionlog\"/>"));
		System.out.println(StringUtils.encodeHTML("<classpathentry kind=\"src\" path=\"src/functions/datacenter\"/>"));
		System.out.println(StringUtils.encodeHTML("<classpathentry kind=\"src\" path=\"src/functions/codetable\"/>"));
		System.out.println(StringUtils.encodeHTML("<name>base</name>"));
		
	}
}
