package ru.proitr.example.model.dto.auth;

import ru.proitr.example.domain.auth.RolesEnum;
import ru.proitr.example.utils.ProjectValidationErrors;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by Gainutdinov on 06.09.17.
 */
public class RegistrationDto implements Serializable
{
	private static final long serialVersionUID = -7160596102953772608L;

	@NotNull(message = ProjectValidationErrors.FIELD_EMPTY)
	public String login;

	public String firstName;

	public String lastName;

	public String email;

	@NotNull(message = ProjectValidationErrors.FIELD_EMPTY)
	public String password;

	public ArrayList<RolesEnum> roles;
}
