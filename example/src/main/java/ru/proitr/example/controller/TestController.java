package ru.proitr.example.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.proitr.example.domain.auth.RolesEnum;
import ru.proitr.example.domain.test.TestData;
import ru.proitr.example.repository.test.TestDataRepository;
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
	@Autowired private TestDataRepository testDataRepository;

	@RequestMapping(value = "/caching")
	public String caching(Model mv)
	{
		mv.addAttribute("title", "Freemarker Template. Caching.");

		//default User
		long start = System.nanoTime();
		List<RolesEnum> rolesEnumList = testService.getUserRoles();
		long end = System.nanoTime();
		mv.addAttribute("firstGetDataDefault", "First time:" + (end - start));

		start = System.nanoTime();
		rolesEnumList = testService.getUserRoles();
		end = System.nanoTime();
		mv.addAttribute("secondGetDataDefault", "Second time:" + (end - start));

		start = System.nanoTime();
		rolesEnumList = testService.getUserRoles();
		end = System.nanoTime();
		mv.addAttribute("thirdGetDataDefault", "Third time:" + (end - start));

		//admin
		start = System.nanoTime();
		rolesEnumList = testService.getUserRoles("admin");
		end = System.nanoTime();
		mv.addAttribute("firstGetData", "First time:" + (end - start));

		start = System.nanoTime();
		rolesEnumList = testService.getUserRoles("admin");
		end = System.nanoTime();
		mv.addAttribute("secondGetData", "Second time:" + (end - start));

		start = System.nanoTime();
		rolesEnumList = testService.getUserRoles("admin");
		end = System.nanoTime();
		mv.addAttribute("thirdGetData", "Third time:" + (end - start));

		//manager
		start = System.nanoTime();
		rolesEnumList = testService.getUserRoles("manager");
		end = System.nanoTime();
		mv.addAttribute("firstGetData2", "First time:" + (end - start));

		start = System.nanoTime();
		rolesEnumList = testService.getUserRoles("manager");
		end = System.nanoTime();
		mv.addAttribute("secondGetData2", "Second time:" + (end - start));

		start = System.nanoTime();
		rolesEnumList = testService.getUserRoles("manager");
		end = System.nanoTime();
		mv.addAttribute("thirdGetData2", "Third time:" + (end - start));

		return "freemarker/test/caching";
	}

	@RequestMapping(value = {"/table", "/"})
	public String drawTable(Model mv, @PageableDefault(page = 0, size = 15) Pageable pageable)
	{
		mv.addAttribute("title", "Example table.");

		Page<TestData> testData = testDataRepository.findTestDataValInt30(pageable);

		return "freemarker/test/table";
	}


}
