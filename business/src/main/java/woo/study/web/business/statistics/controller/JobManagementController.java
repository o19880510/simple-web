package woo.study.web.business.statistics.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import woo.study.web.business.statistics.contant.PageContants;
import woo.study.web.business.statistics.service.ScheduleJobService;
import woo.study.web.common.message.SuccessActionResult;
import woo.study.web.common.util.value.HttpRequestParamValueHelper;
import woo.study.web.common.util.value.ValueHelper;

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
