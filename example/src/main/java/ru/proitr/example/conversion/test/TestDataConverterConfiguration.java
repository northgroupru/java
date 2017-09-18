package ru.proitr.example.conversion.test;

/**
 * Created by Gainutdinov on 18.09.17.
 */
public class TestDataConverterConfiguration
{
	private boolean showResultInt;

	private boolean showResultString;

	public TestDataConverterConfiguration()
	{
	}

	public TestDataConverterConfiguration(boolean showResultInt, boolean showResultString)
	{
		this.showResultInt = showResultInt;
		this.showResultString = showResultString;
	}

	public boolean isShowResultInt()
	{
		return showResultInt;
	}

	public void setShowResultInt(boolean showResultInt)
	{
		this.showResultInt = showResultInt;
	}

	public boolean isShowResultString()
	{
		return showResultString;
	}

	public void setShowResultString(boolean showResultString)
	{
		this.showResultString = showResultString;
	}
}
