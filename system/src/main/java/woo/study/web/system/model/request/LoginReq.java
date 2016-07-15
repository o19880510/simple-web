package woo.study.web.system.model.request;

import org.hibernate.validator.constraints.NotEmpty;


public class LoginReq {

	@NotEmpty ( message = "{login.userid.required}" )
	private String	userid;

	@NotEmpty ( message = "{login.password.required}" )
	private String	password;

	@NotEmpty ( message = "{login.verifyCode.required}" )
	private String	verifyCode;

	// 强行登录,挤掉已经登录的相同账号
	private boolean	forceLogin;

	public String getUserid ( ) {
		return userid;
	}

	public void setUserid ( String userid ) {
		this.userid = userid;
	}

	public String getPassword ( ) {
		return password;
	}

	public void setPassword ( String password ) {
		this.password = password;
	}

	public String getVerifyCode ( ) {
		return verifyCode;
	}

	public void setVerifyCode ( String verifyCode ) {
		this.verifyCode = verifyCode;
	}

	public boolean isForceLogin ( ) {
		return forceLogin;
	}

	public void setForceLogin ( boolean forceLogin ) {
		this.forceLogin = forceLogin;
	}

	@Override
	public String toString() {
		return "LoginReq [userid=" + userid + ", password=" + password + ", verifyCode=" + verifyCode + ", forceLogin=" + forceLogin + "]";
	}
	
}
