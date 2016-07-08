package com.lutongnet.system.controller;

import java.util.List;





import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.lutongnet.base.functions.datacenter.DataLoadManagement;
import com.lutongnet.base.functions.datacenter.DataLoadable;
import com.lutongnet.base.functions.datacenter.DataManagementHelper;
import com.lutongnet.base.functions.datacenter.loader.DataLoader;
import com.lutongnet.base.message.SuccessActionResult;
import com.lutongnet.system.annotation.MarkRequest;
import com.lutongnet.system.constant.PageConstants;
import com.lutongnet.system.util.HttpUtils;

/**
 * 
 * 管理内存数据。包含展示，加载等功能
 * 
 * @author tianjp
 *
 */
@Controller
@RequestMapping ( value = "/system/data" )
public class SysMemDataController {
	
	private static Logger log	= LoggerFactory.getLogger(SysMemDataController.class);
	
	@Resource(name = "dataLoadManagement")
	private DataLoadable dataLoadManagement;
	
	@RequestMapping(value = "/list")
	@MarkRequest
	public String getAllDataNames(HttpServletRequest request) {
		
		List<DataLoader> loaderList = ((DataLoadManagement)dataLoadManagement).getDataLoaderList();
		request.setAttribute("loaderList", loaderList);
		
		return PageConstants.Data.LIST;
	}
	
	@RequestMapping(value = "/detail")
	public String getDetails(HttpServletRequest request) {
		String dataName = request.getParameter("dataName");
		
		Object dataObj = DataManagementHelper.getDataContainer().get(dataName, Object.class);
		request.setAttribute("memData", dataObj);
		
		log.debug("getDetails getDataContainer=" + DataManagementHelper.getDataContainer());
		log.debug("getDetails dataObj=" + dataObj);
		
		return PageConstants.Data.DETAIL;
	}
	
	@RequestMapping(value = "/reload")
	public String reload(HttpServletRequest request) {
		String dataName = request.getParameter("dataName");
		
		dataLoadManagement.load(dataName);
		
		Object dataObj = DataManagementHelper.getDataContainer().get(dataName, Object.class);
		request.setAttribute("memData", dataObj);
		
		return PageConstants.Data.DETAIL;
	}
	
	
	@RequestMapping(value = "/reload_all")
	public String loadAll(HttpServletRequest request, RedirectAttributes ra) {
		
		dataLoadManagement.loadAll();
		
		ra.addFlashAttribute("actionResult", new SuccessActionResult());
		return "redirect:" + HttpUtils.getMarkRequestInfo(request, "returnURI", PageConstants.Data.LIST);
	}
	
}
