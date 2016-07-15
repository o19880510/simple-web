package woo.study.web.common.util;

import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

public class TimeDemo01 {
	
	public static void main(String[] args) {
		DateTimeFormatter format = DateTimeFormat .forPattern("yyyy-MM-dd HH:mm:ss");
		
		DateTime dateTime = DateTime.parse("2014-04-17 12:23:59", format);
		System.out.println(dateTime);
	}

}
