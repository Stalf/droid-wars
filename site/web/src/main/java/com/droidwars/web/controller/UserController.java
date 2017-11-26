package com.droidwars.web.controller;

import com.droidwars.core.entity.User;
import com.droidwars.core.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.AuditorAware;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private AuditorAware<User> auditorService;

    @GetMapping(value = "/rest/user")
    public Iterable<User> getUserList() {
        return userService.findAll();
    }

    @GetMapping("current-user")
    public User getCurrentUser() {
        return auditorService.getCurrentAuditor();
    }
}
