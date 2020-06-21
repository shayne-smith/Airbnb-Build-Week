package com.lambdaschool.airbnbbuildweek.services;

import com.lambdaschool.airbnbbuildweek.models.User;

import java.util.List;

public interface UserService
{
    List<User> findAll();

    User findUserById(long id);

    User findByName(String name);

    User save(User user);

    void deleteUserRole(
        long userid,
        long roleid);

    void addUserRole(
        long userid,
        long roleid);
}
