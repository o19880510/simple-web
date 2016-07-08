package com.lutongnet.base.util.none;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;

public class PropertiesUpdateTest {
	
	public static void main(String[] args) throws FileNotFoundException, IOException {
		String path = PropertiesUpdateTest.class.getResource("test.properties").getPath();
		System.out.println(path);
		Properties properties = new Properties();
		properties.load(new FileInputStream(path));
		System.out.println(properties);
		
		properties.setProperty("key", "ABC");
		
		properties.store(new FileOutputStream(path), "none");
		
	}
}
