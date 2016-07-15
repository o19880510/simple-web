package woo.study.web.common.functions.actionlog2.datacenter;

import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import woo.study.web.common.functions.actionlog2.model.vo.LogConfigValue;
import woo.study.web.common.functions.datacenter.DataManagementHelper;

public class ActionLogDataManagementHelper {
	
	private static Logger log	= LoggerFactory.getLogger(ActionLogDataManagementHelper.class);
	
	public static final String USER_ACTION_LOG_FULL_PATH = "USER_ACTION_LOG_FULL_PATH";
	public static final String USER_ACTION_LOG_MATCH_PATH = "USER_ACTION_LOG_MATCH_PATH";
	
	public static final String ACTION_CODE = "ACTION_CODE";
	public static final String CONFIG_PROPES = "CONFIG_PROPES";
	
	private static ActionLogDataManagementHelper DATA_MANAGEMENT_HELPER = new ActionLogDataManagementHelper();
	
	protected ActionLogDataManagementHelper() {
	}
	
	
	public static ActionLogDataManagementHelper getInstance(){
		return DATA_MANAGEMENT_HELPER;
	}
	
	public Map<String, LogConfigValue> getUserActionLogsFullPath() {
		return DataManagementHelper.getDataContainer().get(USER_ACTION_LOG_FULL_PATH, Map.class);
	}
	
	public List<LogConfigValue> getUserActionLogsMatchPath() {
		return DataManagementHelper.getDataContainer().get(USER_ACTION_LOG_MATCH_PATH, List.class);
	}

	public Map<String, String> getActionCode() {
		return DataManagementHelper.getDataContainer().get(ACTION_CODE, Map.class);
	}
	
	public Properties getConfigProperties(){
		Properties properties = DataManagementHelper.getDataContainer().get(CONFIG_PROPES, Properties.class);
		return properties;
	}
}
