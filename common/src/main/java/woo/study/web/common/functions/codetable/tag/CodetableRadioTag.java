package woo.study.web.common.functions.codetable.tag;

import java.io.IOException;

import java.util.Map;

import javax.servlet.jsp.JspTagException;
import javax.servlet.jsp.JspWriter;

import woo.study.web.common.util.AssertHelper;

public class CodetableRadioTag extends CodetableCommon {
	
	private static final long	serialVersionUID	= 1L;
	
	private String table;
	private boolean enterFlag = false;
	private int checkIndex = 0;
	private String attributes;
	private String checkValue;
	
	public int doStartTag() throws JspTagException {
		
		Map<String, String> tableRecords = getMap(table);
		
		if(tableRecords == null || tableRecords.size() == 0) {
			return SKIP_BODY;
		}
		
		if(AssertHelper.notEmpty(checkValue) ){
			int i = 0;
			for(String key : tableRecords.keySet()){
				if(checkValue.equals(key)){
					checkIndex = i;
					break;
				}
				++i;
			}
		}
		
		
		int i = 0 ;
		for(Map.Entry<String, String> entry : tableRecords.entrySet()){
			
			try {
				boolean checked = false;
				if(!checked && checkIndex == i){
					checked = true;
				}
				
				this.pageContext.getOut().print(getRadio(entry.getKey(), entry.getValue(), checked));
				
				++i;
			} catch (Exception e) {
				return SKIP_BODY;
			}
		}
		
		return SKIP_BODY;
	}

	private String getRadio(String key, String value, boolean checked){
		StringBuilder radioString = new StringBuilder();
		
		radioString.append("<input type=\"radio\" value=\"").append(key).append("\" ");
		if(checked){
			radioString.append(" checked=\"checked\" ");
		}
		if(AssertHelper.notEmpty(attributes)){
			radioString.append(attributes);
		}
		radioString.append(" >").append(value);
		
		if(enterFlag){
			radioString.append("<br>");
		}else{
			radioString.append("  ");
		}
		return radioString.toString();
	}
	
	
	
	public void setTable(String table) {
		this.table = table;
	}

	public String getTable() {
		return table;
	}

	public boolean isEnterFlag() {
		return enterFlag;
	}
	public void setEnterFlag(boolean enterFlag) {
		this.enterFlag = enterFlag;
	}

	public int getCheckIndex() {
		return checkIndex;
	}

	public void setCheckIndex(int checkIndex) {
		this.checkIndex = checkIndex;
	}

	public String getAttributes() {
		return attributes;
	}

	public void setAttributes(String attributes) {
		this.attributes = attributes;
	}

	public String getCheckValue() {
		return checkValue;
	}

	public void setCheckValue(String checkValue) {
		this.checkValue = checkValue;
	}
	
	
	
}
