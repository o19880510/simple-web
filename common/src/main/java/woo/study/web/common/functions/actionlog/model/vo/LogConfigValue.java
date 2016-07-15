package woo.study.web.common.functions.actionlog.model.vo;

import java.io.Serializable;
import java.util.List;

public class LogConfigValue implements Serializable{

	private String						url;
	private String						method;
	private String						actionCode;
	private String						desc;
	private String						recordFlag;
	private List<LogConfigParamValue>	paramNames;
	
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
	}
	
	public String getMethod() {
		return method;
	}
	public void setMethod(String method) {
		this.method = method;
	}
	
	public String getRecordFlag() {
		return recordFlag;
	}
	public void setRecordFlag(String recordFlag) {
		this.recordFlag = recordFlag;
	}
	
	@Override
	public String toString() {
		return "LogConfigValue [url=" + url + ", method=" + method + ", actionCode=" + actionCode + ", desc=" + desc + ", recordFlag=" + recordFlag
				+ ", paramNames=" + paramNames + "]";
	}
	
}
