package com.lutongnet.system.tag;

import java.util.Set;






import javax.servlet.http.HttpSession;
import javax.servlet.jsp.JspTagException;
import javax.servlet.jsp.tagext.BodyTagSupport;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.lutongnet.system.constant.AppConstant;
import com.lutongnet.system.datacenter.SystemDataManagementHelper;
import com.lutongnet.system.model.vo.UserInfo;

public class PermissionTag extends BodyTagSupport {
	
	private static Logger log	= LoggerFactory.getLogger(PermissionTag.class);
	
	private String uri;
	
	public int doStartTag() throws JspTagException {
		
		HttpSession session = this.pageContext.getSession();
		
		UserInfo user = (UserInfo) session.getAttribute(AppConstant.USER_INFO);
		if(user == null){
			return SKIP_BODY;
		}
		
		Set<String> privilegeUriSet = SystemDataManagementHelper.getInstance().getPrivilegeUris();
		Set<String> allowUriSet = user.getAllowUriSet();
		
		if(privilegeUriSet.contains(uri) ){
			if(allowUriSet.contains(uri)){
				return EVAL_BODY_INCLUDE;
			}
			return SKIP_BODY;
		}else{
			return EVAL_BODY_INCLUDE;
		}
		
	}

	public String getUri() {
		return uri;
	}

	public void setUri(String uri) {
		this.uri = uri;
	}
	
}
