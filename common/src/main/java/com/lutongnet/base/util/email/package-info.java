/**
 * 
 * 提供邮件发送功能
 * <br>
 * 代码示例
 * <br>
 * EmailSendServer emailSendServer = new EmailSendServer("smtp.126.com", "sender&#64;126.com", "passwd");<br>
 * emailSendServer.addReceiver(new EmailReceiver("receiver&#64;126.com", EmailReceiver.Type.TO));<br>
 * emailSendServer.send(new EmailDTO()); <br>
 * <br>
 * //文本内容<br>
 * EmailDTO emailDTO = new EmailDTO();<br>
 * emailDTO.setTitle("邮件标题");<br>
 * emailDTO.setEmailContent(new EmailContent("文本内容"));<br>
 * <br>
 * //富文本内容<br>
 * EmailDTO emailDTO = new EmailDTO();<br>
 * emailDTO.setTitle("邮件标题");<br>
 * <br>
 * EmailContent emailContent = new EmailContent("邮件正文，HTML格式。这是测试邮件!&lt;img src='cid:p1'/&gt;&lt;img src='cid:p2'/&gt;&lt;img src='cid:p3'/&gt;", EmailContent.Type.HTML);<br>
 * <br>
 * emailContent.add(new EmailFile("p1", "1.jpg")); // p1 与html内容中的&lt;img src='cid:p1'/&gt;相关<br>
 * emailContent.add(new EmailFile("p2", "2.jpg")); // p2 与html内容中的&lt;img src='cid:p3'/&gt;相关<br>
 * emailContent.add(new EmailFile("3.jpg")); // 增加附件<br>
 * <br>
 * <br>
 * <br>
 * 也可以使用spring配置 <br>
 * <br>
 * &lt;bean id="emailSendServer" class="com.lutongnet.base.util.email.EmailSendServer"&gt;<br>
		&lt;property name="host" value="smtp.126.com"&gt;&lt;/property&gt;<br>
		&lt;property name="sender" value="sender@126.com"&gt;&lt;/property&gt;<br>
		&lt;property name="senderPwd" value="******"&gt;&lt;/property&gt;<br>
		&lt;property name="receiver"&gt;<br>
			&lt;list&gt;<br>
				 &lt;bean class="com.lutongnet.base.util.email.EmailReceiver"&gt;<br>
					&lt;property name="receiver" value="receiver@126.com"&gt;&lt;/property&gt;<br>
					&lt;property name="type" value="TO"&gt;&lt;/property&gt;<br>
				 &lt;/bean&gt;<br>
			&lt;/list&gt;<br>
		&lt;/property&gt;<br>
&lt;/bean&gt;<br>

 * @author tianjp
 *
 */
package com.lutongnet.base.util.email;