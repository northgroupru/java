package ru.proitr.example.model.dto.table;

public class TableColumnDto
{
	private String name, sortProperty, sortDirection, additionalUrlParams;

	public TableColumnDto()
	{
	}

	public TableColumnDto(String name)
	{
		this.name = name;
	}

	public TableColumnDto(String name, String sortProperty)
	{
		this(name, sortProperty, null);
	}

	public TableColumnDto(String name, String sortProperty, String additionalUrlParams)
	{
		this.name = name;
		this.sortProperty = sortProperty;
		this.additionalUrlParams = additionalUrlParams;
	}

	public String getSortDirection()
	{
		return sortDirection;
	}

	public void setSortDirection(String sortDirection)
	{
		this.sortDirection = sortDirection;
	}

	public String getSortProperty()
	{
		return sortProperty;
	}

	public void setSortProperty(String sortProperty)
	{
		this.sortProperty = sortProperty;
	}

	public String getName()
	{
		return name;
	}

	public void setName(String name)
	{
		this.name = name;
	}

	public boolean isSortable()
	{
		return sortProperty != null;
	}

	public boolean isSorted()
	{
		return sortDirection != null;
	}

	public String getAdditionalUrlParams()
	{
		return additionalUrlParams;
	}

	public void setAdditionalUrlParams(String additionalUrlParams)
	{
		this.additionalUrlParams = additionalUrlParams;
	}
}

