package ru.proitr.example.service.test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import ru.proitr.example.domain.auth.Role;
import ru.proitr.example.domain.auth.RolesEnum;
import ru.proitr.example.repository.UserRepository;
import ru.proitr.example.utils.ProjectUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Gainutdinov on 12.09.17.
 */
@Service
public class TestService
{
	@Autowired private UserRepository userRepository;

	@Cacheable(value = "userRolesDefault")
	public List<RolesEnum> getUserRoles()
	{
		List<RolesEnum> result = new ArrayList<>();
		List<Role> roles = userRepository.getProjectUserRolesByLogin(ProjectUtils.getLogin());
		roles.stream().forEach(role -> {result.add(role.getId());});

		return result;
	}

	@Cacheable(value = "userRoles", key = "#login")
	public List<RolesEnum> getUserRoles(String login)
	{
		List<RolesEnum> result = new ArrayList<>();
		List<Role> roles = userRepository.getProjectUserRolesByLogin(login);
		roles.stream().forEach(role -> {result.add(role.getId());});

		return result;
	}
}
