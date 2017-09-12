package ru.proitr.example.model.rest;

import java.io.Serializable;

/**
 * Created by Gainutdinov on 08.09.17.
 */
public class ApiValidationError implements Serializable
{
	private static final long serialVersionUID = -1667852182550481267L;

	private String resource;

	private String field;

	private String code;

	private String description;

	public String getResource()
	{
		return resource;
	}

	public void setResource(String resource)
	{
		this.resource = resource;
	}

	public String getField()
	{
		return field;
	}

	public void setField(String field)
	{
		this.field = field;
	}

	public String getCode()
	{
		return code;
	}

	public void setCode(String code)
	{
		this.code = code;
	}

	public String getDescription()
	{
		return description;
	}

	public void setDescription(String description)
	{
		this.description = description;
	}
}
