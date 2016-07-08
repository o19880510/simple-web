package com.lutongnet.base.functions.actionlog.service;


import java.io.BufferedReader;



import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

import com.lutongnet.base.thread.ThreadLocalMaps;
import com.lutongnet.base.util.AssertHelper;
import com.lutongnet.base.util.DateUtils;
import com.lutongnet.base.util.HttpRequestUtils;
import com.lutongnet.base.util.HttpResponseUtils;
import com.lutongnet.base.util.IOSystem;
import com.lutongnet.base.util.JSON2Helper;
import com.lutongnet.base.util.NumberUtils;
import com.lutongnet.base.util.StringUtils;
import com.lutongnet.base.wrapper.InfoLogServletRequest;
import com.lutongnet.base.wrapper.ResponseInfo;
import com.lutongnet.base.cookies.CookieStore;
import com.lutongnet.base.functions.actionlog.constants.SeqContants;
import com.lutongnet.base.functions.actionlog.constants.ThreadLocalConstants;
import com.lutongnet.base.functions.actionlog.dao.UserActionLogDao;
import com.lutongnet.base.functions.actionlog.dao.UserActionLogDaoImpl;
import com.lutongnet.base.functions.actionlog.model.entity.UserActionLogHibernate;
import com.lutongnet.base.functions.actionlog.model.entity.UserActionLog;
import com.lutongnet.base.functions.actionlog.model.vo.LogConfigParamValue;
import com.lutongnet.base.functions.actionlog.model.vo.LogConfigValue;

@Transactional
@Service("userActionLogService")
public class UserActionLogService {
	
	private static Logger log	= LoggerFactory.getLogger(UserActionLogService.class);
	
	public static final String	RECORD_NOT_ALLOW	= "N";
	
	private static String		S_COL_1	= "s_col_1";
	private static String		S_COL_2	= "s_col_2";
	private static String		S_COL_3	= "s_col_3";
	private static String		S_COL_4	= "s_col_4";

	private static String		I_COL_1	= "i_col_1";
	private static String		I_COL_2	= "i_col_2";
	private static String		I_COL_3	= "i_col_3";

	private static String		D_COL_1	= "d_col_1";
	private static String		D_COL_2	= "d_col_2";
	private static String		D_COL_3	= "d_col_3";

	private static String		USER_ID	= "user_id";
	
	@Resource(name ="seqService")
	private SeqService seqService;
	
	@Resource(name ="userActionLogDao")
	private UserActionLogDao userActionLogDao;
	
	@Transactional(propagation = Propagation.REQUIRED)
	public void save(HttpServletRequest request, LogConfigValue logConfigValue){
		
		// 将请求参数封装为Parameter对象，以便统一获取参数获取方式。针对json、multipart和普通传参方式。
		Parameter parameter = null;
		if (HttpRequestUtils.isJsonContent(request)) {
			parameter = new JsonParameter(request);
			
		} else if (HttpRequestUtils.isMultipart(request)) {
			parameter = new MultipartParameter(request);
			
		} else {
			parameter = new DefaultParameter(request);
		}
		
		// 获取字段值，判断是否需要记录日志
		String recordFlag = logConfigValue.getRecordFlag();
		if(AssertHelper.notEmpty(recordFlag)){
			if(RECORD_NOT_ALLOW.equals(parameter.getParameter(recordFlag))){
				return;
			}
		}
		
		String userId = (String) request.getSession().getAttribute(ThreadLocalConstants.USER_ID);
		
		UserActionLog userActionLog = new UserActionLogHibernate();
		
		String logId = seqService.getSeqNoWithTime(SeqContants.Tables.T_USER_ACTION_LOG);
		log.debug("save LOG_ID:" + logId);
		
		userActionLog.setId(logId);
		userActionLog.setUserId(userId);
		userActionLog.setActionCode(logConfigValue.getActionCode());
		userActionLog.setOperationDesc(logConfigValue.getDesc());
		
		analysisParams(parameter, logConfigValue, userActionLog);
		
		userActionLog.setTxnDate(DateTime.now());
		
		userActionLogDao.save(userActionLog);
		
		ThreadLocalMaps.set(ThreadLocalConstants.LOG_ID, logId);
		ThreadLocalMaps.set(ThreadLocalConstants.START_TIME, new Long(System.currentTimeMillis()));
	}
	
