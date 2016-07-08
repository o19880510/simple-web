package com.lutongnet.system.controller;

import javax.annotation.Resource;



import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.lutongnet.base.message.SuccessActionResult;
import com.lutongnet.system.annotation.Log;
import com.lutongnet.system.annotation.MarkRequest;
import com.lutongnet.system.constant.PageConstants;
import com.lutongnet.system.model.entity.Param;
import com.lutongnet.system.model.entity.ParamItem;
import com.lutongnet.system.model.request.ParamReq;
import com.lutongnet.system.service.ParamService;
import com.lutongnet.system.util.HttpUtils;


@Controller
@RequestMapping ( value = "/system" )
public class ParamController {

	@Resource(name = "paramService")
	private ParamService paramService;

	/**
	 * 参数列表
	 * 
	 * @param param
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/param/list")
	@MarkRequest
	@Log("查询参数")
	public String list(@ModelAttribute("PARAM") ParamReq paramReq, Model model) {
		paramService.list(paramReq, model);
		return PageConstants.Param.LIST;
	}

	/**
	 * 查看参数
	 * 
	 * @param id
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/param/view")
	public String view(@RequestParam Integer id, Model model) {
		paramService.get(id, model);
		return PageConstants.Param.VIEW;
	}

	/**
	 * 初始化新增表单
	 * 
	 * @param param
	 * @return
	 */
	@RequestMapping(value = "/param/add", method = RequestMethod.GET)
	public String addForm(Model model) {
		Param param = new Param();
		ParamItem item = new ParamItem();
		param.addItem(item);
		model.addAttribute("PARAM", param);
		return PageConstants.Param.ADD;
	}

	/**
	 * 新增参数
	 * 
	 * @param param
	 * @param result
	 * @param ra
	 * @param model
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/param/add", method = RequestMethod.POST)
	@Log("新增参数")
	public String add(@Valid @ModelAttribute("PARAM") Param param,
			BindingResult result, RedirectAttributes ra, Model model,
			HttpServletRequest request) {

		if (result.hasErrors()) {
			return PageConstants.Param.ADD;
		}

		paramService.add(param, result, model, request);
		if (result.hasErrors()) {
			return PageConstants.Param.ADD;
		}

		ra.addFlashAttribute("actionResult", new SuccessActionResult());

		return "redirect:"+ HttpUtils.getMarkRequestInfo(request, "returnURI",	PageConstants.Param.LIST);
	}

	/**
	 * 初始化修改表单
	 * 
	 * @param param
	 * @return
	 */
	@RequestMapping(value = "/param/update", method = RequestMethod.GET)
	public String update(@RequestParam Integer id, Model model) {
		paramService.get(id, model);
		return PageConstants.Param.UPDATE;
	}

	/**
	 * 修改参数
	 * 
	 * @param param
	 * @param result
	 * @param ra
	 * @param model
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/param/update", method = RequestMethod.POST)
	@Log("修改参数")
	public String update(@Valid @ModelAttribute("PARAM") Param param,
			BindingResult result, RedirectAttributes ra, Model model,
			HttpServletRequest request) {

		if (result.hasErrors()) {
			return PageConstants.Param.UPDATE;
		}
		
		paramService.update(param, result, model, request);
		
		if (result.hasErrors()) {
			return PageConstants.Param.UPDATE;
		}
		ra.addFlashAttribute("actionResult", new SuccessActionResult());

		return "redirect:" + HttpUtils.getMarkRequestInfo(request, "returnURI", PageConstants.Param.LIST);
	}

	/**
	 * 删除参数
	 * 
	 * @param id
	 * @param ra
	 * @return
	 */
	@RequestMapping(value = "/param/remove", method = RequestMethod.GET)
	@Log("删除参数")
	public String remove(@RequestParam Integer id, RedirectAttributes ra,
			HttpServletRequest request) {
		paramService.remove(id, request);
		ra.addFlashAttribute("actionResult", new SuccessActionResult());
		return "redirect:" + HttpUtils.getMarkRequestInfo(request, "returnURI", PageConstants.Param.LIST);
	}
}
