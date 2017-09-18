package ru.proitr.example.model.dto.table;

import org.springframework.data.domain.Sort;

import java.util.List;

public class TableHeaderDto extends GenericTableHeaderDto<TableColumnDto>
{
	public TableHeaderDto()
	{
	}

	public TableHeaderDto(List<TableColumnDto> tableColumnDtos, String pageUrl, String urlParams, Sort.Order order)
	{
		super(tableColumnDtos, pageUrl, urlParams, order);
	}
}
