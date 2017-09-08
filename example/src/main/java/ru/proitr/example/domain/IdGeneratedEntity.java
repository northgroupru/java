package ru.proitr.example.domain;

import org.eclipse.persistence.annotations.Cache;
import org.eclipse.persistence.config.CacheIsolationType;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import java.io.Serializable;

@Cache(shared = false, isolation = CacheIsolationType.ISOLATED)
@MappedSuperclass
public class IdGeneratedEntity implements Serializable
{
	private static final long serialVersionUID = -2313013958761804478L;

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