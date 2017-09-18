package ru.proitr.example.model.dto.test;

import java.io.Serializable;

/**
 * Created by Gainutdinov on 18.09.17.
 */
public class TestDataDto implements Serializable
{
	private static final long serialVersionUID = 3557602339801292847L;

	private Long id;

	private String userId;

	private Integer valInt;

	private String valString;

	private Integer resultInt;

	private String resultString;

	public Long getId()
	{
		return id;
	}

	public void setId(Long id)
	{
		this.id = id;
	}

	public String getUserId()
	{
		return userId;
	}

	public void setUserId(String userId)
	{
		this.userId = userId;
	}

	public Integer getValInt()
	{
		return valInt;
	}

	public void setValInt(Integer valInt)
	{
		this.valInt = valInt;
	}

	public String getValString()
	{
		return valString;
	}

	public void setValString(String valString)
	{
		this.valString = valString;
	}

	public Integer getResultInt()
	{
		return resultInt;
	}

	public void setResultInt(Integer resultInt)
	{
		this.resultInt = resultInt;
	}

	public String getResultString()
	{
		return resultString;
	}

	public void setResultString(String resultString)
	{
		this.resultString = resultString;
	}
}
