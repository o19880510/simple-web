package com.lutongnet.summary.dao;

import java.util.List;

import org.joda.time.LocalDate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.lutongnet.base.dao.EntityDaoSupport;
import com.lutongnet.summary.model.entity.DataSummary;
import com.lutongnet.summary.model.entity.DataSummaryIndicator.IndicatorType;


@Component("dataSummaryDAO")
public class  DataSummaryDAO extends EntityDaoSupport<DataSummary>{
	
	private static Logger log	= LoggerFactory.getLogger(DataSummaryDAO.class);
	
	private static final String HQL_REMOVE = "delete from DataSummary where nameEng = ? and effectiveDate =  ? ";
	public void remove(String nameEng, LocalDate effectiveDate){
		super.update(HQL_REMOVE, nameEng, effectiveDate);
	}
	
	
	private static final String HQL_QUERY_LIST = " from DataSummary where indicatorType = ? and effectiveDate between ? and  ?  order by effectiveDate desc ";
	public List<DataSummary> getDataSummaryList(IndicatorType indicatorType, LocalDate startDate, LocalDate endDate){
		return queryList(HQL_QUERY_LIST, indicatorType, startDate, endDate);
	}
	
}


