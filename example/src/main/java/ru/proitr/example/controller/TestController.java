package ru.proitr.example.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.data.web.SortDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.proitr.example.conversion.test.TestDataConverter;
import ru.proitr.example.conversion.test.TestDataConverterConfiguration;
import ru.proitr.example.domain.auth.RolesEnum;
import ru.proitr.example.domain.test.TestData;
import ru.proitr.example.model.dto.table.TableColumnDto;
import ru.proitr.example.model.dto.table.TableHeaderDto;
import ru.proitr.example.repository.test.TestDataRepository;
import ru.proitr.example.repository.test.TestDataSpecification;
import ru.proitr.example.service.test.TestService;

import java.util.Arrays;
import java.util.List;

/**
 * Created by Gainutdinov on 12.09.17.
 */
@Controller
@RequestMapping("/test")
public class TestController
{
	@Autowired private TestDataConverter testDataConverter;
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
	public String drawTable(
			Model mv,
			@RequestParam(value = "userId", required = false) String userId,
			@RequestParam(value = "lessValueInt", required = false) Integer lessValueInt,
			@PageableDefault(size = 20) @SortDefault(sort = "valInt", direction = Sort.Direction.ASC) Pageable pageable)
	{
		mv.addAttribute("title", "Example table.");

		Sort.Order sortOrder = pageable.getSort().iterator().next();

		mv.addAttribute("sortDirection", sortOrder.getDirection());
		mv.addAttribute("sortProperty", sortOrder.getProperty());

		TableHeaderDto tableHeaderDto = new TableHeaderDto(
				Arrays.asList(
						new TableColumnDto("id"),
						new TableColumnDto("userId"),
						new TableColumnDto("valInt", "valInt"),
						new TableColumnDto("valString", "valString"),
						new TableColumnDto("resultInt"),
						new TableColumnDto("resultString")
				),
				"/test/table",
				"page=" + pageable.getPageNumber() + "&size=" + pageable.getPageSize()
				+ (userId != null ? "&userId=" + userId : "")
				+ (lessValueInt != null ? "&lessValueInt=" + lessValueInt.toString() : ""),
				sortOrder
		);

		mv.addAttribute("tableHeader", tableHeaderDto);

		TestDataSpecification testDataSpecification = new TestDataSpecification(lessValueInt, userId);

		Page<TestData> testData = testDataRepository.findAll(testDataSpecification, pageable);

		TestDataConverterConfiguration testDataConverterConfiguration = new TestDataConverterConfiguration(true, true);
		mv.addAttribute("testDataConverterConfiguration", testDataConverterConfiguration);
		mv.addAttribute("testData", testDataConverter.convert(testData, testDataConverterConfiguration));
		mv.addAttribute("userIdFilter", userId);
		mv.addAttribute("lessValueIntFilter", lessValueInt);

		return "freemarker/test/table";
	}


}
