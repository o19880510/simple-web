package woo.study.web.common.util;

import java.math.BigDecimal;

/**
 * 
 * 数字转换工具
 * 
 * @author tianjp
 *
 */
public class NumberUtils {
	
	/**
	 * 将字符串转换为整型
	 * @param number 输入字符串
	 * @return 返回整型。若转换错误，返回 -1
	 */
	public static int parseInt(String number) {

		try {
			return Integer.parseInt(number);
		} catch (java.lang.NumberFormatException nfe) {
			return -1;
		}
	}
	
	/**
	 * 将字符串转换为整型
	 * @param number 输入字符串
	 * @param defaultValue 转换失败时，返回的整型
	 * @return 返回整型
	 */
	public static int parseInt(String number, int defaultValue) {
		
		try {
			return Integer.parseInt(number);
		} catch (java.lang.NumberFormatException nfe) {
			return defaultValue;
		}
	}
	
	/**
	 * 将字符串转换为长整型
	 * @param number 输入字符串
	 * @return 返回长整型。若转换错误，返回 -1L
	 */
	public static long parseLong(String number){
		try {
			return Long.valueOf(number.toString());
		} catch (java.lang.NumberFormatException nfe) {
			return -1L;
		}
	}
	
	/**
	 * 将字符串转换为长整型
	 * @param number 输入字符串
	 * @param defaultValue 转换失败时，返回的长整型
	 * @return 返回长整型
	 */
	public static long parseLong(String number, long defaultValue) {
		try {
			return Long.valueOf(number.toString());
		} catch (java.lang.NumberFormatException nfe) {
			return defaultValue;
		}
	}
	
	/**
	 * 生成随机数字
	 * @param length  随机数长度
	 * @return 字符串形式返回
	 */
	public static String random(int length){
		
		StringBuilder numberBuilder = new StringBuilder();
		for(int i = 0 ; i < length ; i++){
			int randomNum = (int)(Math.random()*10 - 1);
			numberBuilder.append(randomNum);
		}
		return numberBuilder.toString();
	}
	
	
	/**
	 * 整型转换位byte数组
	 * @param inum 整型
	 * @return byte数组。byte[4]。
	 */
	public static byte[] intToByte(int inum) {
		byte[] abyte0 = new byte[4];
		abyte0[0] = (byte) (0xff & inum);
		abyte0[1] = (byte) ((0xff00 & inum) >> 8);
		abyte0[2] = (byte) ((0xff0000 & inum) >> 16);
		abyte0[3] = (byte) ((0xff000000 & inum) >> 24);
		return abyte0;
	}
	
	/**
	 * byte数组转换整型
	 * @param bytes 数组。byte[4]
	 * @return 整型
	 */
	public static int bytesToInt(byte[] bytes) {
		int addr = bytes[0] & 0xFF;
		addr |= ((bytes[1] << 8) & 0xFF00);
		addr |= ((bytes[2] << 16) & 0xFF0000);
		addr |= ((bytes[3] << 24) & 0xFF000000);
		
		return addr;
	}
	
	/**
	 * 两个数相除,小数保留三位
	 * @param up 分子
	 * @param down 分母
	 * @return 字符串。格式0.000
	 */
	public static String divide(int up, int down) {
		if (down != 0) {
			BigDecimal bigA = new BigDecimal(up);
			BigDecimal bigB = new BigDecimal(down);
			BigDecimal result = bigA.divide(bigB, 3, BigDecimal.ROUND_HALF_UP);
			return result.toString();
		}
		return "0.000";
	}
	 
}
