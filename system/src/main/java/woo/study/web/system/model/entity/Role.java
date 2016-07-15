package woo.study.web.system.model.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.Size;
import org.hibernate.validator.constraints.NotBlank;


@Entity
@Table ( name = "t_role" )
public class Role implements Serializable {

	/**
	 * 
	 */
	private static final long	serialVersionUID			= -6174510692221873060L;

	@Id
	@GeneratedValue ( strategy = GenerationType.IDENTITY )
	private Integer						id;

	@NotBlank ( message = "{role.name.required}" )
	@Column
	private String						name;

	@Size ( max = 200 , message = "{role.description.max}" )
	private String						description;

	@ManyToMany
	@JoinTable ( name = "t_user_role" , joinColumns = @JoinColumn ( name = "role_id" ) , inverseJoinColumns = @JoinColumn ( name = "user_id" ) )
	private List<User>				users									= new ArrayList<User> ( );

	@ManyToMany
	@JoinTable ( name = "t_role_privilege" , joinColumns = @JoinColumn ( name = "role_id" ) , inverseJoinColumns = @JoinColumn ( name = "privilege_id" ) )
	private List<Privilege>		privileges						= new ArrayList<Privilege> ( );

	@Transient
	private List<Integer>			assignedPrivilegeIds	= new ArrayList<Integer> ( );

	public Integer getId ( ) {
		return id;
	}

	public void setId ( Integer id ) {
		this.id = id;
	}

	public String getName ( ) {
		return name;
	}

	public void setName ( String name ) {
		this.name = name;
	}

	public List<User> getUsers ( ) {
		return users;
	}

	public void setUsers ( List<User> users ) {
		this.users = users;
	}

	public String getDescription ( ) {
		return description;
	}

	public void setDescription ( String description ) {
		this.description = description;
	}

	public List<Privilege> getPrivileges ( ) {
		return privileges;
	}

	public void setPrivileges ( List<Privilege> privileges ) {
		this.privileges = privileges;
	}

	public List<Integer> getAssignedPrivilegeIds ( ) {
		return assignedPrivilegeIds;
	}

	public void setAssignedPrivilegeIds ( List<Integer> assignedPrivilegeIds ) {
		this.assignedPrivilegeIds = assignedPrivilegeIds;
	}

	@Override
	public String toString() {
		return "Role [id=" + id + ", name=" + name + ", description=" + description + ", users=" + users
				+ ", privileges=" + privileges + ", assignedPrivilegeIds=" + assignedPrivilegeIds + "]";
	}
	
	
}
