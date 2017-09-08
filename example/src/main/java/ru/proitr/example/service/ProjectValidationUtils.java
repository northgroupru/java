package ru.proitr.example.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by Gainutdinov on 08.09.17.
 */
@Service
public class ProjectValidationUtils
{
	@Autowired private static LocaleService localeService;

	public static String getErrorMessage(String code)
	{
		return localeService.translate(code);
	}
}
