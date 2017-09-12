package ru.proitr.example.domain.auth;

import ru.proitr.example.domain.MainConstants;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by Gainutdinov on 04.09.17.
 */
@Entity
@Table(name = "role", schema = MainConstants.AUTH_SCHEMA_NAME)
public class Role implements Serializable
{
	private static final long serialVersionUID = 5925167673108763686L;

	@Id
	@Column(unique = true, nullable = false)
	@Enumerated(EnumType.STRING)
	private RolesEnum id;

	@Column
	private String name;

	@ManyToMany(mappedBy = "roles")
	private Set<ProjectUser> projectUsers = new HashSet<>();

	public Role()
	{
	}

	public RolesEnum getId()
	{
		return id;
	}

	public void setId(RolesEnum id)
	{
		this.id = id;
	}

	public String getName()
	{
		return name;
	}

	public void setName(String name)
	{
		this.name = name;
	}

	public Set<ProjectUser> getProjectUsers()
	{
		return projectUsers;
	}

	public void setProjectUsers(Set<ProjectUser> projectUsers)
	{
		this.projectUsers = projectUsers;
	}
}
