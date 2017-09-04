package ru.proitr.example.domain.auth;

import ru.proitr.example.domain.MainConstants;

import javax.persistence.*;

/**
 * Created by Gainutdinov on 04.09.17.
 */
@Entity
@Table(schema = MainConstants.AUTH_SCHEMA_NAME)
public class Roles
{
	@Id
	@Column(unique = true)
	@Enumerated(EnumType.STRING)
	private RolesEnum id;

	@Column
	private String name;

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
}
