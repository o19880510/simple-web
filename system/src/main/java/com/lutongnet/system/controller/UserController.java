package com.lutongnet.system.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
import com.lutongnet.system.constant.AppConstant;
import com.lutongnet.system.constant.PageConstants;
import com.lutongnet.system.model.entity.User;
import com.lutongnet.system.model.request.UserReq;
import com.lutongnet.system.model.vo.UserInfo;
import com.lutongnet.system.service.UserService;
import com.lutongnet.system.util.HttpUtils;


@Controller
@RequestMapping(value = "/system")
public class UserController {
	
	private static Logger log	= LoggerFactory.getLogger(UserController.class);
	
	@Resource(name = "userService")
	private UserService	userService;

	/**
	 * 用户列表
	 * 
	 * @param user
	 * @param model
	 * @return
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/user/list")
	@MarkRequest
	@Log("查询用户")
	public String list(@ModelAttribute("user") UserReq userReq, Model model, HttpServletRequest request) {

		userService.list(userReq, model, request);
		return PageConstants.User.LIST;
	}

	/**
	 * 初始化新增表单
	 * 
	 * @param user
	 * @return
	 */
	@RequestMapping(value = "/user/add", method = RequestMethod.GET)
	public String addForm(Model model) {

		userService.addForm(model);

		return PageConstants.User.ADD;
	}

	/**
	 * 新增用户
	 */
	@RequestMapping(value = "/user/add", method = RequestMethod.POST)
	@Log("新增用户")
	public String add(@Valid @ModelAttribute User user, BindingResult result, RedirectAttributes ra, Model model,
			HttpServletRequest request) {
		if (result.hasErrors()) {
			return PageConstants.User.ADD;
		}
		
		userService.add(user, result, model);

		if (result.hasErrors()) {
			return PageConstants.User.ADD;
		}
		ra.addFlashAttribute("actionResult", new SuccessActionResult());
		return "redirect:" + HttpUtils.getMarkRequestInfo(request, "returnURI", PageConstants.User.LIST);
	}

	/**
	 * 初始化修改表单
	 * 
	 * @param user
	 * @return
	 */
	@RequestMapping(value = "/user/update", method = RequestMethod.GET)
	public String updateForm(@RequestParam Integer id, Model model) {

		userService.updateForm(id, model);
		return PageConstants.User.UPDATE;
	}

	@RequestMapping(value = "/user/update", method = RequestMethod.POST)
	@Log("修改用户")
	public String update(@Valid @ModelAttribute User user, BindingResult result, RedirectAttributes ra, Model model,
			HttpServletRequest request) {

		userService.update(user, result, model);

		if (result.hasErrors()) {
			return PageConstants.User.UPDATE;
		}
		ra.addFlashAttribute("actionResult", new SuccessActionResult());
		return "redirect:" + HttpUtils.getMarkRequestInfo(request, "returnURI", PageConstants.User.LIST);
	}

	/**
	 * 删除用户
	 * 
	 * @param id
	 * @param ra
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/user/remove", method = RequestMethod.GET)
	@Log("删除用户")
	public String remove(Integer id, RedirectAttributes ra, HttpServletRequest request) {
		userService.remove(id);
		ra.addFlashAttribute("actionResult", new SuccessActionResult());
		return "redirect:" + HttpUtils.getMarkRequestInfo(request, "returnURI", PageConstants.User.LIST);
	}

	/**
	 * 禁用用户
	 * 
	 * @param id
	 * @param ra
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/user/disable", method = RequestMethod.GET)
	@Log("禁用用户")
	public String disable(Integer id, RedirectAttributes ra, HttpServletRequest request) {
		userService.updateStatus(id, User.Status.DISABLE.name());
		ra.addFlashAttribute("actionResult", new SuccessActionResult());
		return "redirect:" + HttpUtils.getMarkRequestInfo(request, "returnURI", PageConstants.User.LIST);
	}

	/**
	 * 启用用户
	 * 
	 * @param id
	 * @param ra
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/user/enable", method = RequestMethod.GET)
	@Log("启用用户")
	public String enable(Integer id, RedirectAttributes ra, HttpServletRequest request) {
		userService.updateStatus(id, User.Status.ENABLE.name());
		ra.addFlashAttribute("actionResult", new SuccessActionResult());
		return "redirect:" + HttpUtils.getMarkRequestInfo(request, "returnURI", PageConstants.User.LIST);
	}

	/**
	 * 初始化更改自己的密码表单
	 * 
	 * @param user
	 * @param session
	 * @return
	 */
	@RequestMapping(value = "/user/change_self_password", method = RequestMethod.GET)
	public String changeSelfPasswordForm(@ModelAttribute("user") UserReq user, HttpSession session) {
		UserInfo ui = (UserInfo) session.getAttribute(AppConstant.USER_INFO);
		user.setUserid(ui.getUserid());
		return PageConstants.User.CHANGE_SELF_PASSWORD;
	}

	/**
	 * 更改自己的密码
	 * 
	 * @param user
	 * @param session
	 * @return
	 */
	@RequestMapping(value = "/user/change_self_password", method = RequestMethod.POST)
	public String changeSelfPassword(@ModelAttribute("user") UserReq userReq, BindingResult result,
			HttpServletRequest request) {

		userService.changeSelfPassword(userReq, result, request);

		if (result.hasErrors()) {
			return PageConstants.User.CHANGE_SELF_PASSWORD;
		}
		request.setAttribute("actionResult", new SuccessActionResult());
		return PageConstants.User.CHANGE_SELF_PASSWORD;
	}

	/**
	 * 初始化修改密码表单
	 * 
	 * @param id
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/user/change_password", method = RequestMethod.GET)
	public String changePasswordForm(Integer id, Model model) {

		userService.changePasswordForm(id, model);

		return PageConstants.User.CHANGE_PASSWORD;
	}

	/**
	 * 修改密码
	 * 
	 * @param user
	 * @param ra
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/user/change_password", method = RequestMethod.POST)
	@Log("修改密码")
	public String changePassword(@ModelAttribute("user") UserReq userReq, RedirectAttributes ra,
			HttpServletRequest request) {

		userService.changePassword(userReq);
		ra.addFlashAttribute("actionResult", new SuccessActionResult());
		return "redirect:" + HttpUtils.getMarkRequestInfo(request, "returnURI", PageConstants.User.LIST);
	}
}
