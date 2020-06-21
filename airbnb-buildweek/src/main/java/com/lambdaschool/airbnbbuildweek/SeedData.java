package com.lambdaschool.airbnbbuildweek;

import com.lambdaschool.airbnbbuildweek.models.Listing;
import com.lambdaschool.airbnbbuildweek.models.Role;
import com.lambdaschool.airbnbbuildweek.models.User;
import com.lambdaschool.airbnbbuildweek.models.UserRoles;
import com.lambdaschool.airbnbbuildweek.services.RoleService;
import com.lambdaschool.airbnbbuildweek.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;

@Transactional
@Component
public class SeedData implements CommandLineRunner
{
    @Autowired
    UserService userService;

    @Autowired
    RoleService roleService;

    @Override
    public void run(String... args) throws Exception
    {
        Role r1 = new Role("admin");
        Role r2 = new Role("user");
        Role r3 = new Role("data");

        r1 = roleService.save(r1);
        r2 = roleService.save(r2);
        r3 = roleService.save(r3);

        // admin, data, user
        ArrayList<UserRoles> admins = new ArrayList<>();
        admins.add(new UserRoles(new User(),
            r1));
        admins.add(new UserRoles(new User(),
            r2));
        admins.add(new UserRoles(new User(),
            r3));
        User u1 = new User("admin",
            "password",
            "lana.kane@figgisagency.local",
            admins);

        //                @NotNull String listingname,
        //        @NotNull String roomtype,
        //        @NotNull String location,
        //        int minnumnights,
        //        int maxnumguests,
        //        boolean petsallowed,
        //        int numrooms,
        //        int numbeds,
        //        @NotNull User user)

        u1.getListings()
            .add(new Listing(
                "Lana Kane's Apartment",
                "entire place",
                "New York",
                2,
                6,
                false,
                2,
                3,
                u1));
        u1.getListings()
            .add(new Listing(
                "Lana Kane's Tropical Paradise",
                "entire place",
                "Danger Island",
                2,
                6,
                false,
                2,
                3,
                u1));

        userService.save(u1);

        // data, user
        ArrayList<UserRoles> datas = new ArrayList<>();
        datas.add(new UserRoles(new User(),
            r3));
        datas.add(new UserRoles(new User(),
            r2));
        User u2 = new User("Sterling Archer",
            "1234567",
            "sterling.archer@figgisagency.com",
            datas);

        u2.getListings()
            .add(new Listing(
                "Sterling's Archer Penthouse",
                "entire place",
                "New York",
                3,
                9,
                true,
                4,
                5,
                u1));

        userService.save(u2);
    }
}
