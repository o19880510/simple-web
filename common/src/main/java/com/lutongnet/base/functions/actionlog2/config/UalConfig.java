package com.lutongnet.base.functions.actionlog2.config;

import java.util.Properties;

import com.lutongnet.base.functions.actionlog2.datacenter.ActionLogDataManagementHelper;
import com.lutongnet.base.util.AssertHelper;
import com.lutongnet.base.util.NumberUtils;

/**
 * UAL配置类
 * 
 * 定义配置常量<br>
 * 获取配置内容<br>
 * 
 * 
 * @author tianjp
 * @version 2.0.0
 * @since 1.0.4
 */
public class UalConfig {
	
	private static final String UAL_ID = "ual.id";
	private static final String UAL_SWITCH_LOG = "ual.switch.log";
	private static final String UAL_SWITCH_STAYTIME = "ual.switch.staytime";
	private static final String UAL_ID_SEQ_MAX_NUMBER = "ual.id.seq.max.number";
	
	public static String getConfigId(){
		String ualId = getConfig(UAL_ID);
		if(AssertHelper.notEmpty(ualId)){
			return ualId.toUpperCase();
		}
		return null;
	}
	
	public static Switch getLogSwitch(){
		String logSwitch = getConfig(UAL_SWITCH_LOG);
		try{
			return Switch.valueOf(logSwitch.toUpperCase());
		}catch(Exception e){
			return Switch.ON;
		}
	}
	
	public static Switch getStayTimeSwitch(){
		String logSwitch = getConfig(UAL_SWITCH_STAYTIME);
		try{
			return Switch.valueOf(logSwitch.toUpperCase());
		}catch(Exception e){
			return Switch.ON;
		}
	}
	
	public static int getSeqMaxNumber(){
		String maxNumber = getConfig(UAL_ID_SEQ_MAX_NUMBER);
		return NumberUtils.parseInt(maxNumber, 1);
	}
	
	private static String getConfig(String key){
		Properties config = ActionLogDataManagementHelper.getInstance().getConfigProperties();
		if(config != null){
			return config.getProperty(key);
		}
		return null;
	}
}
