package woo.study.web.common.util;

import java.io.ByteArrayInputStream;

import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;


/**
 * XML数据解析工具
 * 
 * @author tianjp
 *
 */
public class XmlUtils {
	
	/**
	 * 获取xml数据中节点的值
	 * 
	 * <p>
	 * 用法示例<br>
	 * XML:<br>
	 * &lt;root&gt;<br>
	 * 	&lt;node1&gt;<br>
	 * 		&lt;subnode&gt;text value&lt;/subnode&gt;<br>
	 * 	&lt;/node1&gt;<br>
	 * 		&lt;node2&gt;<br>
	 * 			&lt;subnode&gt;text value&lt;/subnode&gt;<br>
	 * 		&lt;/node2&gt;<br>
	 * 		&lt;node3&gt;&lt;/node3&gt;<br>
	 * &lt;/root&gt;<br>
	 * 
	 * <br>可以这样获取数据:<br>
	 * Map<String, String> values = XmlUtils.parse(XML, "root", new String[]{"node1.subnode", "node2.subnode"， "node3.subnode"});<br>
	 * values数据格式：{node1.subnode="text value", node2.subnode="text value", node3.subnode=null}<br>
	 * </p>
	 * 
	 * @param in xml数据流
	 * @param rootName 所指xml根元素,表示解析哪个节点;若包含此节点，key中可以省略此节点名
	 * @param keys 关键字段
	 * @return Map对象
	 */
	public static Map<String, String> parse(InputStream in,String rootName,String[] keys){
		SAXReader reader = new SAXReader();
		Document document = null;
		try {
			document = reader.read(in);
		} catch (DocumentException e) {
			e.printStackTrace();
			return new HashMap<String, String>();
		}

		Element root = document.getRootElement();
		Element rootElement = find(root, rootName);

		Map<String,String> map = new HashMap<String, String>();
		for(String key : keys){
			Element tempEle = find(rootElement, key);
			String value = null;
			if(tempEle != null){
				value = tempEle.getText();
			}
			map.put(key, value);
		}
		
		return map;
		
	}
	
	private static Element find(Element rootElement, String key){
		
		if(key.indexOf(".") == -1){
			return rootElement.element(key);
		}else{
			String[] nodeNames = key.split("\\.");
			Element tempEle = rootElement;
			for(String node : nodeNames){
				if(tempEle != null){
					tempEle = tempEle.element(node);
				}else{
					break;
				}
			}
			
			return tempEle;
		}
	}
	
	/**
	 * 获取xml数据中节点的值
	 * @param xml xml数据流
	 * @param rootName 所指xml根元素,表示解析哪个节点;若包含此节点，key中可以省略此节点名
	 * @param keys
	 * @return
	 */
	public static Map<String, String> parse(byte[] xml,String rootName,String[] keys){
		return parse(new ByteArrayInputStream(xml),rootName,keys) ;
	}
	
	
	/**
	 * 获取xml数据中节点的值<br>
	 * 限制：xml所有的节点不能有属性
	 * @param xml XML 字符串
	 * @param keys
	 * @return
	 */
	public static Map<String, String> subXml(String xml,String[] keys){
		
		Map<String,String> map = new HashMap<String, String>();
		for(String key : keys ){
			String value = subXml(xml, key);
			map.put(key, value);
		}
		
		return map;
	}
	
	
	private static String subXml(String xml,String key){
		String[] keys = key.split("\\.");
		
		String returnXml = xml;
		for(String smallKey : keys){
			returnXml = subXmlSingel(returnXml, smallKey);
			if(AssertHelper.isEmpty(returnXml)){
				break;
			}
		}
		
		return returnXml;
	}
	
	private static String subXmlSingel(String xml,String key){
		String startStr = "<"+key+">";
		String endStr = "</"+key+">";
		
		int startIndex = xml.indexOf(startStr);
		int endIndex = xml.indexOf(endStr);
		
		if(startIndex != -1 && endIndex != -1 && startIndex < endIndex){
			return xml.substring(startIndex + startStr.length(), endIndex);
		}
		
		return "";
	}
}
