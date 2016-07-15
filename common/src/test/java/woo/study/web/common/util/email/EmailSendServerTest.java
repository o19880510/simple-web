package woo.study.web.common.util.email;

import java.io.File;

import javax.mail.Message;

import woo.study.web.common.util.email.EmailContent;
import woo.study.web.common.util.email.EmailDTO;
import woo.study.web.common.util.email.EmailFile;
import woo.study.web.common.util.email.EmailReceiver;
import woo.study.web.common.util.email.EmailSendServer;

public class EmailSendServerTest {
	
	static String title = "测试邮箱";
	static String context = "<h4>邮件测试</h4>";
	static String server126 = "smtp.126.com";
	static String sender = "newtjp@126.com";
	static String recever = "tianjinpeng@126.com";
	static String senderUsername = "newtjp@126.com";
	static String senderPassword = "8899000";
	
	
	public static void main(String[] args) throws InterruptedException {
		
		Thread.currentThread().setContextClassLoader( EmailSendServerTest.class.getClassLoader() );
		
		EmailSendServer emailSendServer = new EmailSendServer(server126, sender, senderPassword);
		emailSendServer.addReceiver(new EmailReceiver(recever, EmailReceiver.Type.TO));
//		emailSendServer.send(testText());
//		Thread.currentThread().sleep(60 * 1000);
		emailSendServer.send(testHtml());
		
		
	}
	
	static EmailDTO testText(){
		EmailDTO emailDTO = new EmailDTO();
		emailDTO.setTitle(title);
		emailDTO.setEmailContent(new EmailContent("文本内容"));
		
		return emailDTO;
	}
	
	static EmailDTO testHtml(){
		EmailDTO emailDTO = new EmailDTO();
		emailDTO.setTitle(title);
		
		EmailContent emailContent = new EmailContent("这是测试邮件，不是垃圾邮件！你大爷的！<br><img src='cid:p1'/><img src='cid:p2'/><img src='cid:p3'/>", EmailContent.Type.HTML);
		
		emailContent.add(new EmailFile("p1", "C:/Users/tianjp/Downloads/1419216260_476932.jpg"));
		emailContent.add(new EmailFile("p2", "C:/Users/tianjp/Downloads/1419216260_476932.jpg"));
		emailContent.add(new EmailFile("C:/Users/tianjp/Downloads/1419216260_476932.jpg"));
//		emailContent.add(new EmailFile("/home/tianjp/下载/在eclipse中实施重构.pdf"));
		
		emailDTO.setEmailContent(emailContent);
		
		return emailDTO;
	}
	
}
