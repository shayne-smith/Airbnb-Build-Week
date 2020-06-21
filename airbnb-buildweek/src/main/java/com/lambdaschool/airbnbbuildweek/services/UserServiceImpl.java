package com.lambdaschool.airbnbbuildweek.services;

import com.lambdaschool.airbnbbuildweek.exceptions.ResourceFoundException;
import com.lambdaschool.airbnbbuildweek.exceptions.ResourceNotFoundException;
import com.lambdaschool.airbnbbuildweek.models.Listing;
import com.lambdaschool.airbnbbuildweek.models.Role;
import com.lambdaschool.airbnbbuildweek.models.User;
import com.lambdaschool.airbnbbuildweek.models.UserRoles;
import com.lambdaschool.airbnbbuildweek.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Transactional
@Service(value = "userService")
public class UserServiceImpl
    implements UserService
{
    @Autowired
    private UserRepository userrepos;

    @Autowired
    private RoleService roleService;

    @Autowired
    private UserAuditing userAuditing;

    public List<User> findAll()
    {
        List<User> list = new ArrayList<>();
        /*
         * findAll returns an iterator set.
         * iterate over the iterator set and add each element to an array list.
         */
        userrepos.findAll()
            .iterator()
            .forEachRemaining(list::add);
        return list;
    }

    public User findUserById(long id) throws ResourceNotFoundException
    {
        return userrepos.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("User id " + id + " not found!"));
    }

    public User findByName(String name)
    {
        User uu = userrepos.findByUsername(name.toLowerCase());
        if (uu == null)
        {
            throw new ResourceNotFoundException("User name " + name + " not found!");
        }
        return uu;
    }

    @Transactional
    @Override
    public User save(User user)
    {
        User newUser = new User();

        if (user.getUserid() != 0)
        {
            User oldUser = userrepos.findById(user.getUserid())
                .orElseThrow(() -> new ResourceNotFoundException("User id " + user.getUserid() + " not found!"));

            // delete the roles for the old user we are replacing
            for (UserRoles ur : oldUser.getRoles())
            {
                deleteUserRole(ur.getUser()
                        .getUserid(),
                    ur.getRole()
                        .getRoleid());
            }
            newUser.setUserid(user.getUserid());
        }

        newUser.setUsername(user.getUsername()
            .toLowerCase());
        newUser.setPasswordNoEncrypt(user.getPassword());
        newUser.setPrimaryemail(user.getPrimaryemail()
            .toLowerCase());

        newUser.getRoles()
            .clear();
        if (user.getUserid() == 0)
        {
            for (UserRoles ur : user.getRoles())
            {
                Role newRole = roleService.findRoleById(ur.getRole()
                    .getRoleid());

                newUser.addRole(newRole);
            }
        } else
        {
            // add the new roles for the user we are replacing
            for (UserRoles ur : user.getRoles())
            {
                addUserRole(newUser.getUserid(),
                    ur.getRole()
                        .getRoleid());
            }
        }

        //        @NotNull String listingname,
        //        @NotNull String roomtype,
        //        @NotNull String location,
        //        int minnumnights,
        //        int maxnumguests,
        //        boolean petsallowed,
        //        int numrooms,
        //        int numbeds,
        //        @NotNull User user)

        newUser.getListings()
            .clear();
        for (Listing l : user.getListings())
        {
            newUser.getListings()
                .add(new Listing(l.getListingname(),
                    l.getRoomtype(),
                    l.getLocation(),
                    l.getMinnumnights(),
                    l.getMaxnumguests(),
                    l.isPetsallowed(),
                    l.getNumrooms(),
                    l.getNumbeds(),
                    newUser));
        }

        return userrepos.save(newUser);
    }

    @Transactional
    @Override
    public void deleteUserRole(
        long userid,
        long roleid)
    {
        userrepos.findById(userid)
            .orElseThrow(() -> new ResourceNotFoundException("User id " + userid + " not found!"));
        roleService.findRoleById(roleid);

        if (userrepos.checkUserRolesCombo(userid,
            roleid)
            .getCount() > 0)
        {
            userrepos.deleteUserRoles(userid,
                roleid);
        } else
        {
            throw new ResourceNotFoundException("Role and User Combination Does Not Exists");
        }
    }

    @Transactional
    @Override
    public void addUserRole(
        long userid,
        long roleid)
    {
        userrepos.findById(userid)
            .orElseThrow(() -> new ResourceNotFoundException("User id " + userid + " not found!"));
        roleService.findRoleById(roleid);

        if (userrepos.checkUserRolesCombo(userid,
            roleid)
            .getCount() <= 0)
        {
            userrepos.insertUserRoles(userAuditing.getCurrentAuditor()
                    .get(),
                userid,
                roleid);
        } else
        {
            throw new ResourceFoundException("Role and User Combination Already Exists");
        }
    }
}
