package ru.proitr.example.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import ru.proitr.example.domain.auth.ProjectUser;
import ru.proitr.example.domain.auth.RolesEnum;
import ru.proitr.example.exception.ProjectException;
import ru.proitr.example.model.dto.auth.RegistrationDto;
import ru.proitr.example.model.status.ProjectStatusCode;
import ru.proitr.example.service.auth.RegistrationService;
import ru.proitr.example.service.auth.UserServiceImpl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.transaction.Transactional;
import javax.validation.Valid;

/**
 * Created by Gainutdinov on 06.09.17.
 */
@Controller
public class AuthController
{
	@Autowired private UserServiceImpl userService;
	@Autowired private RegistrationService registrationService;

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

	@RequestMapping(value = "/registration", method = RequestMethod.GET)
	public String registration(Model model)
	{
		model.addAttribute("projectUser", new ProjectUser());
		model.addAttribute("enumRoles", RolesEnum.values());

		return "freemarker/auth/registration";
	}

	@RequestMapping(value = "/registration", method = RequestMethod.POST)
	@Transactional
	public String registration(@RequestBody @Valid RegistrationDto request, BindingResult bindingResult, Model model)
	{
		if (bindingResult.hasErrors())
		{
			throw new ProjectException(ProjectStatusCode.ILLEGAL_INPUT, bindingResult);
		}

		registrationService.registr(request.login, request.firstName, request.lastName, request.email, request.password, request.roles);

		return "freemarker/auth/registration_completed";
	}
}
