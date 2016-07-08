package com.lutongnet.system.dao;

import javax.annotation.Resource;

import org.junit.Test;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.lutongnet.system.dao.UserDao;

import test.tools.BaseTests;

public class UserDaoTest extends BaseTests{
	
	@Resource(name = "userDao")
	private UserDao userDao;
	
	@Test
	@Transactional(propagation = Propagation.SUPPORTS)
	public void test(){
//		userDao.test();
	}
}


// DriverManager