package woo.study.web.system.constant;

public final class AppConstant {

	/** 系统账号标 识 */
	public final static String	USER_ID					= "USER_ID";
	public final static String	USER_INFO				= "USER_INFO";
	
	public final static String	REQUEST_SCOPE			= "REQUEST";
	public final static String	SESSION_SCOPE			= "SESSION";
	public final static String	APPLICATION_SCOPE		= "APPLICATION";

//	public static final String	USER_ENABLE				= "enable";
//	public static final String	USER_DISABLE			= "disable";
//	public static final String	USER_ONLINE				= "online";
//	public static final String	USER_OFFLINE			= "offline";

	public static final String	VERIFY_CODE				= "VERIFY_CODE";
	
	public static final String	USER_MENU_TREE			= "USER_MENU_TREE";
	public static final String	USER_HOLDER				= "USER_HOLDER";
	public static final String	SESSIONID_HOLDER		= "SESSIONID_HOLDER";

	/** 定时任务运行状态常量 tianjp start */
	public static class ScheduledStatus{
		public final static int	_0_CHECK_PARENT		= 0;
		public final static int	_1_CHECK_JOB		= 1;
		public final static int	_2_JOB_RUN			= 2;
		public final static int	_3_JOB_RUN_OVER		= 3;
		public final static int	_4_CALL_CHLIDREN	= 4;
	}
	/** 定时任务运行状态常量 tianjp end */
	
//	public static final String	MEMORY_DATA			= "MEMORY_DATA";
	public static class MemDataKeys{
		/** 系统所有参数在程序中的键标识 */
		public static final String	PARAMETER_MAP			= "PARAMETER_MAP";
		/** 系统里所有省份在程序中的键标识 */
		public static final String	PROVINCE_MAP			= "PROVINCE_MAP";

		/** 系统里所有地市在程序中的键标识 */
		public static final String	CITY_MAP				= "CITY_MAP";

		public static final String	SYSTEM_MENU_TREE		= "SYSTEM_MENU_TREE";

		public static final String	USER_MENU_TREE			= "USER_MENU_TREE";

		/** 系统所有权限的访问URI列表 */
		public static final String	PRIVILEGE_URI_LIST		= "PRIVILEGE_URI_LIST";

		/** 日志配置文件 */
		public static final String	USER_ACTION_LOG			= "USER_ACTION_LOG";
		public static final String	ACTION_CODE				= "ACTION_CODE";

		public static final String	SYSTEM_PARAMTER_MAP		= "SYSTEM_PARAMTER_MAP";

		/** codetable */
		public static final String	CODETABLE_PARAMTERS_MAP	= "CODETABLE_PARAMTERS_MAP";

		public static final String	CODETABLE_CONFIG		= "CODETABLE_CONFIG";
		public static final String	MENU_TREE				= "MENU_TREE";
		public static final String	CONFIG_PROPES			= "CONFIG_PROPES";
	}
}
