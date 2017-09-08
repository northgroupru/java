package ru.proitr.example.domain.auth;

import ru.proitr.example.domain.IdGeneratedEntity;
import ru.proitr.example.domain.MainConstants;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;
import java.util.function.Consumer;

/**
 * Created by Gainutdinov on 22.08.17.
 */
@Entity
@Table(schema = MainConstants.AUTH_SCHEMA_NAME, name = "users", uniqueConstraints = {@UniqueConstraint(columnNames = {"login"})})
public class ProjectUser extends IdGeneratedEntity
{
	private static final long serialVersionUID = -4840628900773206343L;

	@Column(unique = true, nullable = false)
	private String login;

	@Column(nullable = false, name = "PASSWORD")
	private String password;

	@Column(name = "first_name")
	private String firstName;

	@Column(name = "last_name")
	private String lastName;

	@Column
	private String email;

	@Column(name = "is_enabled", nullable = false)
	private int enabled;

	@ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinTable(
			name = "user_roles",
			schema = MainConstants.AUTH_SCHEMA_NAME,
			joinColumns = @JoinColumn(name = "user_id"),
			inverseJoinColumns = @JoinColumn(name = "role_id")
	)
	private Set<Role> roles = new HashSet<>();

	public String getLogin()
	{
		return login;
	}

	public void setLogin(String login)
	{
		this.login = login;
	}

	public String getPassword()
	{
		return password;
	}

	public void setPassword(String password)
	{
		this.password = password;
	}

	public String getFirstName()
	{
		return firstName;
	}

	public void setFirstName(String firstName)
	{
		this.firstName = firstName;
	}

	public String getLastName()
	{
		return lastName;
	}

	public void setLastName(String lastName)
	{
		this.lastName = lastName;
	}

	public String getEmail()
	{
		return email;
	}

	public void setEmail(String email)
	{
		this.email = email;
	}

	public int getEnabled()
	{
		return enabled;
	}

	public void setEnabled(int enabled)
	{
		this.enabled = enabled;
	}

	public Set<Role> getRoles()
	{
		return roles;
	}

	public void setRoles(Set<Role> roles)
	{
		this.roles = roles;
	}

	public ProjectUser()
	{
	}

	public ProjectUser(String login, String password, String firstName, String lastName, String email, int enabled, Set<Role> roles)
	{
		this.login = login;
		this.password = password;
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.enabled = enabled;
		this.roles = roles;
	}

	public ProjectUser(ProjectUser projectUser)
	{
		super(projectUser);

		setLogin(projectUser.getLogin());
		setFirstName(projectUser.getFirstName());
		setLastName(projectUser.getLastName());
		setEmail(projectUser.getEmail());
		setEnabled(projectUser.getEnabled());
		setRoles(projectUser.getRoles());
	}

	public ProjectUser(String id)
	{
		super(id);
	}

	public static ProjectUser ProjectUser(Consumer<ProjectUser> consumer)
	{
		ProjectUser projectUser = new ProjectUser();
		consumer.accept(projectUser);

		return projectUser;
	}
}
