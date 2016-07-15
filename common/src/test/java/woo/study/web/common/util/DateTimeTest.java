package woo.study.web.common.util;

import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;

public class DateTimeTest {
	public static void main(String[] args) {
		System.out.println(DateTime.now());
		
		String hours = DateTime.now().toString("HH:mm:ss");
		DateTime hoursDateTime = DateTime.parse(hours, DateTimeFormat.forPattern("yyyy-MM-dd HH:mm:ss"));
		System.out.println(hours);
		System.out.println(hoursDateTime.toLocalTime());
		
		
		String date = DateTime.now().toString("yyyy-MM-dd");
		DateTime dateDateTime = DateTime.parse(date, DateTimeFormat.forPattern("yyyy-MM-dd"));
		System.out.println(date);
		System.out.println(dateDateTime);
		System.out.println(dateDateTime.toLocalDate());
	}
}
