package woo.study.web.common.functions.codetable.tag;

import java.io.IOException;

import java.util.Map;

import javax.servlet.jsp.JspTagException;
import javax.servlet.jsp.JspWriter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import woo.study.web.common.util.AssertHelper;

public class CodetableJsonTag extends CodetableCommon {
	
	private static Logger log	= LoggerFactory.getLogger(CodetableJsonTag.class);
	
	private static final String ARRAY = "Array";
	private static final String OBJECT = "Object";
	
	private String table;
	private String type;
	private String none;
	
	public int doStartTag() throws JspTagException {
		
		Map<String, String> tableRecords = getMap(table);
		
		try {
			JspWriter out = this.pageContext.getOut();
			
			if (tableRecords == null || tableRecords.size() == 0) {
				if (ARRAY.equalsIgnoreCase(type)) {
					none="[]";
				}else{
					none="{}";
				}
				out.print(none);
			} else if (ARRAY.equalsIgnoreCase(type)) {
				out.print(genArray(tableRecords));
			} else {
				out.print(genObject(tableRecords));
			}
		} catch (IOException e) {}
		
		return SKIP_BODY;
	}
	
	private String genArray(Map<String, String> tableRecords){
		
		StringBuilder stringBuilder = new StringBuilder("[");
		for(Map.Entry<String, String> entry : tableRecords.entrySet()){
			
			stringBuilder.append("{\"key\":\"").append(entry.getKey()).append("\"");
			stringBuilder.append(",");
			stringBuilder.append("\"value\":\"").append(entry.getValue()).append("\"");
			stringBuilder.append("}");
			stringBuilder.append(",");
		}
		
		int lastIndex = stringBuilder.lastIndexOf(",");
		stringBuilder = stringBuilder.replace(lastIndex, lastIndex+1, "]");
		
		return stringBuilder.toString();
	}
	
	private String genObject(Map<String, String> tableRecords){
		
		StringBuilder arrayBuilder = new StringBuilder();
		StringBuilder objectBuilder = new StringBuilder("{");
		for(Map.Entry<String, String> entry : tableRecords.entrySet()){
			
			objectBuilder.append("\"").append(entry.getKey()).append("\"");
			objectBuilder.append(":");
			objectBuilder.append("\"").append(entry.getValue()).append("\"");
			objectBuilder.append(",");
		}
		arrayBuilder.append("\"length\":").append(tableRecords.size());
		objectBuilder.append(arrayBuilder);
		objectBuilder.append("}");
		
		return objectBuilder.toString();
	}
	
	public void setTable(String table) {
		this.table = table;
	}

	public void setType(String type) {
		this.type = type;
	}

	public void setNone(String none) {
		this.none = none;
	}
	
}
