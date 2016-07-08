package com.lutongnet.system.service;

import org.junit.Test;

import org.springframework.beans.factory.annotation.Autowired;

import test.tools.BaseTests;

import com.lutongnet.system.constant.AppConstant;
import com.lutongnet.system.dao.UserDao;
import com.lutongnet.system.model.entity.User;
import com.lutongnet.system.service.UserService;


public class UserTests extends BaseTests {

	@Autowired
	private UserService	userService;
	
	@Autowired
	private UserDao	userDao;

	// @Test
	public void get ( ) {
		User user = userService.get ( "zhangfj" , "a" );
		System.out.println ( user == null );
	}
	
	@Test
	public void testDao ( ) {
		
		userService.updateStatus(57, User.Status.OFFLINE.name());
	}
}
