package woo.study.web.common.functions.actionlog.datacenter;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import woo.study.web.common.functions.actionlog.model.vo.LogConfigValue;
import woo.study.web.common.functions.datacenter.DataManagementHelper;

public class ActionLogDataManagementHelper {
	
	private static Logger log	= LoggerFactory.getLogger(ActionLogDataManagementHelper.class);
	
	public static final String USER_ACTION_LOG = "USER_ACTION_LOG";
	public static final String ACTION_CODE = "ACTION_CODE";
	
	private static ActionLogDataManagementHelper DATA_MANAGEMENT_HELPER = new ActionLogDataManagementHelper();
	
	protected ActionLogDataManagementHelper() {
	}
	
	
	public static ActionLogDataManagementHelper getInstance(){
		return DATA_MANAGEMENT_HELPER;
	}
	
	public Map<String, LogConfigValue> getUserActionLogs() {
		return DataManagementHelper.getDataContainer().get(USER_ACTION_LOG, Map.class);
	}

	public Map<String, String> getActionCode() {
		return DataManagementHelper.getDataContainer().get(ACTION_CODE, Map.class);
	}
}
