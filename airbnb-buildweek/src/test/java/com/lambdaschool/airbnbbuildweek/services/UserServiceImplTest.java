package com.lambdaschool.airbnbbuildweek.services;

import com.lambdaschool.airbnbbuildweek.AirbnbBuildweekApplication;
import com.lambdaschool.airbnbbuildweek.exceptions.ResourceNotFoundException;
import com.lambdaschool.airbnbbuildweek.models.Listing;
import com.lambdaschool.airbnbbuildweek.models.Role;
import com.lambdaschool.airbnbbuildweek.models.User;
import com.lambdaschool.airbnbbuildweek.models.UserRoles;
import org.junit.After;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithUserDetails;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;

import static junit.framework.TestCase.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = AirbnbBuildweekApplication.class)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class UserServiceImplTest
{
    @Autowired
    private UserService userService;

    @Before
    public void setUp() throws
                        Exception
    {
        MockitoAnnotations.initMocks(this);
    }

    @After
    public void tearDown() throws
                           Exception
    {
    }

    @Test
    public void B_findUserById()
    {
        assertEquals("lanakane", userService.findUserById(4)
            .getUsername());
    }

    @Test(expected = ResourceNotFoundException.class)
    public void BA_findUserByIdNotFound()
    {
        assertEquals("cyrilfiggis", userService.findUserById(99)
            .getUsername());
    }

    @Test
    public void C_findAll()
    {
        assertEquals(4, userService.findAll()
            .size());
    }

    @Test
    public void D_delete()
    {
        userService.delete(9);
        assertEquals(3, userService.findAll()
            .size());
    }

    @Test(expected = ResourceNotFoundException.class)
    public void DA_notFoundDelete()
    {
        userService.delete(100);
        assertEquals(2, userService.findAll()
            .size());
    }

    @Test
    public void E_findByUsername()
    {
        assertEquals("admin", userService.findByName("admin")
            .getUsername());
    }

    @Test(expected = ResourceNotFoundException.class)
    public void EA_findByUsernameNotfound()
    {
        assertEquals("admin", userService.findByName("turtle")
            .getUsername());
    }

    @Test
    public void EB_findByNameContaining()
    {
        assertEquals(3, userService.findByNameContaining("a")
            .size());
    }

    @Test
    public void F_save()
    {
        ArrayList<UserRoles> datas = new ArrayList<>();
        User u2 = new User("tiger", "ILuvMath!", "tiger@school.lambda", datas);
        u2.getListings()
            .add(new Listing("Tiger's House", "entire place", "Mumbai", 2, 8, true, 0, 2, 230.00, 14000,  u2));

        User saveU2 = userService.save(u2);

        System.out.println("*** DATA ***");
        System.out.println(saveU2);
        System.out.println("*** DATA ***");

        assertEquals("Tiger's House", saveU2.getListings().get(0).getListingname());
    }

//    @Transactional
//    @WithUserDetails("lanakane")
//    @Test
//    public void G_update()
//    {
//        ArrayList<UserRoles> datas = new ArrayList<>();
//        User u2 = new User("lanakane", "password", "lanakane@figgisagency.local", datas);
//        u2.getUseremails()
//            .add(new Useremail(u2, "cinnamon@mymail.thump"));
//        u2.getUseremails()
//            .add(new Useremail(u2, "hops@mymail.thump"));
//        u2.getUseremails()
//            .add(new Useremail(u2, "bunny@email.thump"));
//
//        User updatedu2 = userService.update(u2, 7);
//
//        System.out.println("*** DATA ***");
//        System.out.println(updatedu2);
//        System.out.println("*** DATA ***");
//
//        int checking = updatedu2.getUseremails()
//            .size() - 1;
//        assertEquals("bunny@email.thump", updatedu2.getUseremails()
//            .get(checking)
//            .getUseremail());
//    }

//    @Transactional
//    @WithUserDetails("cinnamon")
//    @Test(expected = ResourceNotFoundException.class)
//    public void GB_updateNotCurrentUserNorAdmin()
//    {
//        Role r2 = new Role("user");
//
//        ArrayList<UserRoles> datas = new ArrayList<>();
//        User u2 = new User("cinnamon", "password", "cinnamon@school.lambda", datas);
//        u2.getUseremails()
//            .add(new Useremail(u2, "cinnamon@mymail.thump"));
//        u2.getUseremails()
//            .add(new Useremail(u2, "hops@mymail.thump"));
//        u2.getUseremails()
//            .add(new Useremail(u2, "bunny@email.thump"));
//
//        User updatedu2 = userService.update(u2, 8);
//
//        System.out.println("*** DATA ***");
//        System.out.println(updatedu2);
//        System.out.println("*** DATA ***");
//
//        int checking = updatedu2.getUseremails()
//            .size() - 1;
//        assertEquals("bunny@email.thump", updatedu2.getUseremails()
//            .get(checking)
//            .getUseremail());
//    }

    @Test(expected = ResourceNotFoundException.class)
    public void HA_deleteUserRoleRoleNotFound()
    {
        userService.deleteUserRole(7, 50);
    }

    @Test(expected = ResourceNotFoundException.class)
    public void HB_deleteUserRoleUserNotFound()
    {
        userService.deleteUserRole(50, 2);
    }

    @Test(expected = ResourceNotFoundException.class)
    public void IC_addUserRoleRoleNotFound()
    {
        userService.addUserRole(7, 50);
    }

    @Test(expected = ResourceNotFoundException.class)
    public void ID_addUserRoleUserNotFound()
    {
        userService.addUserRole(50, 2);
    }
}