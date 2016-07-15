package woo.study.web.system.controller;

import javax.annotation.Resource;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import woo.study.web.system.constant.PageConstants;
import woo.study.web.system.model.entity.ActionLog;
import woo.study.web.system.model.request.LogReq;
import woo.study.web.system.service.LogService;


@Controller
@RequestMapping ( value = "/system" )
public class LogController {

	@Resource ( name = "logService" )
	private LogService	logService;

	@RequestMapping ( value = "/log/list" )
	public String list ( @ModelAttribute ( "log" ) LogReq logReq , Model model ) {
		 logService.list(logReq, model);
		return PageConstants.Log.LIST;
	}

	@RequestMapping ( value = "/log/{id}" , produces = "application/json" )
	@ResponseBody
	public ActionLog detail ( @PathVariable Integer id ) {
		return logService.get ( id );
	}
}
