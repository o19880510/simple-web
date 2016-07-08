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
import com.lutongnet.system.model.entity.Role;
import com.lutongnet.system.model.request.RoleReq;
import com.lutongnet.system.service.RoleService;
import com.lutongnet.system.util.HttpUtils;


@SuppressWarnings ( "unchecked" )
@Controller
@RequestMapping ( value = "/system" )
public class RoleController {

	@Resource ( name = "roleService" )
	private RoleService				roleService;

	/**
	 * 查询角色
	 * 
	 * @param role
	 * @param model
	 * @return
	 */
	@RequestMapping ( value = "/role/list" )
	@MarkRequest
	@Log ( "查询角色" )
	public String list ( @ModelAttribute ( "role" ) RoleReq roleReq , Model model ) {
		roleService.list(roleReq, model);
		return PageConstants.Role.LIST;
	}

	/**
	 * 查看角色信息
	 * 
	 * @param id
	 * @param model
	 * @return
	 */
	@RequestMapping ( value = "/role/view" , method = RequestMethod.GET )
	public String view ( @RequestParam Integer id , Model model ) {
		 roleService.getRoleInfos(id, model);
		return PageConstants.Role.VIEW;
	}


	/**
	 * 初始化新增表单
	 * 
	 * @param role
	 * @return
	 */
	@RequestMapping ( value = "/role/add" , method = RequestMethod.GET )
	public String addForm ( Model model ) {
		roleService.addForm(model);
		return PageConstants.Role.ADD;
	}

	/**
	 * 新增角色
	 */
	@RequestMapping ( value = "/role/add" , method = RequestMethod.POST )
	@Log ( "新增角色" )
	public String add ( @Valid @ModelAttribute ( "role" ) Role role , BindingResult result , RedirectAttributes ra , Model model ,
			HttpServletRequest request ) {
		
		roleService.add(role, result, model);

		if ( result.hasErrors ( ) ){
			return PageConstants.Role.ADD;
		}
		ra.addFlashAttribute ( "actionResult" , new SuccessActionResult ( ) );
		return "redirect:" + HttpUtils.getMarkRequestInfo ( request , "returnURI" , "system/role/list.do" );
	}

	/**
	 * 初始化修改表单
	 * 
	 * @param id
	 * @param model
	 * @return
	 */
	@RequestMapping ( value = "/role/update" , method = RequestMethod.GET )
	public String updateForm ( @RequestParam Integer id , Model model ) {
		 roleService.updateForm(id, model);
		return PageConstants.Role.UPDATE;
	}

	/**
	 * 修改角色
	 * 
	 * @param role
	 * @param result
	 * @param ra
	 * @param model
	 * @return
	 */
	@RequestMapping ( value = "/role/update" , method = RequestMethod.POST )
	@Log ( "修改角色" )
	public String update ( @Valid @ModelAttribute ( "role" ) Role role , BindingResult result , RedirectAttributes ra , Model model ,
			HttpServletRequest request ) {
		
		roleService.update(role, result, model);

		if ( result.hasErrors ( ) ){
			return  PageConstants.Role.UPDATE;
		}
		
		ra.addFlashAttribute ( "actionResult" , new SuccessActionResult ( ) );
		return "redirect:" + HttpUtils.getMarkRequestInfo ( request , "returnURI" );
	}

	/**
	 * 删除角色
	 * 
	 * @param id
	 * @param ra
	 * @param request
	 * @return
	 */
	@RequestMapping ( value = "/role/remove" , method = RequestMethod.GET )
	@Log ( "删除角色" )
	public String remove ( @RequestParam Integer id , RedirectAttributes ra , HttpServletRequest request ) {
		roleService.remove ( id );
		ra.addFlashAttribute ( "actionResult" , new SuccessActionResult ( ) );
		return "redirect:" + HttpUtils.getMarkRequestInfo ( request , "returnURI" );
	}
}
