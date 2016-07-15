package woo.study.web.common.util.file;


import java.util.List;

import org.junit.Test;

import woo.study.web.common.util.file.ArchiveHelper;
import woo.study.web.common.util.file.ExcelHelper;

public class ExcelHelperTest {
	
	@Test
	public void testRar(){
		List list= ExcelHelper.importExcel(ExcelHelperTest.class.getResourceAsStream("user_import.xls"));
		System.out.println(list);
	}
}
