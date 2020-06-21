package com.lambdaschool.airbnbbuildweek.services;

import com.lambdaschool.airbnbbuildweek.exceptions.ResourceFoundException;
import com.lambdaschool.airbnbbuildweek.exceptions.ResourceNotFoundException;
import com.lambdaschool.airbnbbuildweek.models.Role;
import com.lambdaschool.airbnbbuildweek.repository.RoleRepository;
import com.lambdaschool.airbnbbuildweek.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Transactional
@Service(value = "roleService")
public class RoleServiceImpl implements RoleService
{
    @Autowired
    private RoleRepository rolerepos;

    @Autowired
    private UserRepository userrepos;

    public List<Role> findAll()
    {
        List<Role> list = new ArrayList<>();
        /*
         * findAll returns an iterator set.
         * iterate over the iterator set and add each element to an array list.
         */
        rolerepos.findAll()
            .iterator()
            .forEachRemaining(list::add);
        return list;
    }

    public Role findRoleById(long id)
    {
        return rolerepos.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("Role id " + id + " not found!"));
    }

    public Role findByName(String name)
    {
        Role rr = rolerepos.findByNameIgnoreCase(name);

        if (rr != null)
        {
            return rr;
        } else
        {
            throw new ResourceNotFoundException(name);
        }
    }

    public Role save(Role role)
    {
        if (role.getUsers()
            .size() > 0)
        {
            throw new ResourceFoundException("User Roles are not updated through Role.");
        }

        return rolerepos.save(role);
    }
}