	@Transactional(propagation = Propagation.REQUIRED)
	public void update(ResponseInfo responseInfo){
		
		Object logId = ThreadLocalMaps.get(ThreadLocalConstants.LOG_ID);
		
		log.debug("update LOG_ID:" + logId);
		if(logId == null){
			return;
		}
		
		UserActionLog userActionLog = userActionLogDao.get((String)logId);
		if(userActionLog == null){
			return;
		}
		
		
		int status = responseInfo.getStatus();
		String msg = responseInfo.getMsg();
		String respData = responseInfo.getResponseDatas();
		
		userActionLog.setHttpRespCode(String.valueOf(status));
		userActionLog.setHttpErrorMsg(msg);
		
		if(HttpResponseUtils.isJsonContent(responseInfo)){
			userActionLog.setRespData(StringUtils.checkLength(respData, 1000));
		}
		
		userActionLog.setBusinessResultCode(getResult(respData));
		
		String thirdRespData = (String) ThreadLocalMaps.get(ThreadLocalConstants.THIRD_RESP_DATA);
		String exceptionInfo = (String) ThreadLocalMaps.get(ThreadLocalConstants.EXCEPTION_INFO);
		
		userActionLog.setThirdRespData(StringUtils.checkLength(thirdRespData, ThreadLocalConstants.THIRD_RESP_DATA_LENGTH));
		userActionLog.setExceptionInfo(StringUtils.checkLength(exceptionInfo, ThreadLocalConstants.EXCEPTION_INFO_LENGTH));
		
		
		long startTime = Long.valueOf(ThreadLocalMaps.get(ThreadLocalConstants.START_TIME).toString());
		long usingTime = System.currentTimeMillis() - startTime;
		userActionLog.setUsingTime(usingTime);
		userActionLogDao.update(userActionLog);
	}
	
	
	/**
	 * 
	 * @param logId
	 * @param startTime
	 */
	@Transactional(propagation = Propagation.REQUIRED)
	public void updateTime(String logId, long startTime){
		
		log.debug("updateTime start.");
		UserActionLog userActionLog = userActionLogDao.get(logId);
		
		if(userActionLog == null)
		{
			return;
		}
		
		long endTime = DateTime.now().getMillis();
		long timePeriod = endTime - startTime;
		
		timePeriod /= 1000;
		
		userActionLog.setStayingTime(timePeriod);
		log.debug("updateTime userActionLog:" + userActionLog);
		
		userActionLogDao.update(userActionLog);
		log.debug("updateTime end.");
	}
	
	
	private void analysisParams(Parameter paramter, LogConfigValue logConfigValue, UserActionLog userActionLog){
		 List<LogConfigParamValue> paramsList = logConfigValue.getParamNames();
		 
		 StringBuilder inputParams = new StringBuilder();
		 StringBuilder comment = new StringBuilder();
		 for(LogConfigParamValue configParamValue : paramsList){
			 
			 String paramName = configParamValue.getName();
			 String column = configParamValue.getColumn();
			 Integer maxLength = configParamValue.getMaxLength();
			 String paramValue = configParamValue.getValue();
			 String dateType = configParamValue.getType();
			 
			 if(AssertHelper.isEmpty(paramValue)){
				 paramValue = paramter.getParameter(paramName);
			 }
			 
			 setColumn(column, paramValue, maxLength, dateType, userActionLog);
			 
			 inputParams.append(paramName).append("=").append(paramValue).append(";");

			 if(AssertHelper.notEmpty(column)){
				 comment.append(column).append("=").append(paramName).append(";");
			 }
		 }
		 
		 
		 userActionLog.setInputParams(inputParams.toString());
		 userActionLog.setComment(comment.toString());
	}
	
