package woo.study.web.common.functions.actionlog.model.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.Type;
import org.joda.time.DateTime;

@Entity
@Table(name = "t_user_action_log")
public class UserActionLogHibernate implements Serializable, UserActionLog {

	@Id
	private String		id;

	@Column(name = "user_id")
	private String		userId;

	@Column(name = "action_code")
	private String		actionCode;

	@Column(name = "operation_desc")
	private String		operationDesc;

	@Column(name = "s_col_1")
	private String		stringColumn1;

	@Column(name = "s_col_2")
	private String		stringColumn2;

	@Column(name = "s_col_3")
	private String		stringColumn3;

	@Column(name = "s_col_4")
	private String		stringColumn4;

	@Column(name = "i_col_1")
	private Integer		intColumn1;

	@Column(name = "i_col_2")
	private Integer		intColumn2;

	@Column(name = "i_col_3")
	private Integer		intColumn3;

	@Column(name = "d_col_1")
	@Type(type = "org.joda.time.contrib.hibernate.PersistentDateTime")
	private DateTime	dateColumn1;

	@Column(name = "d_col_2")
	@Type(type = "org.joda.time.contrib.hibernate.PersistentDateTime")
	private DateTime	dateColumn2;

	@Column(name = "d_col_3")
	@Type(type = "org.joda.time.contrib.hibernate.PersistentDateTime")
	private DateTime	dateColumn3;

	@Column(name = "input_params")
	private String		inputParams;

	@Column(name = "http_resp_code")
	private String		httpRespCode;

	@Column(name = "http_error_msg")
	private String		httpErrorMsg;

	@Column(name = "third_resp_data")
	private String		thirdRespData;

	@Column(name = "resp_data")
	private String		respData;

	@Column(name = "business_result_code")
	private Integer		businessResultCode;

	@Column(name = "exception_info")
	private String		exceptionInfo;

	@Column(name = "staying_time")
	private Long		stayingTime;
	
	@Column(name = "using_time")
	private Long		usingTime;

	@Column(name = "comment")
	private String		comment;

