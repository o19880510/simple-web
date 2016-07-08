package com.lutongnet.system.service;

import org.joda.time.DateTime;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.lutongnet.base.dao.vo.PaginationBean;
import com.lutongnet.system.model.request.LogReq;
import com.lutongnet.system.service.LogService;

import test.tools.BaseTests;
import test.tools.TestModel;


public class LogTests extends BaseTests {

	@Autowired
	private LogService	logService;

	@Test
	public void findByPage ( ) {
		LogReq req = new LogReq ( );
		req.setCurrent ( 1 );
		req.setPageSize ( 5 );
		
		TestModel modelTest = new TestModel();
		logService.list ( req , modelTest);
		PaginationBean pb = (PaginationBean) modelTest.asMap().get("pb");
		System.out.println( "*************************" );
		System.out.println ( pb+"()()()()()()()()()()" );
		System.out.println ( pb.getDataList ( )+"()()()()()()()()" );
	}
}
