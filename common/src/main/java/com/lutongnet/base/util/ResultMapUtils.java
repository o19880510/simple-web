package com.lutongnet.base.util;

import java.util.HashMap;
import java.util.Map;

/**
 * ResultMap用在json数据返回业务中
 * 
 * 重要属性
 * 	result : 表示业务处理情况。
 * 	常用值
 * 	0：成功
 * 	1：失败
 * 	100：系统异常
 * 
 * @author tianjp
 *
 */
public class ResultMapUtils {
	
	public static final String RESULT = "result";
	
	private static Map base(String key, int errorCode) {
		Map map = new HashMap();
		map.put(key, errorCode);
		return map;
	}
	
	public static Map result(int errorCode) {
		return base(RESULT, errorCode);
	}
	
	public static Map success() {
		return base(RESULT, 0);
	}
	
	public static Map failture() {
		return base(RESULT, 1);
	}
	
	public static Map sysErr() {
		return base(RESULT, 100);
	}
	
	
	
}
