package com.lutongnet.base.functions.actionlog2.config;

import java.io.Serializable;

import com.lutongnet.base.functions.actionlog.constants.ThreadLocalConstants;
import com.lutongnet.base.thread.ThreadLocalMaps;

public class UalBusinessHelper {

	private static final String	BUSINESS_CODE	= "businessCode";
	private static final String	EXCEPTION		= "exception";

	public static void setBusinessCode(Serializable businessCode) {
		ThreadLocalMaps.set(BUSINESS_CODE, businessCode);
	}

	public static Serializable getBusinessCode() {
		return (Serializable) ThreadLocalMaps.get(BUSINESS_CODE);
	}

	public static void setException(Throwable exception) {
		ThreadLocalMaps.set(EXCEPTION, exception);
	}

	public static Throwable getException() {
		Object obj = ThreadLocalMaps.get(EXCEPTION);
		if(obj == null){
			// 兼容老版本
			String exString = (String) ThreadLocalMaps.get(ThreadLocalConstants.EXCEPTION_INFO);
			
			return new Throwable(exString);
		}
		
		return (Throwable) obj;
	}

}
