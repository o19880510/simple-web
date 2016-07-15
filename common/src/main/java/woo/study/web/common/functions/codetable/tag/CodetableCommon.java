package woo.study.web.common.functions.codetable.tag;

import java.util.Map;

import javax.servlet.jsp.tagext.BodyTagSupport;

import org.springframework.context.ApplicationContext;

import woo.study.web.common.functions.codetable.datacenter.CodetableDataManagement;
import woo.study.web.common.util.SpringUtils;

public abstract class CodetableCommon extends BodyTagSupport {
	
	private static final long	serialVersionUID	= 1L;

	protected Map<String, String> getMap(String key) {
		ApplicationContext ctx = SpringUtils.getApplicationContext();
		CodetableDataManagement codetableDataManagement = (CodetableDataManagement)ctx.getBean(CodetableDataManagement.class);
		return codetableDataManagement.get(key);
	}
}
