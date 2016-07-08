package com.lutongnet.base.util;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import org.joda.time.DateTime;
import org.junit.Test;

import com.lutongnet.base.util.IOSystem;
import com.lutongnet.base.util.file.ExcelDTO;
import com.lutongnet.base.util.file.ExcelHelper;

public class ExcelHelperTest {

	@Test
	public void genExcel(){
		
		List<String> title = new ArrayList<String>();
		title.add("第1列");
		title.add("第2列");
		title.add("第3列");
		title.add("第4列");
		title.add("第5列");
		
		List<List<Object>> dataList = new ArrayList<List<Object>>();
		List<Object> rowDataList = new ArrayList<Object>();
		rowDataList.add("String");
		rowDataList.add(new Date(System.currentTimeMillis()));
		rowDataList.add(DateTime.now());
		rowDataList.add(1);
		rowDataList.add(1.99);
		dataList.add(rowDataList);
		
		byte[] excelDatas = ExcelHelper.export(new ExcelDTO(title, dataList));
		
		IOSystem.writeTo(excelDatas, "test.xls");
	}
}
