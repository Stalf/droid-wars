package com.droidwars.web.controller;

import com.droidwars.entity.User;
import com.droidwars.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping(value = "/rest/user", method = RequestMethod.GET)
    public Iterable<User> getUserList() {
        return userService.findAll();
    }
}
