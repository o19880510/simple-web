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

import com.lutongnet.base.util.ExceptionUtils;
import com.lutongnet.system.constant.PageConstants;
import com.lutongnet.system.model.request.LoginReq;
import com.lutongnet.system.service.LoginService;
import com.lutongnet.system.service.UserService;


@SuppressWarnings ( "unchecked" )
@Controller
@RequestMapping ( value = "/system" )
public class LoginController {
	
	private static Logger log	= LoggerFactory.getLogger(LoginController.class);
	
	@Resource ( name = "loginService" )
	private LoginService	loginService;

	@RequestMapping ( value = "/login_index" , method = RequestMethod.GET )
	public String loginForm ( Model model ) {
		log.info(ExceptionUtils.MethodInvokeList());
		
		model.addAttribute ( "login" , new LoginReq ( ) );

		return PageConstants.Login.LOGIN;
	}
	
	@RequestMapping ( value = "/logout" , method = RequestMethod.GET )
	public String logout ( HttpSession session ) {
		session.invalidate ( );
		return "redirect:/system/login_index.do";
	}

	@RequestMapping ( value = "/login" , method = RequestMethod.POST )
	public String login ( @Valid @ModelAttribute ( "login" ) LoginReq loginReq , BindingResult result , HttpServletRequest request ) throws Exception {
		if ( result.hasErrors ( ) ){
			return PageConstants.Login.LOGIN;
		}
		
		loginService.login(loginReq, result, request);
		
		Object returnObj = request.getAttribute("userid");
		if(returnObj != null) {
			return PageConstants.Login.LOGIN_FORCE;
		}
		
		if ( result.hasErrors ( ) ){
			return PageConstants.Login.LOGIN;
		}
		return "redirect:/system/index.do";
	}
	
	@RequestMapping(value = "/index")
	public String index() {
		return PageConstants.Base.INDEX;
	}
}
