package com.lutongnet.base.functions.codetable.tag;

import java.io.IOException;
import java.util.Map;

import javax.servlet.jsp.JspTagException;
import javax.servlet.jsp.JspWriter;

import com.lutongnet.base.util.AssertHelper;

public class CodetableOptionTag extends CodetableCommon {
	
	private static final long	serialVersionUID	= 1L;
	
	private String table;
	private String attributes;
	private int selectIndex = -1;
	private String selectValue;
	
	public int doStartTag() throws JspTagException {
		Map<String, String> tableRecords = getMap(table);
		
		if(tableRecords == null || tableRecords.size() == 0) {
			return SKIP_BODY;
		}
		
		JspWriter out = this.pageContext.getOut();  
		
		int index = 0;
		for(Map.Entry<String, String> entry : tableRecords.entrySet()){
			
			try {
				out.print("<option value=\"");
				out.print(entry.getKey());
				out.print("\" ");
				
				if(AssertHelper.notEmpty(attributes)){
					out.print(attributes);
				}
				
				if(AssertHelper.notEmpty(selectValue)){
					if(selectValue.equals(entry.getKey())){
						out.print("selected=\"selected\"");
					}
				}
				
				if(selectIndex == index){
					out.print("selected=\"selected\"");
				}
				
				out.print(" >");
				out.print(entry.getValue());
				out.print("</option>");
				out.println();
				
				++index;
			} catch (IOException e) {
				return SKIP_BODY;
			}
			
		}
		
		return SKIP_BODY;
	}

	public void setTable(String table) {
		this.table = table;
	}

	public String getTable() {
		return table;
	}

	public String getAttributes() {
		return attributes;
	}

	public void setAttributes(String attributes) {
		this.attributes = attributes;
	}

	public int getSelectIndex() {
		return selectIndex;
	}

	public void setSelectIndex(int selectIndex) {
		this.selectIndex = selectIndex;
	}

	public String getSelectValue() {
		return selectValue;
	}

	public void setSelectValue(String selectValue) {
		this.selectValue = selectValue;
	}
	
}
