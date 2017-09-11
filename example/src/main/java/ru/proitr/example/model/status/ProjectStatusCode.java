package ru.proitr.example.model.status;

/**
 * Created by Gainutdinov on 08.09.17.
 */
public enum ProjectStatusCode
{
	OK(0, "Операция выполнена успешно"),
	ILLEGAL_INPUT(1, "Некорректные входные данные"),
	UNKNOWN_ERROR(2, "Неизвестная ошибка"),
	ILLEGAL_DATA_FORMAT(3, "Некорректный формат данных");

	private int code;
	private String message;

	ProjectStatusCode(int code, String message)
	{
		this.code = code;
		this.message = message;
	}

	public int getCode()
	{
		return code;
	}

	public String getMessage()
	{
		return message;
	}
}
