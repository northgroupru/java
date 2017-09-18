package ru.proitr.example.conversion.test;

import org.springframework.stereotype.Service;
import ru.proitr.example.common.conversion.ProjectConverterConfigurable;
import ru.proitr.example.domain.test.TestData;
import ru.proitr.example.model.dto.test.TestDataDto;

/**
 * Created by Gainutdinov on 18.09.17.
 */
@Service
public class TestDataConverter implements ProjectConverterConfigurable<TestData, TestDataDto, TestDataConverterConfiguration>
{
	@Override
	public TestDataDto convert(TestData source, TestDataConverterConfiguration configuration)
	{
		TestDataDto target = new TestDataDto();

		target.setId(source.getId());
		target.setUserId(source.getUserId());
		target.setValInt(source.getValInt());
		target.setValString(source.getValString());

		if (configuration.isShowResultInt())
		{
			target.setResultInt(source.getValInt() + 1000);
		}

		if (configuration.isShowResultString())
		{
			target.setResultString(source.getValString() + " its_result");
		}

		return target;
	}

	@Override
	public TestDataConverterConfiguration configure()
	{
		return new TestDataConverterConfiguration(false, false);
	}
}
