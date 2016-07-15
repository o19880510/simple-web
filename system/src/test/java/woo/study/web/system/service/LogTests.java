package woo.study.web.system.service;

import org.joda.time.DateTime;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import test.tools.BaseTests;
import test.tools.TestModel;
import woo.study.web.common.dao.vo.PaginationBean;
import woo.study.web.system.model.request.LogReq;
import woo.study.web.system.service.LogService;


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
