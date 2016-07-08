package com.lutongnet.base.functions.actionlog.model.entity;

import org.joda.time.DateTime;

public interface UserActionLog {

	public abstract String getId();

	public abstract void setId(String id);

	public abstract String getUserId();

	public abstract void setUserId(String userId);

	public abstract String getActionCode();

	public abstract void setActionCode(String actionCode);

	public abstract String getOperationDesc();

	public abstract void setOperationDesc(String operationDesc);

	public abstract String getStringColumn1();

	public abstract void setStringColumn1(String stringColumn1);

	public abstract String getStringColumn2();

	public abstract void setStringColumn2(String stringColumn2);

	public abstract String getStringColumn3();

	public abstract void setStringColumn3(String stringColumn3);

	public abstract String getStringColumn4();

	public abstract void setStringColumn4(String stringColumn4);

	public abstract Integer getIntColumn1();

	public abstract void setIntColumn1(Integer intColumn1);

	public abstract Integer getIntColumn2();

	public abstract void setIntColumn2(Integer intColumn2);

	public abstract Integer getIntColumn3();

	public abstract void setIntColumn3(Integer intColumn3);

	public abstract DateTime getDateColumn1();

	public abstract void setDateColumn1(DateTime dateColumn1);

	public abstract DateTime getDateColumn2();

	public abstract void setDateColumn2(DateTime dateColumn2);

	public abstract DateTime getDateColumn3();

	public abstract void setDateColumn3(DateTime dateColumn3);

	public abstract String getInputParams();

	public abstract void setInputParams(String inputParams);

	public abstract String getHttpRespCode();

	public abstract void setHttpRespCode(String httpRespCode);

	public abstract String getHttpErrorMsg();

	public abstract void setHttpErrorMsg(String httpErrorMsg);

	public abstract String getThirdRespData();

	public abstract void setThirdRespData(String thirdRespData);

	public abstract String getRespData();

	public abstract void setRespData(String respData);

	public abstract Integer getBusinessResultCode();

	public abstract void setBusinessResultCode(Integer businessResultCode);

	public abstract String getExceptionInfo();

	public abstract void setExceptionInfo(String exceptionInfo);

	public abstract Long getUsingTime();

	public abstract void setUsingTime(Long usingTime);

	public abstract String getComment();

	public abstract void setComment(String comment);

	public abstract DateTime getTxnDate();

	public abstract void setTxnDate(DateTime txnDate);

	public abstract Long getStayingTime();

	public abstract void setStayingTime(Long stayingTime);

}