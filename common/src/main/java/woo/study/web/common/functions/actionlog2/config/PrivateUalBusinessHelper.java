package woo.study.web.common.functions.actionlog2.config;

import woo.study.web.common.functions.actionlog2.model.vo.LogConfigValue;
import woo.study.web.common.thread.ThreadLocalMaps;
import woo.study.web.common.util.value.ValueHelper;

public class PrivateUalBusinessHelper {

	private static final String	LOG_ID			= "logId";
	private static final String	USER_ID			= "userId";
	private static final String	LOG_CONFIG		= "logConfig";
	private static final String	VALUE_HELPER	= "valueHelper";

	public static void setLogId(String logId) {
		ThreadLocalMaps.set(LOG_ID, logId);
	}

	public static String getLogId() {
		return (String) ThreadLocalMaps.get(LOG_ID);
	}
	
	public static void setUserId(String userId) {
		ThreadLocalMaps.set(USER_ID, userId);
	}
	
	public static String getUserId() {
		return (String) ThreadLocalMaps.get(USER_ID);
	}

	public static void setLogConfigValue(LogConfigValue logConfigValue) {
		ThreadLocalMaps.set(LOG_CONFIG, logConfigValue);
	}

	public static LogConfigValue getLogConfigValue() {
		return (LogConfigValue) ThreadLocalMaps.get(LOG_CONFIG);
	}
	
	public static void setValueHelper(ValueHelper valueHelper) {
		ThreadLocalMaps.set(VALUE_HELPER, valueHelper);
	}
	
	public static ValueHelper getValueHelper() {
		return (ValueHelper) ThreadLocalMaps.get(VALUE_HELPER);
	}
}
