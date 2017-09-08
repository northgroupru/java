package ru.proitr.example.service.auth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import ru.proitr.example.domain.auth.ProjectUser;
import ru.proitr.example.repository.UserRepository;

/**
 * Created by Gainutdinov on 06.09.17.
 */
@Service
public class UserServiceImpl implements UserService
{
	@Autowired
	private UserRepository userRepository;

	private BCryptPasswordEncoder bCryptPasswordEncoder;

	@Override
	public void save(ProjectUser projectUser)
	{
		projectUser.setPassword(bCryptPasswordEncoder.encode(projectUser.getPassword()));

		userRepository.save(projectUser);
	}

	@Override
	public ProjectUser findByLogin(String login)
	{
		return userRepository.findByLoginIgnoreCase(login);
	}
}
