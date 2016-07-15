package woo.study.web.common.functions.actionlog2.parameter;

import java.io.BufferedReader;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import woo.study.web.common.util.IOSystem;
import woo.study.web.common.util.JSON2Helper;
import woo.study.web.common.wrapper.InfoLogServletRequest;

public class JSONRequestParameter extends CommonRequestParameter {
	
	private static final Logger log = LoggerFactory.getLogger(JSONRequestParameter.class);
	
	private Map<String, Object> jsonParamValues;
	
	public JSONRequestParameter(HttpServletRequest request) {
		super(request);
		setJsonParamValues(request);
	}
	
	
	
	public JSONRequestParameter(HttpServletRequest request, Map<String, String> configParamValues,
			Map<String, String> pathParamValues) {
		super(request, configParamValues, pathParamValues);
		setJsonParamValues(request);
	}



	public JSONRequestParameter(HttpServletRequest request, Map<String, String> pathParamValues) {
		super(request, pathParamValues);
		setJsonParamValues(request);
	}



	public JSONRequestParameter(Map<String, String> configParamValues, HttpServletRequest request) {
		super(configParamValues, request);
		setJsonParamValues(request);
	}



	@Override
	protected String getFromRequestBodyAndQueryString(String name) {
		
		if(jsonParamValues != null){
			Object value = jsonParamValues.get(name);
			if(value == null){
				return super.getFromRequestBodyAndQueryString(name);
			}
			
			return value.toString(); 
		}
		
		return null;
	}
	
	private void setJsonParamValues(HttpServletRequest request){
		
		try {
			InfoLogServletRequest infoRequest = (InfoLogServletRequest)request;
			BufferedReader br = infoRequest.getReader();
			String content = IOSystem.read(br);
			
			jsonParamValues = JSON2Helper.toObject(content, Map.class);
			log.debug("map:" + jsonParamValues);
			
			if(jsonParamValues == null){
				jsonParamValues = new HashMap<String, Object>();
			}
			log.debug("map:" + jsonParamValues);
			
		} catch (Exception e) {
			log.error("request.getReader error:", e);
			throw new RuntimeException("request.getReader error:" + e);
		}

	}

}
