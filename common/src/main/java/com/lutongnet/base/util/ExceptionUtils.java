package com.lutongnet.base.util;

import java.io.ByteArrayOutputStream;
import java.io.PrintWriter;
/**
 * 
 * 异常处理工具
 * 
 * @author tianjp
 *
 */
public class ExceptionUtils {
	
	/**
	 * 获取异常堆栈信息
	 * @param e
	 * @return
	 */
	public static String getErrorMsg(Throwable e){
		
		ByteArrayOutputStream buf = new ByteArrayOutputStream();
		e.printStackTrace(new PrintWriter(buf, true));
		String expMessage = buf.toString();
		
		return expMessage;
		
	}
	
	/**
	 * 获取异常堆栈信息，有字符长度限制
	 * @param e 异常
	 * @param length 最大长度
	 * @return
	 */
	public static String getErrorMsg(Throwable e, int length){
		String errorMsg = getErrorMsg(e);
		if(errorMsg.length() > length){
			return errorMsg.substring(0, length);
		}
		return errorMsg;
	}
	
	/**
	 * 此方法可以获取类之间调用顺序
	 * @return String 
	 */
	public static String MethodInvokeList() {
		StackTraceElement[] stackTraceElements = new Exception().getStackTrace();
		StringBuilder sb = new StringBuilder();
		for (StackTraceElement stackTraceElement : stackTraceElements) {
			sb.append(stackTraceElement.getClassName())
			.append(":")
			.append(stackTraceElement.getMethodName())
			.append("\r\n");
		}
		return sb.toString();
	}
}
