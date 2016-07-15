package woo.study.web.common.util.value;

import org.joda.time.DateTime;
import org.joda.time.LocalDate;
import org.joda.time.LocalTime;

public interface ValueHelper {

	public Object get(String key);

	public int getInt(String key);

	public float getFloat(String key);

	public long getLong(String key);

	public double getDouble(String key);

	public String getString(String key);

	public DateTime getDateTime(String key);

	public DateTime getDateTime(String key, String format);

	public LocalDate getLocalDate(String key);

	public LocalTime getLocalTime(String key);

}