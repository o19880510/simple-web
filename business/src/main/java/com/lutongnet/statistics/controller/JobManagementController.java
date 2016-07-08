package com.lutongnet.statistics.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.lutongnet.base.message.SuccessActionResult;
import com.lutongnet.base.util.value.HttpRequestParamValueHelper;
import com.lutongnet.base.util.value.ValueHelper;
import com.lutongnet.statistics.contant.PageContants;
import com.lutongnet.statistics.service.ScheduleJobService;

@Controller
@RequestMapping(value = "/system/statistic/job")
public class JobManagementController {
	
	@Resource(name = "scheduleJobService")
	private ScheduleJobService scheduleJobService;
	
	@RequestMapping(value = "/list")
	public String jobList(HttpServletRequest request){
		scheduleJobService.list(request);
		return PageContants.LIST_JOB;
	}
	@RequestMapping(value = "/run")
	public String jobRun(HttpServletRequest request){
		
		String jobClass = request.getParameter("jobClass");
		ValueHelper valueHelper = new HttpRequestParamValueHelper(request);
		
		scheduleJobService.invokeJob(jobClass, valueHelper);
		
		scheduleJobService.list(request);
		
		request.setAttribute("actionResult", new SuccessActionResult());
		return PageContants.LIST_JOB;
	}
}
