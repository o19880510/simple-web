package com.lutongnet.statistics.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.lutongnet.base.util.HttpResponseUtils;
import com.lutongnet.base.util.JSON2Helper;
import com.lutongnet.statistics.contant.PageContants;
import com.lutongnet.statistics.entity.ScheduleLogNew2;
import com.lutongnet.statistics.service.ScheduleLogStoreService;
import com.lutongnet.statistics.vo.req.JobLogReq;

@Controller
@RequestMapping(value = "/system/statistic/job/log")
public class JobLogController {

	@Resource(name = "scheduleLogStoreService")
	private ScheduleLogStoreService	scheduleLogStoreService;

	@RequestMapping(value = "/list")
	public String jobLogList(@ModelAttribute("jobLogReq") JobLogReq jobLogReq, HttpServletRequest request) {
		scheduleLogStoreService.jobLogPage(jobLogReq, request);
		return PageContants.LIST_LOG;
	}
	
	@RequestMapping(value = "/detail")
	public void jobDetail(@RequestParam("logId") Integer logId, HttpServletRequest request, HttpServletResponse response) {
		
		ScheduleLogNew2 scheduleLogStore = scheduleLogStoreService.jobLogDetail(logId, request);
		HttpResponseUtils.write(response, JSON2Helper.toJson(scheduleLogStore));
	}

}
