package com.lutongnet.base.util.email;

import java.util.ArrayList;
import java.util.List;

/**
 * Email 内容对象
 * @author tianjp
 *
 */
public class EmailContent {

	public static enum Type {
		TEXT(null), HTML("text/html;charset=utf-8");
		private String	header;

		private Type(String header) {
			this.header = header;
		}

		public String getHeader() {
			return header;
		}

	}

	private String	content;
	private Type	type;
	private List<EmailFile>	files;

	public EmailContent(String content) {
		this.content = content;
		this.type = Type.TEXT;
		files = new ArrayList<EmailFile>();
	}
	
	public EmailContent(String content, Type type) {
		this(content);
		this.type = type;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Type getType() {
		return type;
	}

	public void setType(Type type) {
		this.type = type;
	}
	
	public List<EmailFile> getFiles() {
		return files;
	}

	public void setFiles(List<EmailFile> files) {
		this.files = files;
	}
	
	public void add(EmailFile emailFile){
		files.add(emailFile);
	}
	
	@Override
	public String toString() {
		return "EmailContent [content=" + content + ", type=" + type + "]";
	}
}
