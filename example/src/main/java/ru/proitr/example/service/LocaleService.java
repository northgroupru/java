package ru.proitr.example.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Service;

import java.util.Locale;

/**
 * Created by Gainutdinov on 08.09.17.
 */
@Service
public class LocaleService
{
	@Autowired private MessageSource messageSource;

	public String translate(String code, Object ... args)
	{
		try
		{
			String result = messageSource.getMessage(code, args, new Locale("ru"));
			return result;
		}
		catch (Exception e)
		{
			return String.format("%s_%s", code, getCurrentLocale());
		}
	}

	public String getCurrentLocale()
	{
		return LocaleContextHolder.getLocale().getLanguage();
	}
}
