package woo.study.web.common.functions.codetable.tag;

import java.io.IOException;

import java.util.Map;
import javax.servlet.jsp.JspTagException;
import javax.servlet.jsp.JspWriter;

import woo.study.web.common.util.AssertHelper;

public class CodetableOutTag extends CodetableCommon {
	
	private String table;
	private String selectValue;
	private int selectIndex;
	private String defaultValue;
	
	public CodetableOutTag() {
		super();
		selectIndex = -1;
	}

	public int doStartTag() throws JspTagException {
		
		Map<String, String> tableRecords = getMap(table);
		
		if(tableRecords == null || tableRecords.size() == 0) {
			return SKIP_BODY;
		}
		
		JspWriter out = this.pageContext.getOut();  
		
		try {
			String value = null;
			if(AssertHelper.notEmpty(selectValue)){
				value = tableRecords.get(selectValue);
			}
			
			if(selectIndex >= 0){
				
				int index = 0;
				for(Map.Entry<String, String> entry : tableRecords.entrySet()){
					if(index == selectIndex){
						value = entry.getKey();
						break;
					}
					index++;
				}
			}
			
			if(AssertHelper.isEmpty(value)){
				value = defaultValue;
			}
			
			out.print(value);
		} catch (IOException e) {
		}
		
		return SKIP_BODY;
	}

	
	
	
	public void setTable(String table) {
		this.table = table;
	}

	public String getTable() {
		return table;
	}

	public String getSelectValue() {
		return selectValue;
	}

	public void setSelectValue(String selectValue) {
		this.selectValue = selectValue;
	}

	public String getDefaultValue() {
		return defaultValue;
	}

	public void setDefaultValue(String defaultValue) {
		this.defaultValue = defaultValue;
	}

	public int getSelectIndex() {
		return selectIndex;
	}

	public void setSelectIndex(int selectIndex) {
		this.selectIndex = selectIndex;
	}
	
}
