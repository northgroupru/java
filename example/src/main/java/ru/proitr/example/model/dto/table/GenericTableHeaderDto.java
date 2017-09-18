package ru.proitr.example.model.dto.table;

import org.springframework.data.domain.Sort;

import java.util.Collections;
import java.util.List;

public class GenericTableHeaderDto<COLUMN extends TableColumnDto>
{
	private List<COLUMN> columns;
	private String sortProperty, sortDirection, pageUrl, urlParams;

	public GenericTableHeaderDto()
	{
	}

	public GenericTableHeaderDto(List<COLUMN> columns, String pageUrl, String urlParams, Sort.Order order)
	{
		this.columns = columns;
		this.pageUrl = pageUrl;
		this.urlParams = urlParams;

		if (order != null)
		{
			sortProperty = order.getProperty();
			sortDirection = order.getDirection().toString().toLowerCase();

			columns.forEach(column -> {
				if (sortProperty.equals(column.getSortProperty()))
				{
					column.setSortDirection(sortDirection);
				}
			});
		}
	}

	public COLUMN getColumn(String name)
	{
		return columns.stream().filter(c -> name.equals(c.getName())).findAny().orElse(null);
	}

	public void addColumn(COLUMN column)
	{
		columns.add(column);

		if (sortProperty.equals(column.getSortProperty()))
		{
			column.setSortDirection(sortDirection);
		}
	}

	public List<COLUMN> getColumns()
	{
		return Collections.unmodifiableList(columns);
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

	public String getPageUrl()
	{
		return pageUrl;
	}

	public void setPageUrl(String pageUrl)
	{
		this.pageUrl = pageUrl;
	}

	public String getUrlParams()
	{
		return urlParams;
	}

	public void setUrlParams(String urlParams)
	{
		this.urlParams = urlParams;
	}
}
