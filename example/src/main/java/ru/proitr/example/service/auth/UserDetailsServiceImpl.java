package ru.proitr.example.service.auth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ru.proitr.example.domain.auth.ProjectUser;
import ru.proitr.example.domain.auth.Role;
import ru.proitr.example.repository.UserRepository;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Gainutdinov on 04.09.17.
 */
@Service
public class UserDetailsServiceImpl implements UserDetailsService
{
	@Autowired
	private UserRepository userRepository;

	@Autowired
	private UserServiceImpl userService;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException
	{
	 	ProjectUser user = userService.findByLogin(username);

	 	if (user == null || user.getEnabled() == 0)
		{
			throw new UsernameNotFoundException("Пользователь не найден");
		}

		List<GrantedAuthority> authorities = new ArrayList<>();

		try
		{
			if (user.getRoles() != null)
			{
				for (Role role : user.getRoles())
				{
					authorities.add(new SimpleGrantedAuthority(role.getId().toString()));
				}

			}
		}
		catch (Exception e)
		{
			throw new RuntimeException(e);
		}

		UserDetails userDetails = new User(
				user.getLogin(),
				user.getPassword(),
				user.getEnabled() == 1 ? true : false,
				true,
				true,
				true,
				authorities);

		return userDetails;
	}
}
