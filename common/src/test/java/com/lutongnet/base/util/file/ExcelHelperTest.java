package com.lutongnet.base.util.file;


import java.util.List;

import org.junit.Test;

import com.lutongnet.base.util.file.ArchiveHelper;

public class ExcelHelperTest {
	
	@Test
	public void testRar(){
		List list= ExcelHelper.importExcel(ExcelHelperTest.class.getResourceAsStream("user_import.xls"));
		System.out.println(list);
	}
}
