package com.lutongnet.base.util;

import java.io.InputStream;

import java.io.UnsupportedEncodingException;

import org.junit.Test;

import com.lutongnet.base.util.IOSystem;
import com.lutongnet.base.util.algorithm.Base64;

public class Base64Test {
	
	public static void main(String[] args) throws UnsupportedEncodingException {
		// ""
		String result = Base64.decodeToString("yta7+rGo0rXO8b341bnH6b/2");
		System.out.println(result);
		
		byte[] bytes = Base64.decode("yta7+rGo0rXO8b341bnH6b/2");
		System.out.println(new String(bytes, "gb2312"));
		
		bytes = Base64.decode("J87i0MfX3Cc=");
		System.out.println(new String(bytes, "gb2312"));
		bytes = Base64.decode("yta7+rGo0rXO8dfUt/7O8c+1zbO/qrei0OjH8y5kb2N4");
		System.out.println(new String(bytes, "gb2312"));
	}
	
	@Test
	public void testDoc(){
		InputStream inputStream = Base64Test.class.getResourceAsStream("Base64Docx.txt");
		byte[] docxBytes = Base64.decode(IOSystem.readToBytes(inputStream));
		
		IOSystem.writeTo(docxBytes, "C:/Users/tianjp/Desktop/xxx.docx");
	}
}
