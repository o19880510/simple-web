
package woo.study.web.common.functions.actionlog2.model.entity;

import java.io.Serializable;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.Type;
import org.joda.time.DateTime;

@Entity
@Table ( name = "T_UAL_BASE" )
public class  UalBase implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	
	@Id
	@Column ( name = "ID" )
	private String id;
	
	@Column ( name = "USER_ID" )
	private String userId;
	
	@Column ( name = "ACTION_CODE" )
	private String actionCode;
	
	@Column ( name = "OPERATION_DESC" )
	private String operationDesc;
	
	@Column ( name = "S_COL_1" )
	private String scol1;
	
	@Column ( name = "S_COL_2" )
	private String scol2;
	
	@Column ( name = "S_COL_3" )
	private String scol3;
	
	@Column ( name = "S_COL_4" )
	private String scol4;
	
	@Column ( name = "I_COL_1" )
	private Integer icol1;
	
	@Column ( name = "I_COL_2" )
	private Integer icol2;
	
	@Column ( name = "I_COL_3" )
	private Integer icol3;
	
	@Column ( name = "D_COL_1" )
	@Type ( type = "org.joda.time.contrib.hibernate.PersistentDateTime" )
	private DateTime					dcol1;
	
	@Column ( name = "D_COL_2" )
	@Type ( type = "org.joda.time.contrib.hibernate.PersistentDateTime" )
	private DateTime					dcol2;
	
	@Column ( name = "D_COL_3" )
	@Type ( type = "org.joda.time.contrib.hibernate.PersistentDateTime" )
	private DateTime					dcol3;
	
	@Column ( name = "INPUT_PARAMS" )
	private String inputParams;
	
	@Column ( name = "PARAM_COMMENT" )
	private String paramComment;
	
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

	public void setOperationDesc(String operationDesc){
		this.operationDesc = operationDesc;
	}

	public String getOperationDesc(){
		return this.operationDesc;
	}

	public String getScol1() {
		return scol1;
	}

	public void setScol1(String scol1) {
		this.scol1 = scol1;
	}

	public String getScol2() {
		return scol2;
	}

	public void setScol2(String scol2) {
		this.scol2 = scol2;
	}

	public String getScol3() {
		return scol3;
	}

	public void setScol3(String scol3) {
		this.scol3 = scol3;
	}

	public String getScol4() {
		return scol4;
	}

	public void setScol4(String scol4) {
		this.scol4 = scol4;
	}

	public Integer getIcol1() {
		return icol1;
	}

	public void setIcol1(Integer icol1) {
		this.icol1 = icol1;
	}

	public Integer getIcol2() {
		return icol2;
	}

	public void setIcol2(Integer icol2) {
		this.icol2 = icol2;
	}

	public Integer getIcol3() {
		return icol3;
	}

	public void setIcol3(Integer icol3) {
		this.icol3 = icol3;
	}

	public DateTime getDcol1() {
		return dcol1;
	}

	public void setDcol1(DateTime dcol1) {
		this.dcol1 = dcol1;
	}

	public DateTime getDcol2() {
		return dcol2;
	}

	public void setDcol2(DateTime dcol2) {
		this.dcol2 = dcol2;
	}

	public DateTime getDcol3() {
		return dcol3;
	}

	public void setDcol3(DateTime dcol3) {
		this.dcol3 = dcol3;
	}

	public String getInputParams() {
		return inputParams;
	}

	public void setInputParams(String inputParams) {
		this.inputParams = inputParams;
	}

	public String getParamComment() {
		return paramComment;
	}

	public void setParamComment(String paramComment) {
		this.paramComment = paramComment;
	}

	public DateTime getTxnDate() {
		return txnDate;
	}

	public void setTxnDate(DateTime txnDate) {
		this.txnDate = txnDate;
	}

	@Override
	public String toString() {
		return "UalBase [id=" + id + ", userId=" + userId + ", actionCode=" + actionCode + ", operationDesc="
				+ operationDesc + ", scol1=" + scol1 + ", scol2=" + scol2 + ", scol3=" + scol3 + ", scol4=" + scol4
				+ ", icol1=" + icol1 + ", icol2=" + icol2 + ", icol3=" + icol3 + ", dcol1=" + dcol1 + ", dcol2="
				+ dcol2 + ", dcol3=" + dcol3 + ", inputParams=" + inputParams + ", paramComment=" + paramComment
				+ ", txnDate=" + txnDate + "]";
	}

}

