package com.lutongnet.base.message;

import com.lutongnet.base.loader.EnumDisplay;


public class ActionResult {
	
	public static enum Type implements EnumDisplay{
		SUCCESS("成功"), ERROR("异常");
		
		private String type;
		
		private Type(String type) {
			this.type = type;
		}

		@Override
		public String getDesc() {
			return type;
		}
	}

//	public static final String	SUCCESS	= "success";
//	public static final String	ERROR		= "error";

	private String							type;
	private String							message;

	public ActionResult ( String type , String message ) {
		super ( );
		this.type = type;
		this.message = message;
	}

	public String getType ( ) {
		return type;
	}

	public void setType ( String type ) {
		this.type = type;
	}

	public String getMessage ( ) {
		return message;
	}

	public void setMessage ( String message ) {
		this.message = message;
	}

}
