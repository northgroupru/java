package ru.proitr.example.common.conversion;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public interface ProjectConverterConfigurable<S, T, C> extends ProjectConverter<S, T>
{
	T convert(S source, C configuration);

	C configure();

	default T convert(S source)
	{
		return convert(source, configure());
	}

	default List<T> convert(List<S> list, C configuration)
	{
		return list.stream().map(it -> convert(it, configuration)).collect(Collectors.toList());
	}

	default List<T> convert(List<S> list)
	{
		return list.stream().map(it -> convert(it, configure())).collect(Collectors.toList());
	}

	default Page<T> convert(Page<S> page, C configuration)
	{
		if (!page.hasContent())
		{
			return new PageImpl<>(Collections.emptyList());
		}

		List<T> converted = convert(page.getContent(), configuration);

		return new PageImpl<>(converted, new PageRequest(page.getNumber(), page.getSize()), page.getTotalElements());
	}

	default Page<T> convert(Page<S> page)
	{
		return convert(page, configure());
	}
}
