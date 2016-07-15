package woo.study.web.system.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import woo.study.web.system.constant.PageConstants;


@Controller
public class MainController {

	@RequestMapping ( "/system/main" )
	public String defaultHandler ( ) {
		return PageConstants.Base.MAIN;
	}
}
