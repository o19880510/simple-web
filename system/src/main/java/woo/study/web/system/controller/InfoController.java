package woo.study.web.system.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import woo.study.web.system.constant.PageConstants;


@Controller
@RequestMapping ( value = "/system" )
public class InfoController {

	@RequestMapping ( value = "/info/denied" )
	public String denied ( Model model ) {
		model.addAttribute ( "info" , "权限不足" );
		return PageConstants.Base.INFO;
	}
}
