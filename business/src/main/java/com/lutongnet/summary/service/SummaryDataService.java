package com.lutongnet.summary.service;

import java.util.ArrayList;

import java.util.Arrays;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.joda.time.DateTime;
import org.joda.time.LocalDate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.lutongnet.base.dao.vo.PaginationBean;
import com.lutongnet.summary.dao.DataSummaryDAO;
import com.lutongnet.summary.dao.DataSummaryDateDAO;
import com.lutongnet.summary.dao.DataSummaryIndicatorDAO;
import com.lutongnet.summary.model.entity.DataSummary;
import com.lutongnet.summary.model.entity.DataSummaryDate;
import com.lutongnet.summary.model.entity.DataSummaryIndicator;
import com.lutongnet.summary.model.entity.DataSummaryIndicator.IndicatorType;
import com.lutongnet.summary.model.req.StatisticReq;
import com.lutongnet.summary.model.rsp.StatisticRsp;


@Service
public class SummaryDataService {
	
	private static Logger log = LoggerFactory.getLogger(SummaryDataService.class);
	
	@Resource(name = "dataSummaryDAO")
	private DataSummaryDAO dataSummaryDAO;
	
	@Resource(name = "dataSummaryIndicatorDAO")
	private DataSummaryIndicatorDAO dataSummaryIndicatorDAO;
	
	@Resource(name = "dataSummaryDateDAO")
	private DataSummaryDateDAO dataSummaryDateDAO;
	
	@Transactional(propagation = Propagation.REQUIRED)
	public void getPage(StatisticReq statisticReq, HttpServletRequest request){
		
		log.debug("MmsStatisticService getPage input-statisticReq=" + statisticReq);
		
		IndicatorType indicatorType = statisticReq.getIndicatorType();
		LocalDate startDate = statisticReq.getStartDate();
		LocalDate endDate = statisticReq.getEndDate();
		
		LocalDate newStartDate = startDate;
		LocalDate newEndDate = endDate;
		
		switch(indicatorType){
			case W:{
				newStartDate = startDate == null ? null:startDate.withDayOfWeek(1);
				newEndDate = endDate == null ? null:endDate.withDayOfWeek(1);
				break;
			}
			case M:{
				newStartDate = startDate == null ? null:startDate.withDayOfMonth(1);
				newEndDate = endDate == null ? null:endDate.withDayOfMonth(1);
				break;
			}
			case Y:{
				newStartDate = startDate == null ? null:startDate.withDayOfYear(1);
				newEndDate = endDate == null ? null:endDate.withDayOfYear(1);
				break;
			}
			default:{
				
			}
		}
		
		statisticReq.setStartDate(newStartDate);
		statisticReq.setEndDate(newEndDate);
		
		log.debug("MmsStatisticService getPage (new)output-statisticReq=" + statisticReq);
		
		
		PaginationBean<DataSummaryDate> datePage = dataSummaryDateDAO.getDataSummaryPage(statisticReq);
		List<DataSummaryDate> dateList = datePage.getDataList();
		
		List<DataSummary> dataSummaryList = new ArrayList<DataSummary>();
		if(dateList.size() != 0){
			LocalDate queryStartDate = dateList.get(dateList.size() - 1).getDataDate();
			LocalDate queryEndDate = dateList.get(0).getDataDate();
			
			dataSummaryList = dataSummaryDAO.getDataSummaryList(indicatorType, queryStartDate, queryEndDate);
		}
		
		PaginationBean<StatisticRsp> dataSummaryPage = new PaginationBean<StatisticRsp>(datePage.getCurrent(), datePage.getRowCount(), datePage.getPageSize());
		dataSummaryPage.setDataList( Arrays.asList(new StatisticRsp[]{ new StatisticRsp().add(dataSummaryList)} ));
		request.setAttribute("statisticRspPage", dataSummaryPage);
		
		List<DataSummaryIndicator> dataSummaryIndicators = dataSummaryIndicatorDAO.getsByType(statisticReq.getIndicatorType());
		request.setAttribute("dataSummaryIndicators", dataSummaryIndicators);
	}
	
	public static void main(String[] args) {
		System.out.println(DateTime.now().withDayOfWeek(7));
		System.out.println(DateTime.now().withDayOfYear(1));
	}
}
