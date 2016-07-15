package woo.study.web.common.functions.actionlog2.model.vo;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import woo.study.web.common.functions.actionlog2.service.UalBaseService;

public class LogConfigValue implements Serializable{

	private String						url;
	private String						method;
	private String						matchUrl;
	
	private String						actionCode;
	private String						desc;
	private String						recordFlag;
	
	private List<LogConfigParamValue>	paramNames;
	private Map<String, String>			configParamValues;
	private LogConfigParamValue			userIdParam;
	
	
	public LogConfigValue() {
		super();
		configParamValues = new HashMap<String, String>();
	}

	public String getUrl() {
		return url;
	}
	
	public void setUrl(String url) {
		this.url = url;
	}
	public String getActionCode() {
		return actionCode;
	}
	public void setActionCode(String actionCode) {
		this.actionCode = actionCode;
	}
	public String getDesc() {
		return desc;
	}
	public void setDesc(String desc) {
		this.desc = desc;
	}
	public List<LogConfigParamValue> getParamNames() {
		return paramNames;
	}
	
	public void setParamNames(List<LogConfigParamValue> paramNames) {
		this.paramNames = paramNames;
		setConfigParamValues();
		setUserIdParam();
	}
	
	public String getMethod() {
		return method;
	}
	public void setMethod(String method) {
		this.method = method;
	}
	
	public String getMatchUrl() {
		return matchUrl;
	}

	public void setMatchUrl(String matchUrl) {
		this.matchUrl = matchUrl;
	}

	public String getRecordFlag() {
		return recordFlag;
	}
	public void setRecordFlag(String recordFlag) {
		this.recordFlag = recordFlag;
	}
	
	public Map<String, String> getConfigParamValues() {
		return configParamValues;
	}

	private void setConfigParamValues() {
		for(LogConfigParamValue configParamValue : this.paramNames){
			if(configParamValue.getValue() != null){
				configParamValues.put(configParamValue.getName(), configParamValue.getValue());
			}
		} 
	}
	

	public LogConfigParamValue getUserIdParam() {
		return userIdParam;
	}

	public void setUserIdParam() {
		for(LogConfigParamValue configParamValue : this.paramNames){
			if(UalBaseService.USER_ID.equals(configParamValue.getColumn())){
				this.userIdParam = configParamValue;
			}
		} 
	}

	@Override
	public String toString() {
		return "LogConfigValue [url=" + url + ", method=" + method + ", matchUrl=" + matchUrl + ", actionCode="
				+ actionCode + ", desc=" + desc + ", recordFlag=" + recordFlag + ", paramNames=" + paramNames
				+ ", configParamValues=" + configParamValues + "]";
	}
	
	
}
