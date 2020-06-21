package com.lambdaschool.airbnbbuildweek.controllers;

import com.lambdaschool.airbnbbuildweek.models.User;
import com.lambdaschool.airbnbbuildweek.services.UserService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController
{
    @Autowired
    private UserService userService;

    // http://localhost:2019/users/users">http://localhost:2019/users/users
    @ApiOperation(value = "returns all Users",
        response = User.class,
        responseContainer = "List")
    @PreAuthorize("hasAnyRole('ADMIN')")
    @GetMapping(value = "/users",
        produces = {"application/json"})
    public ResponseEntity<?> listAllUsers()
    {
        List<User> myUsers = userService.findAll();
        return new ResponseEntity<>(myUsers,
            HttpStatus.OK);
    }
}
