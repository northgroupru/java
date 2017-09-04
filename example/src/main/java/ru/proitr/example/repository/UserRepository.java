package ru.proitr.example.repository;

import org.springframework.stereotype.Repository;
import ru.proitr.example.domain.auth.ProjectUser;

/**
 * Created by Gainutdinov on 04.09.17.
 */
@Repository
public interface UserRepository extends CustomJpaRepository<ProjectUser, String>
{
	ProjectUser findByLoginIgnoreCase(String login);
}
