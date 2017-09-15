package ru.proitr.example.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;
import ru.proitr.example.common.util.freemarker.BuildUriMethod;

/**
 * Created by Gainutdinov on 15.09.17.
 */
@ControllerAdvice
public class CommonControllerService
{
	@Autowired
	private BuildUriMethod buildUriMethod;

	@ModelAttribute("buildUriMethod")
	public Object uriBuilder()
	{
		return buildUriMethod;
	}
}
