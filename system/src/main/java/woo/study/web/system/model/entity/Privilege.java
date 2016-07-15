package woo.study.web.system.model.entity;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotBlank;

@Entity
@Table(name = "t_privilege")
public class Privilege implements Serializable {

	/**
	 * 
	 */
	private static final long	serialVersionUID	= -2654804727635545710L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer				id;

	@NotBlank(message = "{privilege.name.required}")
	@Column
	private String				name;

	@NotBlank(message = "{privilege.group.required}")
	@Column(name = "group_name")
	private String				group;

	@NotBlank(message = "{privilege.uri.required}")
	@Size(min = 1, max = 256, message = "{privilege.uri.max}")
	@Column
	private String				uri;

	@ManyToMany
	@JoinTable(name = "t_role_privilege", joinColumns = @JoinColumn(name = "privilege_id"), inverseJoinColumns = @JoinColumn(name = "role_id"))
	private Set<Role>			roles				= new HashSet<Role>();

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getGroup() {
		return group;
	}

	public void setGroup(String group) {
		this.group = group;
	}

	public Set<Role> getRoles() {
		return roles;
	}

	public void setRoles(Set<Role> roles) {
		this.roles = roles;
	}

	public String getUri() {
		return uri;
	}

	public void setUri(String uri) {
		this.uri = uri;
	}

	@Override
	public String toString() {
		return "Privilege [id=" + id + ", name=" + name + ", group=" + group + ", uri=" + uri + ", roles=" + roles
				+ "]";
	}
	
}
