package com.lutongnet.base.util;

import java.util.HashMap;
import java.util.Map;

import com.lutongnet.base.util.HttpClientResponse;
import com.lutongnet.base.util.HttpClientUtils;
import com.lutongnet.base.util.IOSystem;

public class HttpClientUtilsTest {

	public static void main(String[] args) throws Exception {
		for (int i = 0; i < 100; i++) {
			String result = testRecomList();
			IOSystem.writeTo(i + "\t\t" + result + "\r\n", "d:/log", true);
		}
	}

	public static String testRecomList() throws Exception {
		final Map map = new HashMap();
		map.put("pageName", "testPage");
		HttpClientResponse result = null;
		// http://172.16.12.90:8080/store-topbox-1.0.0/top/recom/list.do
		result = HttpClientUtils.jsonPost("http://172.16.4.159:9000/store_base/top/recom/list.do", map);
		return String.valueOf(result);
	}
}
