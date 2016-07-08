package com.lutongnet.base.functions.actionlog.datacenter.loader;


import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import com.lutongnet.base.functions.actionlog.model.vo.LogConfigParamValue;
import com.lutongnet.base.functions.actionlog.model.vo.LogConfigValue;
import com.lutongnet.base.functions.datacenter.loader.CommonDataLoader;
import com.lutongnet.base.util.DateUtils;
import com.lutongnet.base.util.NumberUtils;
import com.lutongnet.base.util.AssertHelper;

public class UserActionLogDataLoader extends CommonDataLoader<Map<String,LogConfigValue>> {

	public Map<String,LogConfigValue> loading() {

		InputStream inputStream = getFileStream();
		
		SAXReader reader = new SAXReader();
		Document document = null;
		try {
			document = reader.read(inputStream);
		} catch (DocumentException e) {
			throw new RuntimeException(e);
		}
		Element root = document.getRootElement();
		
		Iterator actionIter = root.elementIterator(); 
		Map<String,LogConfigValue>  configValues = new HashMap<String,LogConfigValue>();
		
		while (actionIter.hasNext()) {
			 Element actionElement = (Element) actionIter.next();
			 
			 LogConfigValue userActionValue = new LogConfigValue();
			 
			 String url = actionElement.attributeValue("url");
			 String method = actionElement.attributeValue("method");
			 
			 userActionValue.setUrl(url);
			 userActionValue.setActionCode(actionElement.attributeValue("action-code"));
			 userActionValue.setDesc(actionElement.attributeValue("desc"));
			 userActionValue.setMethod(method);
			 userActionValue.setRecordFlag(actionElement.attributeValue("record-flag"));;
			 
			 Iterator paramIter =  actionElement.elementIterator();
			 List<LogConfigParamValue> paramList = new ArrayList<LogConfigParamValue>();
			 while(paramIter.hasNext()){
				 LogConfigParamValue logParamValue = new LogConfigParamValue();
				 Element paramElement = (Element) paramIter.next();
				 
				 String name = paramElement.attributeValue("name");
				 
				 if(AssertHelper.notEmpty(name)){
					 logParamValue.setName(name);
				 }
				 
				 String type = paramElement.attributeValue("column");
				 if(AssertHelper.notEmpty(type)){
					 logParamValue.setColumn(type);
				 }
				 
				 String value = paramElement.attributeValue("value");
				 logParamValue.setValue(value);
				 
				 String maxLength = paramElement.attributeValue("maxLength");
				 if(AssertHelper.isEmpty(maxLength)){
					 logParamValue.setMaxLength(0);
				 }else{
					 logParamValue.setMaxLength(NumberUtils.parseInt(maxLength));
				 }
				 
				 String dateType = paramElement.attributeValue("type");
				 if(AssertHelper.isEmpty(dateType)){
					 logParamValue.setType(DateUtils.FORMAT_DAFAULT);
				 }else{
					 logParamValue.setType(dateType);
				 }
				 
				 paramList.add(logParamValue);
			 }
			 
			 userActionValue.setParamNames(paramList);
			 
			 String key = url;
			 if(AssertHelper.notEmpty(method)){
				 key = method.toUpperCase()+url;
			 }
			 configValues.put(key, userActionValue);
		}
		
		return configValues;
	}

}
