package ru.proitr.example.service.auth;

import ru.proitr.example.domain.auth.ProjectUser;

/**
 * Created by Gainutdinov on 06.09.17.
 */
public interface UserService
{
	void save(ProjectUser projectUser);

	ProjectUser findByLogin(String login);
}
