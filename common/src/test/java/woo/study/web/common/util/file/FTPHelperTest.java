package woo.study.web.common.util.file;


import org.junit.Test;

import woo.study.web.common.util.IOSystem;
import woo.study.web.common.util.file.FTPHelper;

public class FTPHelperTest {
	
	public static void main(String[] args) {
		FTPHelper ftpHelper = null;
		ftpHelper = new FTPHelper("172.16.4.250", 40001);
		boolean loginSuccess =  ftpHelper.login("ftp_lutong", "ftp_lutong");
		System.out.println("loginSuccess:" + loginSuccess);
		
		ftpHelper.listRootFiles();
		//ftpHelper.download("/a.txt", "d:/a1111.txt");
//		ftpHelper.uploadBinaryFile(IOSystem.getInputStream("d://1.jpg"), "/A/B/11113.jpg");
		ftpHelper.uploadAsciiFile("d://a.txt", "A/B/C/tianjp2.txt");
	}
	
	
	FTPHelper ftpHelper = null;
	
	public FTPHelperTest(){
		ftpHelper = new FTPHelper("172.16.4.159");
		boolean loginSuccess =  ftpHelper.login("tianjp", "tianjp");
		System.out.println("loginSuccess:" + loginSuccess);
	}
	
	
	@Test
	public void testUploadFile(){
		boolean upload = ftpHelper.uploadBinaryFile("/home/tianjp/桌面/Velocity语法.doc", "D/ftptest/E/汉字/Velocity语法.doc");
		
		System.out.println("upload:" + upload);
	}
	
	@Test
	public void testUploadTxtFile(){
		boolean upload = ftpHelper.uploadAsciiFile("/home/tianjp/桌面/无标题文档", "D/ftptest/TXT/无标题文档");
		
		System.out.println("upload:" + upload);
	}
	
	@Test
	public void testRemoveFile(){
		boolean remove = ftpHelper.removeFile("D/ftptest/TXT/无标题文档");
		
		System.out.println("remove:" + remove);
	}
	
	@Test
	public void testRemoveDir(){
		boolean remove = ftpHelper.removeDir("D/ftptest/TXT");
		
		System.out.println("remove:" + remove);
	}
	
	@Test
	public void testDownload(){
		boolean download = ftpHelper.download("D/ftptest/E/汉字/Velocity语法.doc", "/home/tianjp/D/ftptest/cpoy.doc");
		
		System.out.println("download:" + download);
	}
}
