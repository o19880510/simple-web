package woo.study.web.common.functions.actionlog2.datacenter.loader;


import java.io.InputStream;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import woo.study.web.common.functions.actionlog2.model.vo.LogConfigParamValue;
import woo.study.web.common.functions.actionlog2.model.vo.LogConfigValue;
import woo.study.web.common.functions.datacenter.loader.CommonDataLoader;
import woo.study.web.common.util.AssertHelper;
import woo.study.web.common.util.DateUtils;
import woo.study.web.common.util.NumberUtils;

public class UserActionLogDataLoader extends CommonDataLoader<List<LogConfigValue>> {

	public List<LogConfigValue> loading() {

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
		
		List<LogConfigValue> logConfigValues = new ArrayList<LogConfigValue>();
		
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
			 
			 logConfigValues.add(userActionValue);
		}
		
		return logConfigValues;
	}
	

}
