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
        User u1 = new User("lanakane",
            "password",
            "lana.kane@figgisagency.local",
            admins);

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
                1231.00,
                736,
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
                999.00,
                1233,
                u1));

        userService.save(u1);

        // data, user
        ArrayList<UserRoles> datas = new ArrayList<>();
        datas.add(new UserRoles(new User(),
            r3));
        datas.add(new UserRoles(new User(),
            r2));
        User u2 = new User("sterlingarcher",
            "1234567",
            "sterling.archer@figgisagency.com",
            datas);

        u2.getListings()
            .add(new Listing(
                "Sterling Archer's Penthouse",
                "entire place",
                "New York",
                3,
                9,
                true,
                4,
                5,
                1200.00,
                2430,
                u2));

        userService.save(u2);

        // user
        ArrayList<UserRoles> users = new ArrayList<>();
        users.add(new UserRoles(new User(),
            r2));
        User u3 = new User("cyrilfiggis",
            "password",
            "cyril.figgis@figgisagency.com",
            users);

        u3.getListings()
            .add(new Listing(
                "Cyril Figgis' Apartment",
                "shared room",
                "New York",
                1,
                3,
                true,
                2,
                2,
                200.00,
                945,
                u3));
        userService.save(u3);

        User u4 = new User("admin",
            "password",
            "admin@figgisagency.local",
            admins);

        u4.getListings()
            .add(new Listing(
                "Admin's House",
                "entire place",
                "Nashville",
                2,
                12,
                true,
                6,
                6,
                400.00,
                1000,
                u4));
        u4.getListings()
            .add(new Listing(
                "Admin's Lakehouse",
                "entire place",
                "Lake Cumberland",
                2,
                9,
                true,
                4,
                5,
                340.00,
                736,
                u4));

        userService.save(u4);
    }
}
