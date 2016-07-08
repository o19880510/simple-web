package com.lutongnet.summary.controller;

import java.util.ArrayList;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.joda.time.LocalDate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.lutongnet.base.dao.vo.PaginationBean;
import com.lutongnet.base.util.HttpResponseUtils;
import com.lutongnet.base.util.file.ExcelDTO;
import com.lutongnet.base.util.file.ExcelHelper;
import com.lutongnet.summary.constant.PageConstant;
import com.lutongnet.summary.model.entity.DataSummary;
import com.lutongnet.summary.model.entity.DataSummaryIndicator;
import com.lutongnet.summary.model.req.StatisticReq;
import com.lutongnet.summary.model.rsp.StatisticRsp;
import com.lutongnet.summary.service.SummaryDataService;
import com.lutongnet.system.annotation.MarkRequest;

@Controller
@RequestMapping(value = "/system/statistic")
public class SummaryDataController {

	private static Logger	log	= LoggerFactory.getLogger(SummaryDataController.class);
	
	@Autowired
	private SummaryDataService summaryDataService;
	
	@MarkRequest
	@RequestMapping(value = "/query")
	public String list(@ModelAttribute("statisticReq") StatisticReq statisticReq, HttpServletRequest request) {
		summaryDataService.getPage(statisticReq, request);
		
		return PageConstant.QUERY;
	}

	@RequestMapping(value = "/export")
	public void resend(@ModelAttribute("statisticReq") StatisticReq statisticReq, HttpServletRequest request, HttpServletResponse response) {
		
		summaryDataService.getPage(statisticReq, request);
		
		List<String> titles = new ArrayList<String>();
		List<List<Object>> dataList = new ArrayList<List<Object>>();
		
		List<DataSummaryIndicator> dataSummaryIndicators = (List<DataSummaryIndicator>) request.getAttribute("dataSummaryIndicators");
		PaginationBean<StatisticRsp> statisticRspPage = (PaginationBean<StatisticRsp>) request.getAttribute("statisticRspPage");
		
		for(StatisticRsp statisticRsp: statisticRspPage.getDataList()){
			Map<LocalDate, Map<String, DataSummary>> dataMap = statisticRsp.getDataMap();
			
			{
				titles.add("日期");
				for(DataSummaryIndicator dataSummaryIndicator : dataSummaryIndicators){
					titles.add(dataSummaryIndicator.getNameChi());
				}
			}
			
			{	
				for(Map.Entry<LocalDate, Map<String, DataSummary>> dataSummaryEntry : dataMap.entrySet()){
					List<Object> record = new ArrayList<Object>();
					
					record.add(dataSummaryEntry.getKey());
					
					for(DataSummaryIndicator dataSummaryIndicator : dataSummaryIndicators){
						String key = dataSummaryIndicator.getNameEng();
						DataSummary dataSummary = dataSummaryEntry.getValue().get(key);
						if(dataSummary != null){
							record.add(dataSummary.getDataValue());
						}else{
							record.add("");
						}
					}
					dataList.add(record);
				}
			}
		}
		
		ExcelDTO excelDTOs = new ExcelDTO(titles, dataList);
		byte[] excelDatas = ExcelHelper.export(excelDTOs);
		
		HttpResponseUtils.writeExcel(response, "数据统计.xls", excelDatas);
	}
}
