package woo.study.web.common.util.file;


import org.junit.Test;

import woo.study.web.common.util.file.ArchiveHelper;

public class ArchiveHelperTest {
	
	@Test
	public void testRar(){
		System.out.println(ArchiveHelper.getRarEntryList("/home/tianjp/D/D.rar"));
	}
	

	@Test
	public void testUnrar(){
		ArchiveHelper.unrar("/home/tianjp/D/D.rar", "/home/tianjp/D/D_rar/");
		System.out.println(ArchiveHelper.getRarEntryList("/home/tianjp/D/D.rar"));
	}
	
	@Test
	public void testZip(){
		System.out.println(ArchiveHelper.getZipEntryList("/home/tianjp/D/D.zip"));
	}
	
	
	@Test
	public void testUnzip(){
		ArchiveHelper.unzip("/home/tianjp/D/D.zip", "/home/tianjp/D/D_zip/");
	}
	
	@Test
	public void testZIP() throws Exception{
		ArchiveHelper.zip("/home/tianjp/D/D_rar/",  "/home/tianjp/D/auto01.zip");
		
		System.out.println(ArchiveHelper.getZipEntryList("/home/tianjp/D/auto01.zip"));
	}
}
