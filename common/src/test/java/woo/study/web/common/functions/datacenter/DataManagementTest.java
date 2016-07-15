package woo.study.web.common.functions.datacenter;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import woo.study.web.common.functions.datacenter.DataLoadable;
import woo.study.web.system.constant.AppConstant;

@RunWith ( SpringJUnit4ClassRunner.class )
@ContextConfiguration ( locations = "/root-context.xml" )
public class DataManagementTest {
	
	@Resource(name = "dataLoadManagement")
	private DataLoadable dataLoadManagement;	
	
	@Test
	public void testReload(){
		dataLoadManagement.load(AppConstant.MemDataKeys.CODETABLE_PARAMTERS_MAP, AppConstant.MemDataKeys.CODETABLE_CONFIG);
	}
}
