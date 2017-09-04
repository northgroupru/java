package ru.proitr.example.domain;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import java.io.Serializable;

@MappedSuperclass
public class IdGeneratedEntity implements Serializable
{
	private static final long serialVersionUID = -777888999000L;

	@Id
	@GeneratedValue(generator = "system-uuid")
	@Column(length = 40)
	protected String id;

	public String getId()
	{
		return id;
	}

	public void setId(String id)
	{
		this.id = id;
	}

	public IdGeneratedEntity()
	{
	}

	public IdGeneratedEntity(String id)
	{
		this.id = id;
	}

	protected IdGeneratedEntity(IdGeneratedEntity other)
	{
		this.id = other.id;
	}
}