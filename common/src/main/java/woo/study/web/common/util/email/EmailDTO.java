package woo.study.web.common.util.email;

/**
 * Email 邮件数据对象，包含标题和正文（文本和HTML富文本）
 * 
 * @author tianjp
 *
 */
public class EmailDTO {

	private String				title;
	private EmailContent		emailContent;

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public EmailContent getEmailContent() {
		return emailContent;
	}

	public void setEmailContent(EmailContent emailContent) {
		this.emailContent = emailContent;
	}
	
	@Override
	public String toString() {
		return "EmailDTO [title=" + title + ", emailContent=" + emailContent + "]";
	}

}
