package ru.proitr.example.domain.auth;

/**
 * Created by Gainutdinov on 23.08.17.
 */
public enum RolesEnum
{
	ANONYMOUSE("ANONYMOUSE", "Анонимный пользователь"),
	MANAGER("MANAGER","Менеджер проектов"),
	ADMIN("ADMIN","Администратор");

	private String code;

	private String name;

	RolesEnum(String code, String name)
	{
		this.code = code;
		this.name = name;
	}

	public String getCode()
	{
		return code;
	}

	public String getName()
	{
		return name;
	}


}
