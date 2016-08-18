package woo.study.web.common.util.value;

import org.joda.time.DateTime;
import org.joda.time.LocalDate;
import org.joda.time.LocalTime;

public interface ValueHelper {

	public Object get(String key);

	/**
	 * @param key
	 * @return 返回Integer对象，而不是返回int。为啥？因为当数据不存在时，
	 *         返回0，-1都不合适（这些数据可能有业务上的意义）。返回null比较合适。
	 */
	public Integer getInt(String key);

	public Long getLong(String key);

	public Double getDouble(String key);

	public String getString(String key);

	public DateTime getDateTime(String key);

	public DateTime getDateTime(String key, String format);

	public LocalDate getLocalDate(String key);

	public LocalTime getLocalTime(String key);

}