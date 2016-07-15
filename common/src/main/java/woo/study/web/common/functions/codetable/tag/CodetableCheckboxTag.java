package woo.study.web.common.functions.codetable.tag;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import javax.servlet.jsp.JspTagException;

import woo.study.web.common.util.AssertHelper;
import woo.study.web.common.util.NumberUtils;

public class CodetableCheckboxTag extends CodetableCommon {
	
	private static final long	serialVersionUID	= 1L;
	
	private String table;
	private boolean enterFlag = false;
	private String name;
	private String attributes;
	private String checkIndexs;
	private String checkValues;
	
	private Set<Integer> checkIndexsList;
	private Set<String> checkValuesList;
	
	public int doStartTag() throws JspTagException {
		
		Map<String, String> tableRecords = getMap(table);
		
		if(tableRecords == null || tableRecords.size() == 0) {
			return SKIP_BODY;
		}
		
		{
			checkIndexsList = new HashSet<Integer>();
			if(AssertHelper.notEmpty(checkIndexs)){
				String[] indexs = checkIndexs.split(",");
				for(String indexStr : indexs){
					
					if(indexStr != null){
						indexStr = indexStr.trim();
					}
					
					int index = NumberUtils.parseInt(indexStr);
					checkIndexsList.add(index);
				}
			}
		}
		
		{
			checkValuesList = new HashSet<String>();
			if(AssertHelper.notEmpty(checkValues)){
				String[] values = checkValues.split(",");
				for(String value : values){
					
					if(value != null){
						value = value.trim();
					}
					
					checkValuesList.add(value);
				}
			}
		}
		
		
		int i = 0 ;
		for(Map.Entry<String, String> entry : tableRecords.entrySet()){
			
			try {
				boolean checked = false;
				if(checkIndexsList.contains(i) || checkValuesList.contains(entry.getKey())){
					checked = true;
				}
				
				this.pageContext.getOut().print(getCheckbox(entry.getKey(), entry.getValue(), checked));
				
				++i;
			} catch (Exception e) {
				return SKIP_BODY;
			}
		}
		
		return SKIP_BODY;
	}

	private String getCheckbox(String key, String value, boolean checked){
		StringBuilder radioString = new StringBuilder();
		
		radioString.append("<input name=\"").append(name).append("\" type=\"checkbox\" value=\"").append(key).append("\" ");
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

	public String getAttributes() {
		return attributes;
	}

	public void setAttributes(String attributes) {
		this.attributes = attributes;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCheckIndexs() {
		return checkIndexs;
	}

	public void setCheckIndexs(String checkIndexs) {
		this.checkIndexs = checkIndexs;
	}

	public String getCheckValues() {
		return checkValues;
	}

	public void setCheckValues(String checkValues) {
		this.checkValues = checkValues;
	}

}
