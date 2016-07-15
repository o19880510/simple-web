package woo.study.web.system.dao;

import javax.annotation.Resource;

import org.junit.Test;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import test.tools.BaseTests;
import woo.study.web.system.dao.UserDao;

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