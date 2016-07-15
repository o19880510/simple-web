package woo.study.web.business.statistics.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import woo.study.web.business.statistics.contant.PageContants;
import woo.study.web.business.statistics.entity.ScheduleLogNew2;
import woo.study.web.business.statistics.service.ScheduleLogStoreService;
import woo.study.web.business.statistics.vo.req.JobLogReq;
import woo.study.web.common.util.HttpResponseUtils;
import woo.study.web.common.util.JSON2Helper;

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
