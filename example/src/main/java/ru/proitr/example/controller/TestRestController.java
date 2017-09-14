package ru.proitr.example.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.proitr.example.common.util.RandomValueStringGenerator;
import ru.proitr.example.domain.test.TestData;
import ru.proitr.example.model.rest.ApiResponse;
import ru.proitr.example.model.status.ProjectStatusCode;
import ru.proitr.example.repository.test.TestDataRepository;
import ru.proitr.example.utils.ProjectUtils;

import java.util.Random;

/**
 * Created by Gainutdinov on 14.09.17.
 */
@RestController
@RequestMapping("/test/rest")
public class TestRestController
{
	@Autowired
	private TestDataRepository testDataRepository;

	@RequestMapping(value = "generateData", method = RequestMethod.GET)
	public ApiResponse generateData(@RequestParam(value = "n", defaultValue = "100") Integer num)
	{
		RandomValueStringGenerator randomValueStringGenerator = new RandomValueStringGenerator(40);

		for (int i = 1; i <= num; i++)
		{
			TestData testData = testDataRepository.save(TestData.TestData(c -> {
				c.setUserId(ProjectUtils.getLogin());
				c.setValInt(new Random().nextInt(100));
				c.setValString(randomValueStringGenerator.generate());
			}));
		}

		return new ApiResponse(ProjectStatusCode.OK);
	}
}
