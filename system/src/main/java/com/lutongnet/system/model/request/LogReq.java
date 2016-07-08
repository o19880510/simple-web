package com.lutongnet.system.model.request;

import org.joda.time.DateTime;
import org.springframework.format.annotation.DateTimeFormat;


public class LogReq extends CommonReq {

	private String		actor;
	private String		content;
	@DateTimeFormat ( pattern = "yyyy-MM-dd" )
	private DateTime	from;
	@DateTimeFormat ( pattern = "yyyy-MM-dd" )
	private DateTime	to;
	private String		type;
	private String		typeCN	= "所有";

	public String getActor ( ) {
		return actor;
	}

	public void setActor ( String actor ) {
		this.actor = actor;
	}

	public String getContent ( ) {
		return content;
	}

	public void setContent ( String content ) {
		this.content = content;
	}

	public DateTime getFrom ( ) {
		return from;
	}

	public void setFrom ( DateTime from ) {
		this.from = from;
	}

	public DateTime getTo ( ) {
		return to;
	}

	public void setTo ( DateTime to ) {
		this.to = to;
	}

	public String getType ( ) {
		return type;
	}

	public void setType ( String type ) {
		this.type = type;
	}

	public String getTypeCN ( ) {
		return typeCN;
	}

	public void setTypeCN ( String typeCN ) {
		this.typeCN = typeCN;
	}
}
