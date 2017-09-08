package ru.proitr.example.service.auth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ru.proitr.example.domain.auth.ProjectUser;
import ru.proitr.example.domain.auth.Role;
import ru.proitr.example.repository.RoleRepository;
import ru.proitr.example.repository.UserRepository;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by Gainutdinov on 06.09.17.
 */
@Service
public class RegistrationService
{
	@Autowired private UserRepository userRepository;
	@Autowired private RoleRepository roleRepository;

	@Autowired private PasswordEncoder passwordEncoder;

	private ProjectUser registrUser(String login, String firstName, String lastName, String email, String password, ArrayList<String> roles)
	{
		Set<Role> roleIds = new HashSet<>();

		if (roles != null)
		{
			roleIds = roleRepository.findByIdIn(roles);
		}

		ProjectUser projectUser = new ProjectUser(login, password, firstName, lastName, email, 1, roleIds);
		userRepository.save(projectUser);

		return projectUser;
	}

	public void registr(String login, String firstName, String lastName, String email, String password, ArrayList<String> roles)
	{
		if (password != null)
		{
			password = passwordEncoder.encode(password);
		}

		registrUser(login, firstName, lastName, email, password, roles);
	}
}
