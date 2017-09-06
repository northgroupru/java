package ru.proitr.example.controller;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.transaction.Transactional;

/**
 * Created by Gainutdinov on 06.09.17.
 */
@Controller
@Transactional
public class AuthController
{
	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public ModelAndView login(@RequestParam(value = "error",required = false) String error,
							  @RequestParam(value = "logout",	required = false) String logout)
	{
		ModelAndView model = new ModelAndView();

		if (error != null)
		{
			model.addObject("error", "Invalid Credentials provided.");
		}

		if (logout != null)
		{
			model.addObject("message", "Logged out from JournalDEV successfully.");
		}

		model.setViewName("freemarker/auth/login");

		return model;
	}

	@RequestMapping(value = "/logout", method = RequestMethod.GET)
	public ModelAndView logout(HttpServletRequest request, HttpServletResponse response)
	{
		ModelAndView model = new ModelAndView();
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();

		if (auth != null)
		{
			SecurityContextHolder.getContext().setAuthentication(null);
		}

		model.setViewName("freemarker/auth/logout");

		return model;
	}
}
