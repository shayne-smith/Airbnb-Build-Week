package com.lambdaschool.airbnbbuildweek.repository;

import com.lambdaschool.airbnbbuildweek.models.Role;
import org.springframework.data.repository.CrudRepository;

public interface RoleRepository extends CrudRepository<Role, Long>
{
    Role findByNameIgnoreCase(String name);
}
