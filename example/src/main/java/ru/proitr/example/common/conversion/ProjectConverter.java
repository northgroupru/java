package ru.proitr.example.common.conversion;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;

import java.util.List;
import java.util.stream.Collectors;

public interface ProjectConverter<S, T>
{
	T convert(S source);

	default List<T> convert(List<S> list)
	{
		return list.stream().map(it -> convert(it)).collect(Collectors.toList());
	}

	default Page<T> convert(Page<S> page)
	{
		List<T> converted = convert(page.getContent());

		return new PageImpl<>(converted, new PageRequest(page.getNumber(), page.getSize()), page.getTotalElements());
	}
}
