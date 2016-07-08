package com.lutongnet.system.service;

import javax.annotation.Resource;


import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import test.tools.BaseTests;

import com.lutongnet.system.service.LoginService;


public class LoginTests extends BaseTests {
	
	@Resource(name= "loginService")
	private LoginService loginService;

	@Test
	public void get() {
		loginService.login(null, null, null);
	}
}