	private void setColumn(String column, Object paramValue,Integer maxLength, String dateType, UserActionLog userActionLog){
		log.debug("setColumn column:"+column);
		
		if(column == null || paramValue == null || AssertHelper.isEmpty(column)){
			return ;
		}
		
		boolean isString = column.startsWith("s");
		boolean isInteger = column.startsWith("i");
		boolean isDate = column.startsWith("d");
		
		if(USER_ID.equals(column))
		{
			String userId = String.valueOf(paramValue);
			if(AssertHelper.notEmpty(userId)){
				userActionLog.setUserId(userId);
			}
			
		}else if(isString)
		{
			String value = String.valueOf(paramValue);
			if(maxLength > 0 && value.length() > maxLength){
				value = value.substring(0, maxLength - 1);
			}
			
			if(S_COL_1.equals(column)){
				userActionLog.setStringColumn1(value);
			}else if(S_COL_2.equals(column)){
				userActionLog.setStringColumn2(value);
			}else if(S_COL_3.equals(column)){
				userActionLog.setStringColumn3(value);
			}else if(S_COL_4.equals(column)){
				userActionLog.setStringColumn4(value);
			}else{
				throw new RuntimeException("Unknow column:"+column+"; please check user-action-log.xml!");
			}
			
		}else if(isInteger){
			Integer value  = NumberUtils.parseInt(String.valueOf(paramValue));
			
			if(I_COL_1.equals(column)){
				userActionLog.setIntColumn1(value);
			}else if(I_COL_2.equals(column)){
				userActionLog.setIntColumn2(value);
			}else if(I_COL_3.equals(column)){
				userActionLog.setIntColumn3(value);
			}else{
				throw new RuntimeException("Unknow column:"+column+"; please check user-action-log.xml!");
			}
			
		}else if(isDate){
			DateTimeFormatter formatter = DateUtils.FORMATTER_MAP.get(dateType);
			if(formatter == null){
				try{
					formatter = DateTimeFormat.forPattern(dateType);
				}catch(Exception e){
					throw new RuntimeException("date type error: " + dateType, e);
				}
			}
			if(AssertHelper.isEmpty(paramValue.toString())){
				return;
			}
			
			DateTime dateTime = DateTime.parse(paramValue.toString(), formatter);
			
			if(D_COL_1.equals(column)){
				userActionLog.setDateColumn1(dateTime);
			}else if(D_COL_2.equals(column)){
				userActionLog.setDateColumn2(dateTime);
			}else if(D_COL_3.equals(column)){
				userActionLog.setDateColumn3(dateTime);
			}else{
				throw new RuntimeException("Unknow column:"+column+"; please check user-action-log.xml!");
			}
			
		}else{
			throw new RuntimeException("Unknow column:"+column+"; please check user-action-log.xml!");
		}
	}
	
	private Integer getResult(String json){
		try{
			Map map = JSON2Helper.toObject(json, Map.class);
			
			Object result = map.get("result");
			return NumberUtils.parseInt(String.valueOf(result));
		}catch(Exception e){
			return null;
		}
		
	}
	
	private interface Parameter{
		String getParameter(String name);
	}
	
	private class JsonParameter implements Parameter{
		
		private Map<String, Object> map;
		
		private HttpServletRequest request;
		
		@SuppressWarnings("unchecked")
		public JsonParameter(HttpServletRequest request)
		{
			this.request = request;
			
			try {
				InfoLogServletRequest infoRequest = (InfoLogServletRequest)request;
				BufferedReader br = infoRequest.getReader();
				String content = IOSystem.read(br);
				
				map = JSON2Helper.toObject(content, Map.class);
				log.debug("map:" + map);
				
				if(map == null){
					map = new HashMap<String, Object>();
				}
				log.debug("map:" + map);
				
			} catch (Exception e) {
				log.error("request.getReader error:", e);
				throw new RuntimeException("request.getReader error:" + e);
			}
		}
		
		public String getParameter(String name) 
		{
			
			String value = (String)map.get(name);
			if(value == null){
				CookieStore cookieStore = new CookieStore(request, null);
				value = cookieStore.get(name);
			}
			return value;
		}
	}
	
	private class DefaultParameter implements Parameter{
		
		private HttpServletRequest request;
		
		public DefaultParameter(HttpServletRequest request){
			this.request = request;
		}
		
		public String getParameter(String name) 
		{
			String value = request.getParameter(name);
			if(value == null){
				CookieStore cookieStore = new CookieStore(request, null);
				value = cookieStore.get(name);
			}
			return value;
		}
	}
	private class MultipartParameter implements Parameter{
		
		private HttpServletRequest request;

		public MultipartParameter(HttpServletRequest request)  {
			CommonsMultipartResolver commonsMultipartResolver = new CommonsMultipartResolver();
			commonsMultipartResolver.setResolveLazily(true);
			this.request = commonsMultipartResolver.resolveMultipart(request);
		}
		
		public String getParameter(String name) 
		{
			String value = request.getParameter(name);
			if(value == null){
				CookieStore cookieStore = new CookieStore(request, null);
				value = cookieStore.get(name);
			}
			return value;
		}
	}
	
}
