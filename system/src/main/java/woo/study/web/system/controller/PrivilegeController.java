package woo.study.web.system.controller;

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

import woo.study.web.common.message.SuccessActionResult;
import woo.study.web.system.annotation.Log;
import woo.study.web.system.annotation.MarkRequest;
import woo.study.web.system.constant.PageConstants;
import woo.study.web.system.model.entity.Privilege;
import woo.study.web.system.model.request.PrivilegeReq;
import woo.study.web.system.model.request.PrivilegesAddReq;
import woo.study.web.system.service.PrivilegeService;
import woo.study.web.system.util.HttpUtils;


@Controller
@RequestMapping ( value = "/system" )
public class PrivilegeController {

	@Resource ( name = "privilegeService" )
	private PrivilegeService	privilegeService;

	/**
	 * 初始化新增表单
	 * 
	 * @param model
	 * @return
	 */
	@RequestMapping ( value = "/privilege/add" , method = RequestMethod.GET )
	public String addForm ( Model model ) {
		model.addAttribute ( "privilegesAddReq" , new PrivilegesAddReq ( ) );
		return PageConstants.Privilege.ADD;
	}

	/**
	 * 新增权限
	 * 
	 * @param privilege
	 * @param result
	 * @param ra
	 * @return
	 */
	@RequestMapping ( value = "/privilege/add" , method = RequestMethod.POST )
	@Log ( "新增权限" )
	public String add ( @Valid @ModelAttribute ( "privilegesAddReq" ) PrivilegesAddReq privilegesAddReq , BindingResult result , RedirectAttributes ra ,
			HttpServletRequest request ) {
		if ( result.hasErrors ( ) ){
			return PageConstants.Privilege.ADD;
		}
		
		privilegeService.add(privilegesAddReq, result, request);
		
		if ( result.hasErrors ( ) ){
			return PageConstants.Privilege.ADD;
		}
		
		ra.addFlashAttribute ( "actionResult" , new SuccessActionResult ( ) );
		return "redirect:" + HttpUtils.getMarkRequestInfo ( request );
	}

	/**
	 * 查询权限
	 * 
	 * @param privilege
	 * @return
	 */
	@RequestMapping ( value = "/privilege/list" )
	@MarkRequest
	@Log ( "查询权限" )
	public String list ( @ModelAttribute ( "privilege" ) PrivilegeReq privilege , Model model ) {
		 privilegeService.list(privilege, model);
		return  PageConstants.Privilege.LIST;
	}

	/**
	 * 删除权限
	 * 
	 * @param id
	 * @param ra
	 * @return
	 */
	@RequestMapping ( value = "/privilege/remove" , method = RequestMethod.GET )
	@Log ( "删除权限" )
	public String remove (@RequestParam("group") String group , RedirectAttributes ra , HttpServletRequest request ) {
		privilegeService.remove(group, request);
		ra.addFlashAttribute ( "actionResult" , new SuccessActionResult ( ) );
		return "redirect:" + HttpUtils.getMarkRequestInfo ( request , "returnURI" );
	}

	/**
	 * 初始化修改表单
	 * 
	 * @param id
	 * @param model
	 * @return
	 */
	@RequestMapping ( value = "/privilege/update" , method = RequestMethod.GET )
	public String updateForm ( @RequestParam("group") String group, Model model ) {
		privilegeService.get(group, model);
		return  PageConstants.Privilege.UPDATE;
	}

	/**
	 * 修改权限
	 * 
	 * @param privilege
	 * @param result
	 * @param ra
	 * @param request
	 * @return
	 */
	@RequestMapping ( value = "/privilege/update" , method = RequestMethod.POST )
	@Log ( "修改权限" )
	public String update (  @Valid @ModelAttribute ( "privilegesAddReq" ) PrivilegesAddReq privilegesAddReq, BindingResult result , RedirectAttributes ra , 	HttpServletRequest request ) {
		if ( result.hasErrors ( ) ){
			return PageConstants.Privilege.UPDATE;
		}
		
		privilegeService.update(privilegesAddReq, result, request);
		
		if ( result.hasErrors ( ) ){
			return PageConstants.Privilege.UPDATE;
		}
		ra.addFlashAttribute ( "actionResult" , new SuccessActionResult ( ) );
		return "redirect:" + HttpUtils.getMarkRequestInfo ( request , "returnURI" );
	}
	
	@RequestMapping ( value = "/privilege/detail" )
	@Log ( "查询权限" )
	public String detail ( @RequestParam("group") String group, HttpServletRequest request ) {
		
		privilegeService.detail(group, request);
		
		return PageConstants.Privilege.DETAIL;
	}
}
