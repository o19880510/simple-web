
package com.lutongnet.summary.dao;

import java.util.ArrayList;
import java.util.List;

import org.joda.time.LocalDate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.lutongnet.base.dao.EntityDaoSupport;
import com.lutongnet.base.dao.vo.PaginationBean;
import com.lutongnet.summary.model.entity.DataSummaryDate;
import com.lutongnet.summary.model.entity.DataSummaryIndicator.IndicatorType;
import com.lutongnet.summary.model.req.StatisticReq;


@Component("dataSummaryDateDAO")
public class  DataSummaryDateDAO extends EntityDaoSupport<DataSummaryDate>{
	
	private static Logger log	= LoggerFactory.getLogger(DataSummaryDateDAO.class);
	
	public PaginationBean<DataSummaryDate> getDataSummaryPage(StatisticReq statisticReq){
		
		StringBuilder stringBuilder = new StringBuilder(" where 1=1 and indicatorType = ? ");
		List params = new ArrayList();
		
		params.add(statisticReq.getIndicatorType());
		
		LocalDate startDate = statisticReq.getStartDate();
		if(startDate != null){
			stringBuilder.append(" and dataDate >= ? ");
			params.add(startDate);
		}
		
		LocalDate endDate = statisticReq.getEndDate();
		if(endDate != null){
			stringBuilder.append(" and dataDate <= ? ");
			params.add(endDate);
		}
		
		stringBuilder.append(" order by dataDate desc ");
		return findByPaging(stringBuilder.toString(), params, statisticReq.getCurrent(), statisticReq.getPageSize());
	}
	
	
	
	private static final String HQL_QUERY = "from DataSummaryDate where indicatorType = ?  and dataDate = ? ";
	public DataSummaryDate get(IndicatorType indicatorType, LocalDate endDate){
		return queryFirst(HQL_QUERY, indicatorType, endDate);
	}
	
	private static final String HQL_DELETE = "delete from DataSummaryDate where indicatorType = ?  and dataDate = ? ";
	public void delete(IndicatorType indicatorType, LocalDate endDate){
		update(HQL_DELETE, indicatorType, endDate);
	}
}


