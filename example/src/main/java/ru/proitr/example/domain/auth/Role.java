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
	@Id
	@Column(unique = true, nullable = false)
	private String id;

	@Column
	private String name;

	@ManyToMany(mappedBy = "roles")
	private Set<ProjectUser> projectUsers = new HashSet<>();

	public Role()
	{

	}

	public String getId()
	{
		return id;
	}

	public void setId(String id)
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
