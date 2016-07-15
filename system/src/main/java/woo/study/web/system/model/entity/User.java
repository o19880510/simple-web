package woo.study.web.system.model.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.validation.constraints.Pattern;

import org.hibernate.annotations.Type;
import org.joda.time.DateTime;

import woo.study.web.common.loader.EnumDisplay;


@Entity
@Table ( name = "t_user" )
public class User implements Serializable {
	
	public static enum Status implements EnumDisplay{
		
		ENABLE("启用"), DISABLE("禁用"), ONLINE("在线"), OFFLINE("离线");
		
		private String desc;
		
		private Status(String desc) {
			this.desc = desc;
		}

		@Override
		public String getDesc() {
			return this.desc;
		}
		
	}
	
	/**
	 * 
	 */
	private static final long	serialVersionUID	= 4210915695863772093L;

	@Id
	@GeneratedValue ( strategy = GenerationType.IDENTITY )
	private Integer						id;

	@Pattern ( regexp = "^[a-zA-Z0-9]+$" , message = "{user.userid.invalid}" )
	@Column
	private String						userid;

	@Pattern ( regexp = "^[a-zA-Z0-9]+$" , message = "{user.password.invalid}" )
	@Column
	private String						password;

	@Column
	private String						nickname;

	@Column
	private String						province;

	@Column
	private String						city;

	@Column ( name = "create_date" )
	@Type ( type = "org.joda.time.contrib.hibernate.PersistentDateTime" )
	private DateTime					createDate;

	@Column ( name = "last_login_date" )
	@Type ( type = "org.joda.time.contrib.hibernate.PersistentDateTime" )
	private DateTime					lastLoginDate;

	@Column
	private String						status;

	@ManyToMany ( fetch = FetchType.EAGER )
	@JoinTable ( name = "t_user_role" , joinColumns = @JoinColumn ( name = "user_id" ) , inverseJoinColumns = @JoinColumn ( name = "role_id" ) )
	private List<Role>				roles							= new ArrayList<Role> ( );

	public Integer getId ( ) {
		return id;
	}

	public void setId ( Integer id ) {
		this.id = id;
	}

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

	public String getNickname ( ) {
		return nickname;
	}

	public void setNickname ( String nickname ) {
		this.nickname = nickname;
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

	public DateTime getCreateDate ( ) {
		return createDate;
	}

	public void setCreateDate ( DateTime createDate ) {
		this.createDate = createDate;
	}

	public DateTime getLastLoginDate ( ) {
		return lastLoginDate;
	}

	public void setLastLoginDate ( DateTime lastLoginDate ) {
		this.lastLoginDate = lastLoginDate;
	}

	public String getStatus ( ) {
		return status;
	}

	public void setStatus ( String status ) {
		this.status = status;
	}

	public List<Role> getRoles ( ) {
		return roles;
	}

	public void setRoles ( List<Role> roles ) {
		this.roles = roles;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", userid=" + userid + ", password=" + password + ", nickname=" + nickname
				+ ", province=" + province + ", city=" + city + ", createDate=" + createDate + ", lastLoginDate="
				+ lastLoginDate + ", status=" + status + "]";
	}
	
}
