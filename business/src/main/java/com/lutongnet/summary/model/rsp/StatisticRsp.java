package com.lutongnet.summary.model.rsp;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.joda.time.LocalDate;

import com.lutongnet.summary.model.entity.DataSummary;


public class StatisticRsp {

	private Map<LocalDate, Map<String, DataSummary>>	dataMap;

	public StatisticRsp() {
		super();
		dataMap = new LinkedHashMap<LocalDate, Map<String,DataSummary>>();
	}
	
	public StatisticRsp add(DataSummary dataSummary){
		LocalDate localDate = dataSummary.getEffectiveDate();
		Map<String,DataSummary> map = dataMap.get(localDate);
		if(map == null){
			map = new HashMap<String, DataSummary>();
			map.put(dataSummary.getNameEng(), dataSummary);
			
			dataMap.put(localDate, map);
		}else{
			map.put(dataSummary.getNameEng(), dataSummary);
		}
		
		return this;
	}
	public StatisticRsp add(List<DataSummary> dataSummaries){
		
		for(DataSummary summary : dataSummaries){
			add(summary);
		}
		
		return this;
	}

	public Map<LocalDate, Map<String, DataSummary>> getDataMap() {
		return dataMap;
	}

	public void setDataMap(Map<LocalDate, Map<String, DataSummary>> dataMap) {
		this.dataMap = dataMap;
	}

	@Override
	public String toString() {
		return "StatisticRsp [dataMap=" + dataMap + "]";
	}
	
}
