package woo.study.web.common.functions.codetable.datacenter.loader;

import java.io.InputStream;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import woo.study.web.common.functions.codetable.model.vo.CodeTableConfigValue;
import woo.study.web.common.functions.datacenter.loader.CommonDataLoader;

public class CodetableConfigDataLoader extends CommonDataLoader{
	
	
	// Map<String, CodeTableConfigValue>
	public Object loading() {
		
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
		Map<String, CodeTableConfigValue>  configValues = new HashMap<String, CodeTableConfigValue>();
		
		while (actionIter.hasNext()) {
			 Element tableElement = (Element) actionIter.next();
			 
			 CodeTableConfigValue configValue = new CodeTableConfigValue();
			 
			 String name = tableElement.attributeValue("name");
			 String tableName = tableElement.attributeValue("table");
			 String value = tableElement.attributeValue("value");
			 String desc = tableElement.attributeValue("desc");
			 String order = tableElement.attributeValue("order");
			 String orderBy = tableElement.attributeValue("orderBy");
			 
			 configValue.setName(name);
			 configValue.setTableName(tableName);
			 configValue.setValue(value);
			 configValue.setDesc(desc);
			 configValue.setOrder(order);
			 configValue.setOrderBy(orderBy);
			 
			 Iterator paramIter =  tableElement.elementIterator();
			 while(paramIter.hasNext()){
				 Element sqlElement = (Element) paramIter.next();
				 String sql = sqlElement.getText();
				 sql = sql.replace("\n", "");
				 configValue.setSql(sql);
			 }
			 
			 configValues.put(name.toUpperCase(), configValue);
		}
		
		return configValues;
	}

}
