package com.lutongnet.system.model.request;

import org.joda.time.DateTime;
import org.springframework.format.annotation.DateTimeFormat;


public class FaqReq extends CommonReq {

	private String		question;
	private String		answer;
	@DateTimeFormat ( pattern = "yyyy-MM-dd" )
	private DateTime	from;
	@DateTimeFormat ( pattern = "yyyy-MM-dd" )
	private DateTime	to;

	public String getQuestion ( ) {
		return question;
	}

	public void setQuestion ( String question ) {
		this.question = question;
	}

	public String getAnswer ( ) {
		return answer;
	}

	public void setAnswer ( String answer ) {
		this.answer = answer;
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
}
