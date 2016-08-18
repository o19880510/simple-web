package woo.study.web.common.util.value;

import org.joda.time.DateTime;
import org.joda.time.LocalDate;
import org.joda.time.LocalTime;
import org.joda.time.format.DateTimeFormat;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import woo.study.web.common.util.AssertHelper;

public class ValueHelperImpl implements ValueHelper {

	private static final Logger log = LoggerFactory.getLogger(ValueHelperImpl.class);

	private ValueGettable valueGettable;

	public ValueHelperImpl(ValueGettable valueGettable) {
		super();
		this.valueGettable = valueGettable;
	}

	@Override
	public String getString(String key) {
		Object obj = get(key);
		if (obj != null) {
			return obj.toString();
		}
		return null;
	}

	@Override
	public Integer getInt(String key) {
		Object obj = get(key);
		if (obj != null) {
			try {
				return Integer.valueOf(obj.toString());

			} catch (Exception e) {
				log.warn("ValueHelperImpl getInteger error, key={}, obj={}", key, obj);
				return null;
			}
		}
		return null;
	}

	@Override
	public Long getLong(String key) {
		Object obj = get(key);
		if (obj != null) {
			try {
				return Long.valueOf(obj.toString());
			} catch (Exception e) {
				log.warn("ValueHelperImpl getLong error, key={}, obj={}", key, obj);
				return null;
			}
		}
		return null;
	}

	@Override
	public Double getDouble(String key) {
		Object obj = get(key);
		if (obj != null) {
			try {
				return Double.valueOf(obj.toString());
			} catch (Exception e) {
				log.warn("ValueHelperImpl getDouble error, key={}, obj={}", key, obj);
				return null;
			}
		}
		return null;
	}

	@Override
	public Object get(String key) {
		return valueGettable.get(key);
	}

	@Override
	public DateTime getDateTime(String key, String format) {
		Object obj = get(key);
		if (obj == null || AssertHelper.isEmpty(obj.toString())) {
			return null;

		} else if (obj instanceof DateTime) {
			return (DateTime) obj;

		} else {
			DateTime dateTime = null;
			try {
				dateTime = DateTime.parse(obj.toString(), DateTimeFormat.forPattern(format));
			} catch (Exception e) {
				log.warn("ValueHelperImpl getDateTime parse error!", e);
			}
			return dateTime;
		}
	}

	@Override
	public DateTime getDateTime(String key) {
		return getDateTime(key, "yyyy-MM-dd HH:mm:ss");
	}

	@Override
	public LocalDate getLocalDate(String key) {
		DateTime dateTime = getDateTime(key, "yyyy-MM-dd");
		return dateTime == null ? null : dateTime.toLocalDate();
	}

	@Override
	public LocalTime getLocalTime(String key) {
		DateTime dateTime = getDateTime(key, "HH:mm:ss");
		return dateTime == null ? null : dateTime.toLocalTime();
	}

}
