package ru.proitr.example.configuration.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.session.SessionRegistry;
import org.springframework.security.core.session.SessionRegistryImpl;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.LoginUrlAuthenticationEntryPoint;
import org.springframework.security.web.session.HttpSessionEventPublisher;
import ru.proitr.example.domain.MainConstants;

/**
 * Created by Gainutdinov on 04.09.17.
 */
@Configuration
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter
{
	private static final String[] UNSECURED_RESOURCE_LIST = new String[]{"/login**", "/", "/registration**", "/test**"};

	@Bean
	public PasswordEncoder passwordEncoder()
	{
		return new BCryptPasswordEncoder();
	}

	@Override
	public void configure(WebSecurity web)
	{
		web.ignoring().antMatchers("/css/**", "/fonts/**", "/js/**", "/images/**");
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception
	{
		http
				.exceptionHandling()
					.authenticationEntryPoint(new LoginUrlAuthenticationEntryPoint("/login"))
					.and()
				.formLogin()
					.loginProcessingUrl("/login")
					.failureUrl("/login?error")
					.permitAll()
					.and()
				.logout()
					.logoutUrl("/logout")
					.deleteCookies(MainConstants.SESSION_COOKIE_NAME)
					.and()
				.headers()
					.frameOptions()
					.disable()
					.and()
				.authorizeRequests()
					.antMatchers(UNSECURED_RESOURCE_LIST).permitAll()
					.anyRequest().authenticated()
					.and()

				.csrf().disable()

				.sessionManagement()
					.maximumSessions(3)
					.expiredUrl("/login")
					.maxSessionsPreventsLogin(false)
					.sessionRegistry(sessionRegistry());
	}

	@Bean
	public SessionRegistry sessionRegistry()
	{
		SessionRegistry sessionRegistry = new SessionRegistryImpl();

		return sessionRegistry;
	}

	@Bean
	public static HttpSessionEventPublisher httpSessionEventPublisher()
	{
		return new HttpSessionEventPublisher();
	}
}
