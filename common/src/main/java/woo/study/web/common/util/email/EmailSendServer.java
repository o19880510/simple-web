package woo.study.web.common.util.email;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import woo.study.web.common.util.IOSystem;


/**
 * 
 * Email 发送服务
 * 
 * @author tianjp
 *
 */
public class EmailSendServer {
	
	private static Logger log	= LoggerFactory.getLogger(EmailSendServer.class);
	
	private String host;
	private String sender;
	private String senderPwd;
	private List<EmailReceiver>	receiver;
	
	private Session mailSession ;
	
	public EmailSendServer() {
		receiver = new ArrayList<EmailReceiver>();
	}
	
	public EmailSendServer(String host, String sender, String senderPwd) {
		this.host = host;
		this.sender = sender;
		this.senderPwd = senderPwd;
		receiver = new ArrayList<EmailReceiver>();
		
		init();
	}
	
	private void init(){
		Properties props = new Properties();
		props.put("mail.smtp.host", host);// 指定SMTP服务器
		props.put("mail.smtp.auth", "true");// 指定是否需要SMTP验证

		mailSession = Session.getDefaultInstance(props);
	}
	
	/**
	 * 发送邮件
	 * @param emailDTO 邮件内容
	 */
	public void send(EmailDTO emailDTO){
		if(mailSession == null){
			init();
		}
		
		try {
			Message message = new MimeMessage(mailSession);
			message.setFrom(new InternetAddress(sender));// 发件人
			for(EmailReceiver emailReceiver : this.receiver){
				message.addRecipient(emailReceiver.getType().getMType(), new InternetAddress(emailReceiver.getReceiver()));// 收件人
			}
			
			message.setSubject(emailDTO.getTitle());
			
			EmailContent emailContent = emailDTO.getEmailContent();
			
			switch(emailContent.getType()){
			case TEXT:{
				message.setText(emailContent.getContent());
			}break;
			
			case HTML:{
				MimeMultipart part = new MimeMultipart("related");
				MimeBodyPart body = new MimeBodyPart();
				body.setContent(emailContent.getContent(), EmailContent.Type.HTML.getHeader());
				part.addBodyPart(body);
				
				for(EmailFile eFile : emailContent.getFiles()){
					
					MimeBodyPart imgBody = new MimeBodyPart();
					File file = getFile(eFile);
					if(file != null){
						imgBody.attachFile(file);
					}
					
					String contendId = eFile.getConentId();
					if (contendId != null && !"".equals(contendId.trim())) {
						imgBody.setContentID(contendId);
					}
					part.addBodyPart(imgBody);
				}
				
				log.debug("part.toString()==============="+part.getPreamble());
				
				
				message.setContent(part);
			}break;
			}
			message.saveChanges();
			send(mailSession, message);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private void send(Session mailSession, Message message) {
		try {
			log.debug(IOSystem.readToString(message.getInputStream()));
		} catch (IOException e1) {
			e1.printStackTrace();
		} catch (MessagingException e1) {
			e1.printStackTrace();
		}
		
		try {
			Transport transport = mailSession.getTransport("smtp");
			transport.connect(host, sender, senderPwd);
			transport.sendMessage(message, message.getAllRecipients());
			transport.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private File getFile(EmailFile eFile){
		File file = eFile.getFile();
		if(file == null || !file.exists()){
			String filePath = eFile.getFilePath();
			File _file = new File(filePath);
			if(_file.exists()){
				file = _file;
			}else{
				return null;
			}
		}
		
		return file;
	}
	
	public void addReceiver(EmailReceiver emailReceiver ){
		receiver.add(emailReceiver);
	}
	
	public void removeReceiver(EmailReceiver emailReceiver ){
		receiver.remove(emailReceiver);
	}
	
	public void setReceiver(List<EmailReceiver>	receiver){
		this.receiver = receiver;
	}
	
	
	public String getHost() {
		return host;
	}

	public void setHost(String host) {
		this.host = host;
	}

	public String getSender() {
		return sender;
	}

	public void setSender(String sender) {
		this.sender = sender;
	}

	public String getSenderPwd() {
		return senderPwd;
	}

	public void setSenderPwd(String senderPwd) {
		this.senderPwd = senderPwd;
	}

	public List<EmailReceiver> getReceiver() {
		return receiver;
	}
	
}
