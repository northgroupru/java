package ru.proitr.example.common.util.freemarker;

import freemarker.template.SimpleScalar;
import freemarker.template.TemplateMethodModelEx;
import freemarker.template.TemplateModelException;
import org.springframework.stereotype.Component;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

@Component
public class BuildUriMethod implements TemplateMethodModelEx
{
	public Object exec(List arguments) throws TemplateModelException
	{
		if (arguments.isEmpty())
		{
			throw new TemplateModelException("no arguments specified");
		}

		UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(arguments.get(0).toString());

		for (int i = 1; i < arguments.size(); i++)
		{
			String[] paramParts = arguments.get(i).toString().split("=");

			if (paramParts.length < 2)
			{
				throw new TemplateModelException(String.format("bad argument at position %d", i + 1));
			}

			builder.queryParam(paramParts[0], paramParts[1]);
		}

		return new SimpleScalar(builder.build().toUriString());
	}
}

