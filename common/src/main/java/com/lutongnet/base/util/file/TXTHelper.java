package com.lutongnet.base.util.file;

import java.util.List;

/**
 * TXT文本处理类
 * @author tianjp
 */
public class TXTHelper {
	
	/**
	 * 将数据导出位TXT文本字符串
	 * 
	 * @param columns 列名
	 * @param dataList 数据列表
	 * @return 文本字符串
	 */
	public static String export(String[] columns, List<String[]> dataList) {
		StringBuilder buffer = new StringBuilder();
		dataList.add(0, columns);
		
		for(String[] rowDatas : dataList){
			for(String data : rowDatas){
				buffer.append(data).append("\t");
			}
			// 最后一个\t换成\r\n. 为了美观
			buffer.replace(buffer.length()-1, buffer.length(), "\r\n");
		}
		return buffer.toString();
	}
}
