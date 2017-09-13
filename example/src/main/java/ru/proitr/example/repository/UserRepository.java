package ru.proitr.example.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ru.proitr.example.domain.auth.ProjectUser;
import ru.proitr.example.domain.auth.Role;

import java.util.List;

/**
 * Created by Gainutdinov on 04.09.17.
 */
@Repository
public interface UserRepository extends CustomJpaRepository<ProjectUser, String>
{
	ProjectUser findByLoginIgnoreCase(String login);

	@Query("select c.roles from ProjectUser c where c.login = :login")
	List<Role> getProjectUserRolesByLogin(@Param("login") String login);
}
