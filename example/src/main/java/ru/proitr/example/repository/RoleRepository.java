package ru.proitr.example.repository;

import org.springframework.stereotype.Repository;
import ru.proitr.example.domain.auth.Role;

import java.util.List;
import java.util.Set;

/**
 * Created by Gainutdinov on 07.09.17.
 */
@Repository
public interface RoleRepository extends CustomJpaRepository<Role, String>
{
	Set<Role> findByIdIn(List<String> ids);
}
