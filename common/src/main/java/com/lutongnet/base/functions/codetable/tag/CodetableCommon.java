package com.lutongnet.base.functions.codetable.tag;

import java.util.Map;

import javax.servlet.jsp.tagext.BodyTagSupport;

import org.springframework.context.ApplicationContext;

import com.lutongnet.base.functions.codetable.datacenter.CodetableDataManagement;
import com.lutongnet.base.util.SpringUtils;

public abstract class CodetableCommon extends BodyTagSupport {
	
	private static final long	serialVersionUID	= 1L;

	protected Map<String, String> getMap(String key) {
		ApplicationContext ctx = SpringUtils.getApplicationContext();
		CodetableDataManagement codetableDataManagement = (CodetableDataManagement)ctx.getBean(CodetableDataManagement.class);
		return codetableDataManagement.get(key);
	}
}
