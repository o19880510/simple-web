package woo.study.web.system.model.vo;

import java.util.Set;


public class UserInfo {

	private String				userid;
	private String				nickname;
	private String				status;
	private String				province;
	private String				city;

	/** 允许访问的资源URI */
	private Set<String>	allowUriSet;

	public String getUserid ( ) {
		return userid;
	}

	public void setUserid ( String userid ) {
		this.userid = userid;
	}

	public String getNickname ( ) {
		return nickname;
	}

	public void setNickname ( String nickname ) {
		this.nickname = nickname;
	}

	public String getStatus ( ) {
		return status;
	}

	public void setStatus ( String status ) {
		this.status = status;
	}

	public String getProvince ( ) {
		return province;
	}

	public void setProvince ( String province ) {
		this.province = province;
	}

	public String getCity ( ) {
		return city;
	}

	public void setCity ( String city ) {
		this.city = city;
	}

	public Set<String> getAllowUriSet() {
		return allowUriSet;
	}

	public void setAllowUriSet(Set<String> allowUriSet) {
		this.allowUriSet = allowUriSet;
	}

	@Override
	public String toString() {
		return "UserInfo [userid=" + userid + ", nickname=" + nickname + ", status=" + status + ", province=" + province + ", city=" + city + ", allowUriSet="
				+ allowUriSet + "]";
	}
	
}
