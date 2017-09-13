package ru.proitr.example.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.proitr.example.domain.auth.RolesEnum;
import ru.proitr.example.service.test.TestService;

import java.util.List;

/**
 * Created by Gainutdinov on 12.09.17.
 */
@Controller
@RequestMapping("/test")
public class TestController
{
	@Autowired private TestService testService;

	@RequestMapping(value = "/caching")
	public String caching(Model mv)
	{
		mv.addAttribute("title", "Freemarker Template. Caching.");

		long start = System.nanoTime();
		List<RolesEnum> rolesEnumList = testService.getUserRoles();
		long end = System.nanoTime();

		mv.addAttribute("firstGetData", "First time:" + (end - start));

		start = System.nanoTime();
		rolesEnumList = testService.getUserRoles();
		end = System.nanoTime();

		mv.addAttribute("secondGetData", "Second time:" + (end - start));

		start = System.nanoTime();
		rolesEnumList = testService.getUserRoles();
		end = System.nanoTime();

		mv.addAttribute("thirdGetData", "Third time:" + (end - start));

		return "freemarker/test/caching";
	}
}
