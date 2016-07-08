package com.lutongnet.base.util.file;

import java.util.List;

/**
 * Excel 数据对象
 * 
 * @author tianjp
 *
 */
public class ExcelDTO {
	
	/**
	 * 表格中纸张名称
	 */
	private String				sheet;
	/**
	 * 表格列名
	 */
	private List<String>		titles;
	/**
	 * 数据列表 
	 */
	private List<List<Object>>	dataList;

	public ExcelDTO(String sheet, List<String> titles, List<List<Object>> dataList) {
		this(titles, dataList);
		this.sheet = sheet;
	}

	public ExcelDTO(List<String> titles, List<List<Object>> dataList) {
		this.titles = titles;
		this.dataList = dataList;
	}
	
	/**
	 * @return 表格中纸张名称
	 */
	public String getSheet() {
		return sheet;
	}
	
	/**
	 * @return 表格列名
	 */
	public List<String> getTitles() {
		return titles;
	}
	
	/**
	 * @return 数据列表 
	 */
	public List<List<Object>> getDataList() {
		return dataList;
	}

	@Override
	public String toString() {
		return "ExcelDTO [sheet=" + sheet + ", titles=" + titles + ", dataList=" + dataList + "]";
	}
	
}
