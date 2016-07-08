package com.lutongnet.summary.service;

import org.joda.time.LocalDate;

import com.lutongnet.summary.model.entity.DataSummaryIndicator.IndicatorType;

public interface SummaryService {
	
	/**
	 * 保存数据
	 *  先删除老数据，再保存新数据
	 * @param indicatorType
	 * @param nameEng
	 * @param dataValue
	 * @param effectiveDate
	 */
	public void saveData(IndicatorType indicatorType, String nameEng, String dataValue, LocalDate effectiveDate) ;
	
	public void removeData(String nameEng, LocalDate effectiveDate) ;
	
	/**
	 * 检查统计指标
	 * 
	 * 指标不存在，新增
	 * 
	 * @param nameEng
	 * @param nameChi
	 * @param indicatorType
	 */
	public void checkIndicator(String nameEng, String nameChi, IndicatorType indicatorType) ;
	
	public void saveIndicator(String nameEng, String nameChi, IndicatorType indicatorType);
}
