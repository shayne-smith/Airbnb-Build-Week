package com.lambdaschool.airbnbbuildweek.services;

import com.lambdaschool.airbnbbuildweek.models.Role;

import java.util.List;

public interface RoleService
{
    List<Role> findAll();

    Role findRoleById(long id);

    Role findByName(String name);

    Role save(Role role);
}
