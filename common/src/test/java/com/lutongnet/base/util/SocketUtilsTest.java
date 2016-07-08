package com.lutongnet.base.util;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.net.UnknownHostException;
import java.util.Iterator;
import java.util.Map;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.junit.Test;

import com.lutongnet.base.util.CollectionUtils;
import com.lutongnet.base.util.SocketUtils;
import com.lutongnet.base.util.XmlUtils;
import com.lutongnet.base.util.algorithm.Base64;

public class SocketUtilsTest {
	
	@Test
	public void testGet() throws Exception{
		String URI = "/AAAServer/singleAuth.do?stbCode=hwtestwh2&spCode=sp_lutong&serviceCode=service_25618989&tranId=136927643529807&successURL=XX.do?oorderId=1&errorURL=_&sign=5f51336629f311720852df74056326d2&platform=HW2X";
			
		String result  = SocketUtils.get("61.191.44.220", 8080, URI);
		System.out.println(result);
		
		int startIndex = result.indexOf("Location:");
		int endIndex = result.indexOf("\n", startIndex);
		
		String responseUrl = result.substring(startIndex + "Location:".length(), endIndex);
		System.out.println(responseUrl);
		
		String paramsString = responseUrl.substring(responseUrl.indexOf("?") + 1);
		System.out.println(paramsString);
		
		String[] paramsStr = paramsString.split("&");
		for(String str : paramsStr){
			System.out.println(str);
		}
		
		Map map = CollectionUtils.parseToMap(paramsString, "&");
		System.out.println(map);
	}
	
	// @Test
	public void testSoap() throws Exception{
		String ip = "127.0.0.1";
		int port = 8081;
		
//		String requestUri = "/axis2/services/FileDownloadService";
//		String methodNS = "http://ws.apache.org/axis2";
//		String methodName = "download";
//		String paramsXml = "";
		
		String requestUri = "/axis2/services/HelloWorldService";
		String methodNS = "http://ws.apache.org/axis2";
		String methodName = "sayHello";
		String paramsXml = "<arg0>wooAAA</arg0>";
		
		String response = SocketUtils.soapWithTemplate(ip, port, methodNS, methodName, paramsXml, requestUri);
		
		int startIndex = response.indexOf("\n\n");
		String resBody = response.substring(startIndex+2);
		String[] bodys = resBody.split("\n");
		
		StringBuilder respXml = new StringBuilder();
		for(int i = 1; i < bodys.length; i+=2){
			respXml.append(bodys[i]);
		}
		
		System.out.println(respXml);
		
		Map<String, String> map = XmlUtils.parse(respXml.toString().getBytes(), "Body.downloadResponse.return", new String[]{"response"});
		
		String text = map.get("response");
		System.out.println(text);
		System.out.println(Base64.decodeToString(text));
	}
	
	@Test
	public void testJiangSuOrderSoap() throws Exception{
		String ip = "58.223.251.137";
		int port = 8295;
		String requestUri = "/services/NewGameOrderService";
		String methodNS = "http://ui.server.gameorder.game.vas.soap.interfaces.lcsmp.linkage.com";
		String methodName = "gameOrder";
		String paramsXml = 
			"<req>" +
				"<transactionID>spa000052013061302</transactionID>" +
				"<SPID>spa00005</SPID>" +
				"<userID>02586588234</userID>" +
				"<userIDType>1</userIDType>" + // 0：普通用户（需要绑定机顶盒）,1：测试用户(无需绑定机顶盒)
				"<userToken>03111508256980016640821125101009</userToken>" +
				"<productID>productIDa3000000000000000000097</productID>" +
				"<price>10000</price>" +
				"<subscriptionExtend>1</subscriptionExtend>" +
				"<timeStamp>20130613</timeStamp>" +
				"<action>1</action>" + // 1：表示普通订购
			"</req>";
		
		
		String response = SocketUtils.soapWithTemplate(ip, port, methodNS, methodName, paramsXml, requestUri);
		
		int startIndex = response.indexOf("\n\n");
		String resBody = response.substring(startIndex+2);
		String[] bodys = resBody.split("\n");
		
		StringBuilder respXml = new StringBuilder();
		for(int i = 1; i < bodys.length; i+=2){
			respXml.append(bodys[i]);
		}
		
		System.out.println(respXml);
		
		Map<String, String> map = XmlUtils.parse(respXml.toString().getBytes(), "Body.downloadResponse.return", new String[]{"response"});
		
		String text = map.get("response");
		System.out.println(text);
		System.out.println(Base64.decodeToString(text));
	}
	

	 @Test
	public void testSoapWebservice() throws Exception{
		String ip = "127.0.0.1";
//		int port = 8080;
		int port = 9000;
		
//		String requestUri = "/wsServerExample";
		String requestUri = "/hideGold/wsServerExample";
		String methodNS = "http://webserice.woo.lutong.com/";
		String methodName = "firstSayMethod";
		String paramsXml = "<request><age /></request>";
		  
		String response = SocketUtils.soapWithTemplate(ip, port, methodNS, methodName, paramsXml, requestUri);
		
		System.out.println(response);
		
		int startIndex = response.indexOf("\n\n");
		String resBody = response.substring(startIndex+2);
		String[] bodys = resBody.split("\n");
		
		StringBuilder respXml = new StringBuilder();
		for(int i = 1; i < bodys.length; i+=2){
			respXml.append(bodys[i]);
		}
		
		System.out.println(respXml);
		
//		Map<String, String> map = XmlUtils.parse(respXml.toString().getBytes(), "Body.downloadResponse.return", new String[]{"response"});
//		
//		String text = map.get("response");
//		System.out.println(text);
//		System.out.println(Base64.decodeToString(text));
	}
}
