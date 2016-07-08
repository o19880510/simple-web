package com.lutongnet.base.util.email;

import javax.annotation.Resource;

import org.junit.Test;

import test.tools.BaseTests;

import com.lutongnet.base.util.email.EmailContent;
import com.lutongnet.base.util.email.EmailDTO;
import com.lutongnet.base.util.email.EmailFile;
import com.lutongnet.base.util.email.EmailReceiver;
import com.lutongnet.base.util.email.EmailSendServer;

public class EmailSendServerSpringTest  extends BaseTests{
	
	static String title = "测试邮箱";
	
	@Resource (name = "emailSendServer")
	private EmailSendServer emailSendServer;
	
	@Test
	public void testText(){
		EmailDTO emailDTO = new EmailDTO();
		emailDTO.setTitle(title);
		emailDTO.setEmailContent(new EmailContent("哈哈"));
		
		emailSendServer.send(emailDTO);
	}
	
	@Test
	public void testHtml(){
		EmailDTO emailDTO = new EmailDTO();
		emailDTO.setTitle(title);
		
		EmailContent emailContent = new EmailContent("这是测试邮件，不是垃圾邮件！你大爷的！<br><img src='cid:p1'/><img src='cid:p2'/><img src='cid:p3'/>", EmailContent.Type.HTML);
		
		emailContent.add(new EmailFile("p1", "/home/tianjp/下载/2014-09-03pic/1406106558_166670.jpg"));
		emailContent.add(new EmailFile("p2", "/home/tianjp/下载/2014-09-03pic/1355759_689d13a61af9cf13b9c637d25a250b16_1406703355.jpg"));
		emailContent.add(new EmailFile("/home/tianjp/下载/2014-09-03pic/1406539649_778769.jpg"));
//		emailContent.add(new EmailFile("/home/tianjp/下载/在eclipse中实施重构.pdf"));
		
		emailDTO.setEmailContent(emailContent);
		
		emailSendServer.send(emailDTO);
	}
	
}
