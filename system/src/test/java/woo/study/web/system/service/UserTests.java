package woo.study.web.system.service;

import org.junit.Test;

import org.springframework.beans.factory.annotation.Autowired;

import test.tools.BaseTests;
import woo.study.web.system.constant.AppConstant;
import woo.study.web.system.dao.UserDao;
import woo.study.web.system.model.entity.User;
import woo.study.web.system.service.UserService;


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
