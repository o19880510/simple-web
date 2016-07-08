package com.lutongnet.system.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.lutongnet.system.constant.PageConstants;


@Controller
public class MainController {

	@RequestMapping ( "/system/main" )
	public String defaultHandler ( ) {
		return PageConstants.Base.MAIN;
	}
}