	@Type(type = "org.joda.time.contrib.hibernate.PersistentDateTime")
	@Column(name = "txn_date")
	private DateTime	txnDate;

	
	@Override
	public String getId() {
		return id;
	}

	
	@Override
	public void setId(String id) {
		this.id = id;
	}

	
	@Override
	public String getUserId() {
		return userId;
	}

	
	@Override
	public void setUserId(String userId) {
		this.userId = userId;
	}

	
	@Override
	public String getActionCode() {
		return actionCode;
	}

	
	@Override
	public void setActionCode(String actionCode) {
		this.actionCode = actionCode;
	}

	
	@Override
	public String getOperationDesc() {
		return operationDesc;
	}

	
	@Override
	public void setOperationDesc(String operationDesc) {
		this.operationDesc = operationDesc;
	}

	
	@Override
	public String getStringColumn1() {
		return stringColumn1;
	}

	
	@Override
	public void setStringColumn1(String stringColumn1) {
		this.stringColumn1 = stringColumn1;
	}

	
	@Override
	public String getStringColumn2() {
		return stringColumn2;
	}

	
	@Override
	public void setStringColumn2(String stringColumn2) {
		this.stringColumn2 = stringColumn2;
	}

	
	@Override
	public String getStringColumn3() {
		return stringColumn3;
	}

	
	@Override
	public void setStringColumn3(String stringColumn3) {
		this.stringColumn3 = stringColumn3;
	}

	
	@Override
	public String getStringColumn4() {
		return stringColumn4;
	}

	
	@Override
	public void setStringColumn4(String stringColumn4) {
		this.stringColumn4 = stringColumn4;
	}

	
	@Override
	public Integer getIntColumn1() {
		return intColumn1;
	}

	
	@Override
	public void setIntColumn1(Integer intColumn1) {
		this.intColumn1 = intColumn1;
	}

	
	@Override
	public Integer getIntColumn2() {
		return intColumn2;
	}

	
	@Override
	public void setIntColumn2(Integer intColumn2) {
		this.intColumn2 = intColumn2;
	}

	
	@Override
	public Integer getIntColumn3() {
		return intColumn3;
	}

	
	@Override
	public void setIntColumn3(Integer intColumn3) {
		this.intColumn3 = intColumn3;
	}

	
	@Override
	public DateTime getDateColumn1() {
		return dateColumn1;
	}

	
	@Override
	public void setDateColumn1(DateTime dateColumn1) {
		this.dateColumn1 = dateColumn1;
	}

	
	@Override
	public DateTime getDateColumn2() {
		return dateColumn2;
	}

	
	@Override
	public void setDateColumn2(DateTime dateColumn2) {
		this.dateColumn2 = dateColumn2;
	}

	
	@Override
	public DateTime getDateColumn3() {
		return dateColumn3;
	}

	
	@Override
	public void setDateColumn3(DateTime dateColumn3) {
		this.dateColumn3 = dateColumn3;
	}

	
	@Override
	public String getInputParams() {
		return inputParams;
	}

	
	@Override
	public void setInputParams(String inputParams) {
		this.inputParams = inputParams;
	}

	
	@Override
	public String getHttpRespCode() {
		return httpRespCode;
	}

	
	@Override
	public void setHttpRespCode(String httpRespCode) {
		this.httpRespCode = httpRespCode;
	}

	
	@Override
	public String getHttpErrorMsg() {
		return httpErrorMsg;
	}

	
	@Override
	public void setHttpErrorMsg(String httpErrorMsg) {
		this.httpErrorMsg = httpErrorMsg;
	}

	
	@Override
	public String getThirdRespData() {
		return thirdRespData;
	}

	
	@Override
	public void setThirdRespData(String thirdRespData) {
		this.thirdRespData = thirdRespData;
	}

	
	@Override
	public String getRespData() {
		return respData;
	}

	
	@Override
	public void setRespData(String respData) {
		this.respData = respData;
	}

	
	@Override
	public Integer getBusinessResultCode() {
		return businessResultCode;
	}

	
	@Override
	public void setBusinessResultCode(Integer businessResultCode) {
		this.businessResultCode = businessResultCode;
	}

	
	@Override
	public String getExceptionInfo() {
		return exceptionInfo;
	}

	
	@Override
	public void setExceptionInfo(String exceptionInfo) {
		this.exceptionInfo = exceptionInfo;
	}

	
	@Override
	public Long getUsingTime() {
		return usingTime;
	}

	
	@Override
	public void setUsingTime(Long usingTime) {
		this.usingTime = usingTime;
	}

	
	@Override
	public String getComment() {
		return comment;
	}

	
	@Override
	public void setComment(String comment) {
		this.comment = comment;
	}

	
	@Override
	public DateTime getTxnDate() {
		return txnDate;
	}

	
	@Override
	public void setTxnDate(DateTime txnDate) {
		this.txnDate = txnDate;
	}

	
	@Override
	public Long getStayingTime() {
		return stayingTime;
	}

	
	@Override
	public void setStayingTime(Long stayingTime) {
		this.stayingTime = stayingTime;
	}

	
	@Override
	public String toString() {
		return "UserActionLog [id=" + id + ", userId=" + userId
				+ ", actionCode=" + actionCode + ", operationDesc="
				+ operationDesc + ", stringColumn1=" + stringColumn1
				+ ", stringColumn2=" + stringColumn2 + ", stringColumn3="
				+ stringColumn3 + ", stringColumn4=" + stringColumn4
				+ ", intColumn1=" + intColumn1 + ", intColumn2=" + intColumn2
				+ ", intColumn3=" + intColumn3 + ", dateColumn1=" + dateColumn1
				+ ", dateColumn2=" + dateColumn2 + ", dateColumn3="
				+ dateColumn3 + ", inputParams=" + inputParams
				+ ", httpRespCode=" + httpRespCode + ", httpErrorMsg="
				+ httpErrorMsg + ", thirdRespData=" + thirdRespData
				+ ", respData=" + respData + ", businessResultCode="
				+ businessResultCode + ", exceptionInfo=" + exceptionInfo
				+ ", stayingTime=" + stayingTime + ", usingTime=" + usingTime
				+ ", comment=" + comment + ", txnDate=" + txnDate + "]";
	}
	
}
