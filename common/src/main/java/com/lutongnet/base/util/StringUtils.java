package com.lutongnet.base.util;

import java.io.UnsupportedEncodingException;

/**
 * 字符串处理工具
 * 
 * @author tianjp
 *
 */
public class StringUtils {

	private static final String	UTF_8			= "UTF-8";
	public static final String	POSITON_LEFT	= "L";
	public static final String	POSITON_RIGHT	= "R";
	
	/**
	 * 字符串相加，高效率
	 * @param strs
	 * @return
	 */
	public static String plus(String... strs){
		StringBuilder sb = new StringBuilder();
		for(String str : strs){
			sb.append(str);
		}
		return sb.toString();
	}
	
	
	/**
	 * 将byte数据转化为字符串，默认UTF-8编码
	 * @param bytes
	 * @return
	 */
	public static String toString(byte[] bytes ){
		return toString(bytes, UTF_8);
	}
	
	/**
	 * 将byte数据转化为字符串
	 * @param bytes
	 * @param charset 指定字符集
	 * @return
	 */
	public static String toString(byte[] bytes, String charset){
		try {
			return new String(bytes, charset);
		} catch (UnsupportedEncodingException e) {
			throw new RuntimeException(e);
		}
	}
	
	/**
	 * 校验字符串的长度，若超长，则返回截取后的字符串
	 * @param inStr
	 * @param length 指定长度
	 * @return
	 */
	public static final String checkLength(String inStr, int length){
		if(inStr == null){
			return null;
		}
		
		if(inStr.length() <= length){
			return inStr;
		}
		
		return inStr.substring(0, length - 1);
	}
	
	/**
	 * 将null转换为""
	 * @param content
	 * @return
	 */
	public static String nullToEmpty(String content) {

		return content == null ? "" : content;
	}

	/**
	 * 将null转换为"0"
	 * @param content
	 * @return
	 */
	public static String nullToZero(String content) {

		return content == null ? "0" : content;
	}


	/**
	 * 将字符串中的HTML标记符转换为指定字符
	 */
	public static String encodeHTML(String inStr) {
		String result = "";
		if (inStr != null && inStr.length() > 0) {
			result = inStr.trim();
			result = result.replace( "&", "&amp;");
			result = result.replace( "\"", "&quot;");
			result = result.replace(  "<", "&lt;");
			result = result.replace( ">", "&gt;");
			result = result.replace( "#", "&#35;");
			result = result.replace( "%", "&#37;");
			result = result.replace( "'", "&#39;");
		}
		return result;
	}
	
	
	public static String getFileSuffix(String fileName){
		int index = fileName.lastIndexOf(".") + 1;
		return fileName.substring(index);
	}
	
	/**
	 * 将字符串转化为byte数组，默认为UTF-8编码
	 * @param inStr
	 * @return
	 */
	public static byte[] getBytes(String inStr){
		try {
			return inStr.getBytes(UTF_8);
		} catch (UnsupportedEncodingException e) {
			throw new RuntimeException("getBytes error:" + e);
		}
	}
	
	/**
	 * 转换字符转的编码
	 * 
	 * @param inString 输入字符串
	 * @param sourceEncoding 原始编码
	 * @param targetEncoding 目标编码
	 * @return 转码后的字符串
	 */
	public static String changeEncoding(String inString, String sourceEncoding, String targetEncoding) {
		try {
			return new String(inString.getBytes(sourceEncoding), targetEncoding);
		} catch (UnsupportedEncodingException e) {
			throw new RuntimeException(e);
		}
	}
	
	/**
	 * 截取字符串。字符串结束标识只在开始标识之后；返回字符串不包含开始和结束标识。
	 * @param targetStr
	 * @param startStr 字符串开始标识
	 * @param endStr 字符串结束标识
	 * @return
	 */
	public static String subString(String targetStr, String startStr, String endStr){
		int startIndex = targetStr.indexOf(startStr);
		int endIndex = targetStr.indexOf(endStr, startIndex);
		
		String responseUrl = targetStr.substring(startIndex + startStr.length(), endIndex);
		
		return responseUrl;
	}
	
