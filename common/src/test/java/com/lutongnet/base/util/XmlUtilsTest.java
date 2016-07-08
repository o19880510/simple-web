package com.lutongnet.base.util;

import java.io.StringReader;
import java.util.HashMap;
import java.util.Map;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.junit.Test;

import com.lutongnet.base.util.XmlUtils;

public class XmlUtilsTest {
	
	/**
	 * - <S:Envelope xmlns:S="http://schemas.xmlsoap.org/soap/envelope/">
- <S:Body>
- <ns2:firstSayMethodResponse xmlns:ns2="http://webserice.woo.lutong.com/">
  <result>hello HelloWebServiceRequest [name=null, age=12, date=null]</result> 
  </ns2:firstSayMethodResponse>
  </S:Body>
  </S:Envelope>
	 */
	@Test
	public void testParseXml(){
		String responseXML = "<S:Envelope xmlns:S=\"http://schemas.xmlsoap.org/soap/envelope/\">" 
				+ " <S:Body>"
				+ " <ns2:firstSayMethodResponse xmlns:ns2=\"http://webserice.woo.lutong.com/\">"
				+ "  <result>hello HelloWebServiceRequest [name=null, age=12, date=null]</result> "
				+ " </ns2:firstSayMethodResponse>" 
				+ "  </S:Body>" 
				+ " </S:Envelope>";
		
		Map<String, String> result = XmlUtils.parse(responseXML.getBytes(), "Body.firstSayMethodResponse", new String[]{"result"});
		System.out.println(result);
		
		Map<String, String> result2 = XmlUtils.subXml(responseXML, new String[]{"S:Envelope"});
		System.out.println(result2);
	}
	
	@Test
	public void testParseXml02(){
		String responseXML = "<S:Envelope xmlns:S=\"http://schemas.xmlsoap.org/soap/envelope/\">" 
				+ " <S:Body>"
				+ " <ns2:firstSayMethodResponse xmlns:ns2=\"http://webserice.woo.lutong.com/\">"
				+ "  <result>hello HelloWebServiceRequest [name=null, age=12, date=null]</result> "
				+ " </ns2:firstSayMethodResponse>" 
				+ "  </S:Body>" 
				+ " </S:Envelope>";
		
		SAXReader reader = new SAXReader();
		Document document = null;
		try {
			document = reader.read(new StringReader(responseXML));
		} catch (DocumentException e) {
			e.printStackTrace();
		}

		Element root = document.getRootElement();
		System.out.println(root.getName());
		System.out.println(root.getStringValue());
		System.out.println(root.element("Body").element("firstSayMethodResponse").element("result").getTextTrim());
		
	}
}
