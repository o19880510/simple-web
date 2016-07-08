package com.lutongnet.base.util;

import java.io.InputStream;
import java.net.URL;

import org.junit.Test;

import com.lutongnet.base.util.IOSystem;

public class IOSystemTest {
	
	public static void main(String[] args) {
		URL url = IOSystemTest.class.getResource("");
		System.out.println("url :" + url);
		
		URL url2 = IOSystemTest.class.getResource("/");
		System.out.println("url2:" + url2);
		
		System.out.println(IOSystem.getClasspath());
		
		boolean exist = IOSystem.isFileExist(IOSystem.getClasspath()+"codetable-config.xml");
		System.out.println(exist);
		
		String configFile = IOSystem.readToString(IOSystem.getClasspath()+"codetable-config.xml");
		System.out.println(configFile);
	}
	
	@Test
	public void test(){
		InputStream in = IOSystem.getInputStream("C:/Users/tianjp/Desktop/ExcelHelper.java");
		IOSystem.writeTo(in, "C:/Users/tianjp/Desktop/ExcelHelper.txt.java");
	}
}