	/**
	 * 截取字符串
	 * 返回的字符串不包含开始标识
	 * @param targetStr
	 * @param startStr 开始标识
	 * @return
	 */
	public static String subString(String targetStr, String startStr){
		int startIndex = targetStr.indexOf(startStr);
		String responseUrl = targetStr.substring(startIndex + startStr.length());
		return responseUrl;
	}
	
	
	/**
	 * 字符串补零。左补零。
	 * @param value 原始字符串
	 * @param length 字符串长度
	 * @return
	 */
	public static String fillZero(String value, int length){
			
		StringBuffer sb = new StringBuffer();
		
		int i = value.length();
		while ((length - i) > 0) {
			sb.append("0");
			i++;
		}
		sb.append(value);
		
		return sb.toString();
	}
	
	/**
	 * 分割字符串，基于长度。
	 * @param tarStr
	 * @param baseLength 基本长度
	 * @return String[] 
	 */
	public static String[] split(String tarStr, int baseLength){
		int length = tarStr.length();
		int count = length / baseLength;
		
		if(baseLength * count != length){
			count += 1;
		}
		
		String[] strs = new String[count]; 
		
		for(int i = 0; i < strs.length; i++ ){
			int startIndex = baseLength * i;
			int endIndex = baseLength*(i+1);
			if(endIndex > length){
				endIndex = length;
			}
			strs[i] = tarStr.substring(startIndex, endIndex);
		}
		
		return strs;
	}
	
	/**
	 * 获取指定字符串在目标字符串中的位置。
	 * @param strs 目标字符串
	 * @param chars 查询字符串
	 * @param seqNo 顺序第几个
	 * @return
	 */
	public static int getPosition(String strs, String chars, int seqNo){
		
		int startIndex = 0;
		while( --seqNo >= 0 ){
			startIndex = strs.indexOf(chars, startIndex) + 1;
		}
		
		return startIndex ;
	}
	
	
	/**
	 * 填充字符串
	 * @param value 原始字符串
	 * @param length 需要的长度
	 * @param postion 位置，左边还是右边填充
	 * @param fillChar 需要填充的字符
	 * @return
	 */
	public static String fill(String value,int length, String postion, char fillChar){
		String str = value;
		int zeroNum = length - str.length();
		if(zeroNum > 0){
			while(zeroNum-- != 0){
				if(POSITON_LEFT.equals(postion)){
					str = fillChar + str;
				}else{
					str = str + fillChar;
				}
			}
		}
		return str;
	}
	/**
	 * 填充0字符，左边填充
	 * @param value 原始字符串
	 * @param length 需要字符串长度
	 * @return
	 */
	public static String fillZeroLeft(String value,int length){
		return fill(value, length, POSITON_LEFT, '0');
	}
	
	/**
	 * 填充0字符，右边填充
	 * @param value 原始字符串
	 * @param length 需要字符串长度
	 * @return
	 */
	public static String fillZeroRight(String value,int length){
		return fill(value, length, POSITON_RIGHT, '0');
	}
	
	/**
	 * 填充空格字符，左边填充
	 * @param value 原始字符串
	 * @param length 需要字符串长度
	 * @return
	 */
	public static String fillSpaceLeft(String value,int length ){
		return fill(value, length, POSITON_LEFT, ' ');
	}
	
	/**
	 * 填充空格字符，右边填充
	 * @param value 原始字符串
	 * @param length 需要字符串长度
	 * @return
	 */
	public static String fillSpaceRight(String value,int length ){
		return fill(value, length, POSITON_RIGHT, ' ');
	}
	
	/**
	 * 去除字符串中的制表符、回车符、换行符
	 * @param str 输入字符串
	 * @return
	 */
	public static String replaceCrtl(String str) {
		return replace(str, "\t|\r|\n");
	}
	
	/**
	 * 去除字符串中的空格
	 * @param str 输入字符串
	 * @return
	 */
	public static String replaceBlank(String str) {
		return replace(str, "\\s*");
	}
	
	/**
	 * 将正则表达式匹配的字符串替换成空字符串
	 * @param str 输入字符串
	 * @param regex 正则表达式
	 * @return
	 */
	public static String replace(String str, String regex) {
		if (str != null) {
			return str.replaceAll(regex, "");
		}
		return "";
	}
	
	/**
	 * 
	 * @param byteArray
	 * @return
	 */
	public static String byteArrayToHex(byte[] byteArray) {

		char[] hexDigits = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F' };
		char[] resultCharArray = new char[byteArray.length * 2];
		int index = 0;

		for (byte b : byteArray) {
			resultCharArray[index++] = hexDigits[b >>> 4 & 0xf];
			resultCharArray[index++] = hexDigits[b & 0xf];
		}

		return new String(resultCharArray);
	}
}
