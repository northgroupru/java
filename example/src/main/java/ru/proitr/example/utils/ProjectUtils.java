package ru.proitr.example.utils;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;

/**
 * Created by Gainutdinov on 12.09.17.
 */
public class ProjectUtils
{
	public static String getLogin()
	{
		String userName = "";
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

		if (principal instanceof UserDetails)
		{
			userName = ((UserDetails) principal).getUsername();
		}
		else
		{
			userName = principal.toString();
		}

		return userName;
	}
}
