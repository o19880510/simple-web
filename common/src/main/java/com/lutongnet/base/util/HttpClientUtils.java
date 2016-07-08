package com.lutongnet.base.util;

import java.io.IOException;

import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.apache.http.Header;
import org.apache.http.entity.ByteArrayEntity;

import com.lutongnet.base.util.IOSystem;

/**
 * http client 通信工具
 * 
 * @author tianjp
 *
 */
public class HttpClientUtils {

	/**
	 * 发送json数据
	 * @param url http地址
	 * @param jsonParams json数据。字符串形式
	 * @return HttpClientResponse
	 * @throws Exception 
	 */
	public static HttpClientResponse jsonPost(String url, String jsonParams) throws Exception {
		return jsonPost(url, jsonParams, null);
	}
	
	/**
	 * 发送json数据
	 * @param url http地址
	 * @param jsonParams json数据。字符串形式
	 * @param jsessionId 会话ID
	 * @return HttpClientResponse
	 * @throws Exception
	 */
	public static HttpClientResponse jsonPost(String url, String jsonParams, String jsessionId) throws Exception {

		HttpPost httpPost = new HttpPost(url);

		httpPost.setHeader("Content-Type", "application/json;charset=UTF-8");
		if (AssertHelper.notEmpty(jsessionId)) {
			httpPost.setHeader("Cookie", "JSESSIONID=" + jsessionId);
		}
		httpPost.setEntity(new ByteArrayEntity(jsonParams.getBytes("UTF-8")));

		return exe(httpPost);
	}
	
	/**
	 * 发送json数据
	 * @param url http地址
	 * @param params Map数据
	 * @return HttpClientResponse
	 * @throws Exception
	 */
	public static HttpClientResponse jsonPost(String url, Map<String, Object> params) throws Exception {
		return jsonPost(url, params, null);
	}
	
	/**
	 * 发送json数据
	 * @param url http地址
	 * @param params Map数据
	 * @param jsessionId 会话ID
	 * @return HttpClientResponse
	 * @throws Exception
	 */
	public static HttpClientResponse jsonPost(String url, Map<String, Object> params, String jsessionId)
			throws Exception {
		String jsonParams = JSON2Helper.toJson(params);
		return jsonPost(url, jsonParams, jsessionId);
	}
	
	/**
	 * post方式发送数据
	 * 
	 * @param url http地址
	 * @param params Map数据
	 * @return HttpClientResponse
	 * @throws Exception
	 */
	public static HttpClientResponse post(String url, Map<String, Object> params) throws Exception {
		return post(url, toPostParams(params), null);
	}

	/**
	 * post方式发送数据
	 * @param url http地址
	 * @param params Map数据
	 * @param jsessionId 会话ID
	 * @return HttpClientResponse
	 * @throws Exception
	 */
	public static HttpClientResponse post(String url, Map<String, Object> params, String jsessionId) throws Exception {
		return post(url, toPostParams(params), jsessionId);
	}
	
	/**
	 * post方式发送数据
	 * @param url http地址
	 * @param params json数据。字符串形式
	 * @return HttpClientResponse
	 * @throws Exception
	 */
	public static HttpClientResponse post(String url, String params) throws Exception {
		return post(url, params, null);
	}
	
	/**
	 * post方式发送数据
	 * @param url http地址
	 * @param params json数据。字符串形式
	 * @param jsessionId 会话ID
	 * @return HttpClientResponse
	 * @throws Exception
	 */
	public static HttpClientResponse post(String url, String params, String jsessionId) throws Exception {

		HttpPost httpPost = new HttpPost(url);

		httpPost.setHeader("Content-Type", "application/x-www-form-urlencoded;charset=UTF-8");
		if (AssertHelper.notEmpty(jsessionId)) {
			httpPost.setHeader("Cookie", "JSESSIONID=" + jsessionId);
		}
		httpPost.setEntity(new ByteArrayEntity(params.getBytes("UTF-8")));

		return exe(httpPost);
	}

	/**
	 * get方式发送数据
	 * @param url http地址
	 * @param params Map数据
	 * @return HttpClientResponse
	 * @throws Exception
	 */
	public static HttpClientResponse get(String url, Map<String, Object> params) throws Exception {
		return get(url, params, null);
	}
	
	/**
	 * get方式发送数据
	 * @param url http地址
	 * @param params Map数据
	 * @param jsessionId 会话ID
	 * @return HttpClientResponse
	 * @throws Exception
	 */
	public static HttpClientResponse get(String url, Map<String, Object> params, String jsessionId) throws Exception {

		return get(url, toGetParams(params), jsessionId);
	}
	
	/**
	 * get方式发送数据
	 * @param url http地址
	 * @param params json数据。字符串形式
	 * @return HttpClientResponse
	 * @throws Exception
	 */
	public static HttpClientResponse get(String url, String params) throws Exception {
		return get(url, params, null);
	}
	
	/**
	 * get方式发送数据
	 * @param url http地址
	 * @param params json数据。字符串形式
	 * @param jsessionId 会话ID
	 * @return HttpClientResponse
	 * @throws Exception
	 */
	public static HttpClientResponse get(String url, String params, String jsessionId) throws Exception {

		HttpGet httpGet = new HttpGet(url + params);
		httpGet.setHeader("Cookie", "JSESSIONID=" + jsessionId);
		return exe(httpGet);
	}

	private static HttpClientResponse exe(HttpRequestBase request) throws IOException {
		
		HttpClient client = new DefaultHttpClient();
		HttpResponse response = client.execute(request);

		int statusCode = response.getStatusLine().getStatusCode();

		Header[] headers = response.getAllHeaders();
		for (Header header : headers) {
			System.out.println(header.getName() + ":" + header.getValue());
		}

		HttpEntity entity = response.getEntity();
		String entityStr = null;
		if (entity.isStreaming()) {

			InputStream in = entity.getContent();
			try {
				entityStr = IOSystem.readToString(in);
			} catch (Exception e) {
			}
		}

		EntityUtils.consume(entity);
		System.out.println("HTTP status code:" + statusCode);
		System.out.println("HTTP response body:" + entityStr);
		client.getConnectionManager().shutdown();
		return new HttpClientResponse(statusCode, entityStr);
	}

	private static String toGetParams(Map<String, Object> params) throws UnsupportedEncodingException {

		return toParams(params, true);

	}

	private static String toPostParams(Map<String, Object> params) throws UnsupportedEncodingException {

		return toParams(params, false);

	}

	private static String toParams(Map<String, Object> params, boolean isGet) throws UnsupportedEncodingException {

		if (params == null || params.isEmpty()) {
			return "";
		}

		StringBuilder sb = new StringBuilder();
		if (isGet) {

		}
		Set<Entry<String, Object>> entries = params.entrySet();
		for (Entry<String, Object> entry : entries) {
			sb.append("&").append(entry.getKey()).append("=").append(URLEncoder.encode(entry.getValue().toString(), "UTF-8"));
		}

		if (isGet) {
			sb.replace(0, 1, "?");
		} else {
			sb.replace(0, 1, "");
		}

		return sb.toString();

	}

}
