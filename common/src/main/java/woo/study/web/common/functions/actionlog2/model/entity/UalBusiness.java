
package woo.study.web.common.functions.actionlog2.model.entity;

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
@Table ( name = "T_UAL_BUSINESS" )
public class  UalBusiness implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	
	@Id
	@Column ( name = "ID" )
	private String id;
	
	@Column ( name = "USER_ID" )
	private String userId;
	
	@Column ( name = "ACTION_CODE" )
	private String actionCode;
	
	@Column ( name = "BUSINESS_STATUS_CODE" )
	private String businessStatusCode;
	
	@Column ( name = "EXCEPTION_INFO" )
	private String exceptionInfo;
	
	@Column ( name = "TXN_DATE" )
	@Type ( type = "org.joda.time.contrib.hibernate.PersistentDateTime" )
	private DateTime					txnDate;
	
	public void setId(String id){
		this.id = id;
	}

	public String getId(){
		return this.id;
	}

	public void setUserId(String userId){
		this.userId = userId;
	}

	public String getUserId(){
		return this.userId;
	}

	public void setActionCode(String actionCode){
		this.actionCode = actionCode;
	}

	public String getActionCode(){
		return this.actionCode;
	}

	public void setBusinessStatusCode(String businessStatusCode){
		this.businessStatusCode = businessStatusCode;
	}

	public String getBusinessStatusCode(){
		return this.businessStatusCode;
	}

	public void setExceptionInfo(String exceptionInfo){
		this.exceptionInfo = exceptionInfo;
	}

	public String getExceptionInfo(){
		return this.exceptionInfo;
	}

	public void setTxnDate(DateTime txnDate){
		this.txnDate = txnDate;
	}

	public DateTime getTxnDate(){
		return this.txnDate;
	}

	
	public String toString(){
		return "alBusiness [ "
		+ "id:" + id
		+ ", userId:" + userId
		+ ", actionCode:" + actionCode
		+ ", businessStatusCode:" + businessStatusCode
		+ ", exceptionInfo:" + exceptionInfo
		+ ", txnDate:" + txnDate
		+ " ]";
	}

}

