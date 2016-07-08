package com.lutongnet.base.util.email;

import javax.mail.Message;

public class EmailReceiver {
	public static enum Type{
		/**
		 * 发送
		 */
		TO, 
		/**
		 * 抄送
		 */
		CC, 
		/**
		 * 秘密抄送
		 */
		BCC;
		
		private Message.RecipientType	type;
		private Type(){
			String name = this.name();
			if("CC".equals(name)){
				type = Message.RecipientType.CC;
			}else if("BCC".equals(name)){
				type = Message.RecipientType.BCC;
			}else {
				type = Message.RecipientType.TO;
			}
		}
		
		public Message.RecipientType getMType(){
			return type;
		}
	}
	
	
	private String	receiver;
	private Type	receiveType;
	
	
	public EmailReceiver() {
		
	}
	
	public EmailReceiver(String receiver, String type) {
		this.receiver = receiver;
		setType(type);
		
	}
	public EmailReceiver(String receiver, Type type) {
		this.receiver = receiver;
		this.receiveType = type;
	}

	public String getReceiver() {
		return receiver;
	}

	public void setReceiver(String receiver) {
		this.receiver = receiver;
	}

	public Type getType() {
		return receiveType;
	}

	public void setType(Type type) {
		this.receiveType = type;
	}
	
	public void setType(String type){
		try {
			receiveType = Type.valueOf(type);
		} catch (Exception e) {
			receiveType = Type.TO;
		}
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((receiver == null) ? 0 : receiver.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		EmailReceiver other = (EmailReceiver) obj;
		if (receiver == null) {
			if (other.receiver != null)
				return false;
		} else if (!receiver.equals(other.receiver))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "EmailReceiver [receiver=" + receiver + ", type=" + receiveType + "]";
	}

}
